import React from 'react';

// 카드 데이터 타입 정의
interface Card {
    id: number;
    name: string;
}

interface CardComponentProps {
    cards: Card[];
}

const CardComponent: React.FC<CardComponentProps> = ({ cards }) => {
    return (
        <div>
            <h2>카드</h2>
            {cards.map((card) => (
                <div key={card.id}>{card.name}</div>
            ))}
        </div>
    );
};

export default CardComponent;
