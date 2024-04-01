import React from 'react';

// 카드 데이터 타입 정의
interface Stock {
    id: number;
    name: string;
}

interface StockComponentProps {
    stocks: Stock[];
}

const stockComponent: React.FC<StockComponentProps> = ({ stocks }) => {
    return (
        <div>
            <h2>카드</h2>
            {stocks.map((stock) => (
                <div key={stock.id}>{stock.name}</div>
            ))}
        </div>
    );
};

export default stockComponent;
