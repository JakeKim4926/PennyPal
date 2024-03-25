import React from 'react';

type MarketListItemProps = {
    image: string;
    title: string;
    point: number;
};

export function MarketListItem({ image, title, point }: MarketListItemProps) {
    return (
        <div className="marketListItem">
            <div>{image}</div>
            <div>{title}</div>
            <div>{point}</div>
        </div>
    );
}
