import { schedule } from 'node-cron';
import axios from 'axios';
import dotenv from 'dotenv';
import mysql from 'mysql2/promise';

dotenv.config();

// 매일 자정에 실행될 작업 스케줄링
// schedule('38 12 * * *', () => {
//     // dotenv.config();
//     initProcessData();
// });

const totalCount = await initProcessData();
fetchAndProcessData(totalCount);

async function initProcessData() {

    const url = process.env.SERVICE_URL;

    const params = {
        serviceKey: decodeURIComponent(process.env.SERVICE_KEY),
        pageNo: 1,
        numOfRows: 1,
        resultType: 'json'
    };

    try {
        const response = await axios.get(url, { params });
        let items = response.data.response.body.totalCount;
        return items;
    } catch (error) {
        console.error('API 호출 중 오류가 발생했습니다:', error);
    }
}

async function fetchAndProcessData(totalCount) {
    const url = process.env.SERVICE_URL;

    const params = {
        serviceKey: decodeURIComponent(process.env.SERVICE_KEY),
        pageNo: 1,
        numOfRows: totalCount,
        resultType: 'json'
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

        // finalItems 배열에서 필요한 정보만을 추출하여 새 배열 생성
        const simplifiedItems = finalItems.map(item => ({
            basDt: item.basDt,
            crno: item.crno,
            isinCd: item.isinCd,
            stckIssuCmpyNm: item.stckIssuCmpyNm,
            stckGenrDvdnAmt: item.stckGenrDvdnAmt
        }));

        insertData(simplifiedItems);

    } catch (error) {
        console.error('API 호출 또는 파일 생성 중 오류가 발생했습니다:', error);
    }
}


async function insertData(items) {
    const connection = await mysql.createConnection({
        host: process.env.MYSQL_HOST,
        port: process.env.MYSQL_PORT,
        user: process.env.MYSQL_USER,
        password: process.env.MYSQL_PASSWORD,
        database: process.env.MYSQL_DATABASE
    });

    const insertSql = `
        INSERT INTO your_table_name (stock_crno, stock_isin_cd, stock_stck_issu_cmpy_nm, stock_transaction_stck_genr_dvdn_amt) 
        VALUES (?, ?, ?, ?)
    `;

    for (let item of items) {
        const { crno, isinCd, stckIssuCmpyNm, stckGenrDvdnAmt } = item;
        await connection.execute(insertSql, [crno, isinCd, stckIssuCmpyNm, stckGenrDvdnAmt]);
    }

    await connection.end();
}


