import React, { useEffect, useState } from 'react';
import CardComponent from '@/pages/finance/ui/CardComponent/CardComponent';
import StockComponent from '@/pages/finance/ui/StockComponent/StockComponent';
import SavingsComponent from '@/pages/finance/ui/SavingsComponent/SavingsComponent';
import { StockDetail, StockListUp } from '@/pages/finance/model';
interface Stock {
    stockId: number;
    crno: string;
    isinCd: string;
    stckIssuCmpyNm: string;
    basDt: number[];
    stckGenrDvdnAmt: number;
}

export function Finance() {
    const [data, setData] = useState<Stock[]>([]);
    // const [detaildata, setDetailData] = useState<
    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await StockListUp();
                if (res.data.code === 200) {
                    setData(res.data.data.content);
                }
            } catch (err) {
                console.error(err);
            }
        };

        fetchData();
    }, []);
    useEffect(() => {
        const fetchDetail = async () => {
            try {
                for (const el of data) {
                    const res = await StockDetail(el.stockId);
                    console.log(res.data);
                }
            } catch (err) {
                console.log(err);
            }
        };
        fetchDetail();
    }, [data]);
    return (
        <div className="container">
            <div className="container">
                <div className="contentCard">
                    <div className="contentCard__title">소비도 투자도 저축도 현명하게</div>
                </div>
                <div className="stock">
                    <div className="stock__header">
                        <p>주식은 단타가 아냐</p>
                        <h2>배당률 좋은 주식 어때요?</h2>
                    </div>
                    <div className="stock__content">
                        {data.map((stock) => (
                            <div key={stock.stockId} className="stock__content--item">
                                <div className="stock__content--companyName">{stock.stckIssuCmpyNm}</div>
                                <div className="stock__content--info">
                                    <span>CRNO: {stock.crno}/</span>
                                    <span>ISIN Code: {stock.isinCd}/</span>
                                    <span>배당금: {stock.stckGenrDvdnAmt.toLocaleString()} 원/</span>
                                    <span>기준 날짜: {stock.basDt}</span>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
                {/* 추가적인 컴포넌트 렌더링 */}
            </div>
        </div>
    );
}
