import React from 'react';
import { getMarketItemByCategory } from '../../api/getMarketItemByCategory';

export function MarketCategory() {
    const categories = [
        {
            icon: '아이콘',
            title: '전체',
        },
        {
            icon: '아이콘',
            title: '음료',
        },
        {
            icon: '아이콘',
            title: '과자',
        },
        {
            icon: '아이콘',
            title: '초콜릿',
        },
    ];

    return (
        <div className="marketCategory">
            {categories.map((it) => (
                <button
                    className="marketCategory__item"
                    onClick={async () => {
                        const res = await getMarketItemByCategory(it.title);
                    }}
                >
                    <div className="marketCategory__item-icon">{it.icon}</div>
                    <div className="marketCategory__item-title">{it.title}</div>
                </button>
            ))}
        </div>
    );
}
