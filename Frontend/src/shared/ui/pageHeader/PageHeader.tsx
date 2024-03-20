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
            description: '소비도, 투자도, 저축도 현명하게.',
        },

        expenditure: {
            title: 'SPENDING MANAGEMENT',
            description: '나의 지출 내역을 관리하고 분석합니다.',
        },

        ranking: {
            title: 'RANKING',
            description: '열심히 달려보아요, 우리.',
        },

        market: {
            title: 'POINT MARKET',
            description: '개처럼 모았으니 정승같이 써보아요.',
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
