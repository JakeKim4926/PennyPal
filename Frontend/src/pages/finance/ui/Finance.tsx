import React, { useEffect, useState } from 'react';
import CardComponent from '@/pages/finance/ui/CardComponent/CardComponent';
import StockComponent from '@/pages/finance/ui/StockComponent/StockComponent';
import SavingsComponent from '@/pages/finance/ui/SavingsComponent/SavingsComponent';
import { StockListUp } from '../model';
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
        // 테스트용
        console.log(data);
    }, [data]);

    return (
        <div className="container">
            <div className="container">
                <div className="contentCard">
                    <div className="contentCard__title">소비도 투자도 저축도 현명하게</div>
                </div>
                <div className="finance">
                    <div className="finance__header">금융 정보</div>
                    <div className="finance__content">
                        {data.map((stock) => (
                            <div key={stock.stockId} className="finance__item">
                                <div className="finance__item__companyName">{stock.stckIssuCmpyNm}</div>
                                <div className="finance__item__info">
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
