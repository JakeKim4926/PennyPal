import React, { useEffect, useState } from 'react';
import CardComponent from '@/pages/finance/ui/CardComponent/CardComponent';
import StockComponent from '@/pages/finance/ui/StockComponent/StockComponent';
import SavingsComponent from '@/pages/finance/ui/SavingsComponent/SavingsComponent';

export function Finance() {
    const [data, setData] = useState({ cards: [], stocks: [], savings: [] });
    const fetchData = async () => {
        // 여기에 API 호출 로직 구현
        return {
            cards: [
                { id: 1, name: '카드1' },
                { id: 2, name: '카드2' },
            ],
            stocks: [
                { id: 1, name: '주식1' },
                { id: 2, name: '주식2' },
            ],
            savings: [
                { id: 1, name: '적금1' },
                { id: 2, name: '적금2' },
            ],
        };
    };
    useEffect(() => {
        fetchData().then();
    }, []);

    return (
        <div className="container">
            <div className="container">
                <div className="contentCard">
                    <div className="contentCard__tile">소비도 투자도 저축도 현명하게</div>
                </div>
                <div>금융</div>
                <div>
                    <CardComponent cards={data.cards} />
                    <StockComponent stocks={data.stocks} />
                    <SavingsComponent savings={data.savings} />
                </div>
            </div>
        </div>
    );
}
