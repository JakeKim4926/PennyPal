import React from 'react';

// 카드 데이터 타입 정의
interface Saving {
    id: number;
    name: string;
}

interface SavingComponentProps {
    savings: Saving[];
}

const SavingComponent: React.FC<SavingComponentProps> = ({ savings }) => {
    return (
        <div>
            <h2>카드</h2>
            {savings.map((saving) => (
                <div key={saving.id}>{saving.name}</div>
            ))}
        </div>
    );
};

export default SavingComponent;
