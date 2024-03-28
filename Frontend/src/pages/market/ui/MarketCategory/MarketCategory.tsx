import React from 'react';

export function MarketCategory() {
    const categories = [
        {
            id: 0,
            icon: '아이콘',
            title: '전체',
        },
        {
            id: 1,
            icon: '아이콘',
            title: '음료',
        },
        {
            id: 2,
            icon: '아이콘',
            title: '과자',
        },
        {
            id: 3,
            icon: '아이콘',
            title: '초콜릿',
        },
        {
            id: 4,
            icon: '아이콘',
            title: '젤리',
        },
        {
            id: 5,
            icon: '아이콘',
            title: '사탕',
        },
        {
            id: 6,
            icon: '아이콘',
            title: '기타',
        },
    ];

    return (
        <div className="marketCategory">
            {categories.map((it) => (
                <button className="marketCategory__item" key={it.id}>
                    <div className="marketCategory__item-icon">{it.icon}</div>
                    <div className="marketCategory__item-title">{it.title}</div>
                </button>
            ))}
        </div>
    );
}
