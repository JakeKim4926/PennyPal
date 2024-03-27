import React from 'react';
import { MarketListItem } from '../MarketListItem/MarketListItem';

export function MarketList() {
    const props = [
        { id: 1, image: 'image', title: '상품1', point: 2500 },
        { id: 1, image: 'image', title: '상품1', point: 2500 },
        { id: 1, image: 'image', title: '상품1', point: 2500 },
        { id: 1, image: 'image', title: '상품1', point: 2500 },
        { id: 1, image: 'image', title: '상품1', point: 2500 },
        { id: 1, image: 'image', title: '상품1', point: 2500 },
        { id: 1, image: 'image', title: '상품1', point: 2500 },
        { id: 1, image: 'image', title: '상품1', point: 2500 },
    ];

    return (
        <div className="marketList">
            {props.map((it) => (
                <MarketListItem image={it.image} title={it.title} point={it.point} key={it.id} />
            ))}
        </div>
    );
}
