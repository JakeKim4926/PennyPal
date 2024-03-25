import React from 'react';

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
        {
            icon: '아이콘',
            title: '젤리',
        },
        {
            icon: '아이콘',
            title: '사탕',
        },
        {
            icon: '아이콘',
            title: '기타',
        },
    ];

    return (
        <div className="marketCategory">
            {categories.map((it) => (
                <div className="marketCategory__item">
                    <div className="marketCategory__item-icon">{it.icon}</div>
                    <div className="marketCategory__item-title">{it.title}</div>
                </div>
            ))}
        </div>
    );
}
