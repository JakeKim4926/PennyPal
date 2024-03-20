import React from 'react';

type PageHeaderProps = {
    page: string;
};

type Item = {
    title: string;
    description: string;
};

type Items = {
    [key: string]: Item;
};

export function PageHeader({ page }: PageHeaderProps) {
    const items: Items = {
        team: {
            title: 'TEAM',
            description: '함께할 팀을 찾아보아요.',
        },

        finance: {
            title: 'FINANCE',
            description: '금융',
        },

        expenditure: {
            title: 'SPENDING MANAGEMENT',
            description: '나의 지출 내역을 관리하고 분석합니다.',
        },

        ranking: {
            title: 'RANKING',
            description: '랭킹',
        },

        market: {
            title: 'MARKET',
            description: '마켓',
        },

        test: {
            title: 'TEST',
            description: '테스트 페이지입니다.',
        },
    };

    return (
        <div className="pageHeader">
            <div>
                <img src={`assets/image/${page}.svg`} width={150} />
            </div>
            <div className="pageHeader__title">{items[page].title}</div>
            <div className="pageHeader__description">{items[page].description}</div>
        </div>
    );
}
