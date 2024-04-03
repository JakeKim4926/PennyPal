import React from 'react';
import { getMarketItemByCategory } from '@/pages/market/api/getMarketItemByCategory';
import { useDispatch } from 'react-redux';
import { setMarketItemList } from '../../model';
import { getMarketItemList } from '../../model/getMarketItemList';

export function MarketCategory() {
    const dispatch = useDispatch();
    const categories = [
        {
            icon: '아이콘',
            title: '전체',
            category: '*',
        },
        {
            icon: '아이콘',
            title: '음료',
            category: '음료',
        },
        {
            icon: '아이콘',
            title: '과자',
            category: '과자',
        },
        {
            icon: '아이콘',
            title: '초콜릿',
            category: '초콜릿',
        },
    ];

    return (
        <div className="marketCategory">
            {categories.map((it) =>
                it.title === '전체' ? (
                    <button
                        className="marketCategory__item"
                        onClick={async () => {
                            const res = await getMarketItemList(0);
                            if (res.status === 200) {
                                dispatch(setMarketItemList(res.data.content));
                            }
                        }}
                    >
                        <div className="marketCategory__item-icon">{it.icon}</div>
                        <div className="marketCategory__item-title">{it.title}</div>
                    </button>
                ) : (
                    <button
                        className="marketCategory__item"
                        onClick={async () => {
                            const res = await getMarketItemByCategory(it.category);
                            if (res.status === 200) {
                                dispatch(setMarketItemList(res.data.content));
                            }
                        }}
                    >
                        <div className="marketCategory__item-icon">{it.icon}</div>
                        <div className="marketCategory__item-title">{it.title}</div>
                    </button>
                ),
            )}
        </div>
    );
}
