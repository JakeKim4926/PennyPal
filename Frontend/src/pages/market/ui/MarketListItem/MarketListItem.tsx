import React from 'react';
import { useDispatch } from 'react-redux';
import { openMarketItemModal } from '@/pages/market/model/index';

type MarketListItemProps = {
    image: string;
    title: string;
    point: number;
};

export function MarketListItem({ image, title, point }: MarketListItemProps) {
    const dispatch = useDispatch();

    return (
        <div className="marketList__item" onClick={() => dispatch(openMarketItemModal({ value: 123 }))}>
            <div className="marketList__item-image">{image}</div>
            <div className="marketList__item-desc">
                <div className="marketList__item-desc-title">{title}</div>
                <div className="marketList__item-desc-point">
                    <div>[P]{point.toLocaleString()}</div>
                    <div>[I]</div>
                </div>
            </div>
        </div>
    );
}
