import axios from 'axios';
import express from 'express';
import dotenv from 'dotenv';

const app = express();
const port = 9999;

async function getStockPrice(isinCd, retries = 3) {
    const url = process.env.SERVICE_URL;
    const key = process.env.SERVICE_KEY;

    const params = {
        serviceKey: decodeURIComponent(key),
        numOfRows: 1,
        pageNo: 1,
        resultType: 'json',
        isinCd
    };

    try {
        const response = await axios.get(url, { params });
        let item = response.data.response.body.items;
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

app.get('/:isinCd', async (req, res) => {
    const stockPrice = await getStockPrice(req.params.isinCd);
    res.send(stockPrice);
});

// 서버 시작
app.listen(port, () => {
    dotenv.config();
    console.log(`서버가 실행중입니다.`);
});