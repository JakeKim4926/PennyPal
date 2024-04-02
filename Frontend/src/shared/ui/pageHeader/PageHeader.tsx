import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMoneyBill1Wave, faPeopleGroup, faRankingStar, faStore, faReceipt } from '@fortawesome/free-solid-svg-icons';

type PageHeaderProps = {
    page: string;
};

type Item = {
    title: string;
    description: string;
    icon: typeof faPeopleGroup;
};

type Items = {
    [key: string]: Item;
};

export function PageHeader({ page }: PageHeaderProps) {
    const items: Items = {
        team: {
            title: 'TEAM',
            description: '함께할 팀을 찾아보아요.',
            icon: faPeopleGroup,
        },

        teamInfo: {
            title: 'TEAM',
            description: '내가 소속된 팀의 정보입니다.',
            icon: faPeopleGroup,
        },

        finance: {
            title: 'FINANCE',
            description: '소비도, 투자도, 저축도 현명하게.',
            icon: faMoneyBill1Wave,
        },

        expenditure: {
            title: 'SPENDING MANAGEMENT',
            description: '나의 지출 내역을 관리하고 분석합니다.',
            icon: faMoneyBill1Wave,
        },

        ranking: {
            title: 'RANKING',
            description: '열심히 달려보아요, 우리.',
            icon: faRankingStar,
        },

        market: {
            title: 'POINT MARKET',
            description: '개처럼 모았으니 정승같이 써보아요.',
            icon: faStore,
        },

        test: {
            title: 'TEST',
            description: '테스트 페이지입니다.',
            icon: faStore,
        },
        receipt: {
            title: 'RECEIPT',
            description: '포인트 사용 내역입니다.',
            icon: faReceipt,
        },
    };

    return (
        <div className={`pageHeader ${page === 'ranking' ? 'ranking' : ''}`}>
            <FontAwesomeIcon className="pageHeader__icon" icon={items[page].icon} />
            <div className="pageHeader__title">{[...items[page].title]}</div>
            <div className="pageHeader__description">{items[page].description}</div>
        </div>
    );
}
