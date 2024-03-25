import React from 'react';

type MarketListItemProps = {
    image: string;
    title: string;
    point: number;
};

export function MarketListItem({ image, title, point }: MarketListItemProps) {
    return (
        <div className="marketList__item">
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
