import React from 'react';
import { getMarketItemByCategory } from '@/pages/market/api/getMarketItemByCategory';
import { useDispatch } from 'react-redux';
import { useState, useEffect } from 'react';
import { setMarketItemList } from '../../model';
import { getMarketItemList } from '../../model/getMarketItemList';

export function MarketCategory() {
    const dispatch = useDispatch();
    const [category, setCategory] = useState('');
    const categories = [
        {
            icon: '아이콘',
            title: '전체',
            category: '',
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

    useEffect(() => {
        if (category === '') {
            getMarketItemList(0).then((res) => {
                if (res.status === 200) {
                    dispatch(setMarketItemList(res.data.content));
                }
            });
        } else {
            getMarketItemByCategory(category).then((res) => {
                if (res.status === 200) {
                    dispatch(setMarketItemList(res.data.content));
                }
            });
        }
    }, [category]);

    return (
        <div className="marketCategory">
            {categories.map((it) => (
                <button
                    className={`marketCategory__item ${category === it.category ? 'bold' : ''}`}
                    onClick={() => setCategory(it.category)}
                >
                    <div className="marketCategory__item-icon">{it.icon}</div>
                    <div className="marketCategory__item-title">{it.title}</div>
                </button>
            ))}
        </div>
    );
}
