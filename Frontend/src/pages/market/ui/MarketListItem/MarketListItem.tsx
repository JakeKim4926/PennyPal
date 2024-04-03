import React from 'react';
import { useDispatch } from 'react-redux';
import { openMarketItemModal } from '@/pages/market/model/index';

type Product = {
    productBrand: string;
    productCategory: string;
    productId: number;
    productImg: string;
    productPrice: number;
    productQuantity: number;
    productName: string;
};

export function MarketListItem({
    productBrand,
    productCategory,
    productId,
    productImg,
    productPrice,
    productQuantity,
    productName,
}: Product) {
    const dispatch = useDispatch();

    return (
        <div
            className="marketList__item"
            onClick={() =>
                dispatch(
                    openMarketItemModal({
                        productBrand,
                        productCategory,
                        productId,
                        productImg,
                        productPrice,
                        productQuantity,
                        productName,
                    }),
                )
            }
        >
            <div className="marketList__item-image">
                <img src={productImg} />
            </div>
            <div className="marketList__item-desc">
                <div className="marketList__item-desc-title">{productName}</div>
                <div className="marketList__item-desc-point">
                    <div>[P]{productPrice.toLocaleString()}</div>
                    <div>[I]</div>
                </div>
            </div>
        </div>
    );
}
