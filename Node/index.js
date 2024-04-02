import { schedule } from 'node-cron';
import axios from 'axios';
import dotenv from 'dotenv';
import mysql from 'mysql2/promise';

// 매일 실행될 작업 스케줄링
schedule('30 23 * * *', async () => {
    dotenv.config();
    console.log(new Date() + " start");
    const date = await getDate();
    console.log(date);
    const totalCount = await initProcessData(date);
    await fetchAndProcessData(date, totalCount);
    console.log(new Date() + " end");
});


async function getDate() {
    const currentDate = new Date();

    currentDate.setDate(currentDate.getDate() - 1);
    const year = currentDate.getFullYear().toString();
    const month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    const day = currentDate.getDate().toString().padStart(2, '0');
    const formattedDate = year + month + day;

    return formattedDate;
}

async function initProcessData(date, retries = 3) {

    const url = process.env.SERVICE_URL;

    const params = {
        serviceKey: decodeURIComponent(process.env.SERVICE_KEY),
        pageNo: 1,
        numOfRows: 1,
        resultType: 'json',
        basDt: date
    };

    try {
        const response = await axios.get(url, { params });
        let item = response.data.response.body.totalCount;
        return item;
    } catch (error) {
        console.error('API 호출 중 오류가 발생했습니다:', error);
        if (retries > 0) {
            console.log(`재시도 중... (${retries} 남음)`);
            await new Promise(resolve => setTimeout(resolve, 1000));
            return initProcessData(date, retries - 1);
        } else {
            throw error;
        }
    }
}

async function fetchAndProcessData(date, totalCount, retries = 3) {
    
    const url = process.env.SERVICE_URL;

    const params = {
        serviceKey: decodeURIComponent(process.env.SERVICE_KEY),
        pageNo: 1,
        numOfRows: totalCount,
        resultType: 'json',
        basDt: date
    };

    try {
        const response = await axios.get(url, { params });
        let items = response.data.response.body.items.item;

        // 조건 1: "현금배당"만 필터링합니다.
        items = items.filter(item => item.stckDvdnRcdNm === '현금배당');

        // 조건 2: isinCd별로 최신 날짜 필터링, 만약 같은 날짜에 여러 데이터가 있으면 stckGenrDvdnAmt가 가장 큰 데이터를 선택합니다.
        const latestItemsMap = new Map();
        items.forEach(item => {
            if (!latestItemsMap.has(item.crno) ||
                latestItemsMap.get(item.crno).dvdnBasDt < item.dvdnBasDt ||
                (latestItemsMap.get(item.crno).dvdnBasDt === item.dvdnBasDt &&
                    parseFloat(latestItemsMap.get(item.crno).stckGenrDvdnAmt) < parseFloat(item.stckGenrDvdnAmt))) {
                latestItemsMap.set(item.crno, item);
            }
        });

        let finalItems = Array.from(latestItemsMap.values());

        // 조건 3: stckGenrDvdnAmt가 0이 아닌 데이터만 필터링합니다.
        finalItems = finalItems.filter(item => parseFloat(item.stckGenrDvdnAmt) > 0);

        await insert(finalItems);

    } catch (error) {
        console.error('API 호출 중 오류가 발생했습니다:', error);
        if (retries > 0) {
            console.log(`재시도 중... (${retries} 남음)`);
            await new Promise(resolve => setTimeout(resolve, 1000));
            return initProcessData(date, retries - 1);
        } else {
            throw error;
        }
    }
}

async function insert(finalItems) {
    const now = new Date();

    for (const item of finalItems) {
        try {
            const stockId = await insertIntoStockAndGetId(item.crno, item.isinCd, item.stckIssuCmpyNm, now);
            await insertIntoStockTransaction(stockId, {
                basDt: item.basDt,
                stckGenrDvdnAmt: item.stckGenrDvdnAmt
            }, now);
        } catch (error) {
            console.error('데이터 삽입 중 오류가 발생했습니다:', error);
        }
    }
}

async function insertIntoStockAndGetId(stockCrno, stockIsinCd, stockIssuCmpyNm, now) {
    const connection = await mysql.createConnection({
        host: process.env.MYSQL_HOST,
        port: process.env.MYSQL_PORT,
        user: process.env.MYSQL_USER,
        password: process.env.MYSQL_PASSWORD,
        database: process.env.MYSQL_DATABASE
    });

    try {

        const selectSql = `
            SELECT stock_id FROM stock WHERE stock_crno = ? AND stock_isin_cd = ?
        `;
        const [existingRows] = await connection.query(selectSql, [stockCrno, stockIsinCd]);

        if (existingRows.length > 0) {
            return existingRows[0].stock_id;
        } else {
            // 새 레코드를 삽입합니다.
            const insertSql = `
                INSERT INTO stock (stock_crno, stock_isin_cd, stock_stck_issu_cmpy_nm, created_date_time, modified_date_time) 
                VALUES (?, ?, ?, ?, ?)
            `;
            const [insertResult] = await connection.query(insertSql, [
                stockCrno, stockIsinCd, stockIssuCmpyNm, now, now
            ]);

            return insertResult.insertId;
        }
    } catch (error) {
        console.error('데이터베이스 작업 중 오류가 발생했습니다:', error);
        throw error;
    } finally {
        await connection.end();
    }
}

async function insertIntoStockTransaction(stockId, transactionData, now) {
    const connection = await mysql.createConnection({
        host: process.env.MYSQL_HOST,
        port: process.env.MYSQL_PORT,
        user: process.env.MYSQL_USER,
        password: process.env.MYSQL_PASSWORD,
        database: process.env.MYSQL_DATABASE
    });

    try {
        const insertSql = `
            INSERT INTO stock_transaction (stock_transaction_bas_dt, stock_transaction_stck_genr_dvdn_amt, stock_id, created_date_time, modified_date_time) 
            VALUES (?, ?, ?, ?, ?)
        `;
        await connection.query(insertSql, [
            transactionData.basDt,
            transactionData.stckGenrDvdnAmt,
            stockId,
            now,
            now
        ]);
    } catch (error) {
        console.error('stock_transaction 데이터베이스 작업 중 오류가 발생했습니다:', error);
        throw error;
    } finally {
        await connection.end();
    }
}
