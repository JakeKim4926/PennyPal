import React, { useEffect, useState } from 'react';
import { MarketListItem } from '../MarketListItem/MarketListItem';
import { getMarketItemList } from '../../model/getMarketItemList';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '@/app/appProvider';
import { setMarketItemList } from '../../model';

type Product = {
    productBrand: string;
    productCategory: string;
    productId: number;
    productImg: string;
    productPrice: number;
    productQuantity: number;
    productName: string;
};

export function MarketList() {
    const dispatch = useDispatch();
    const productList: any = useSelector((state: RootState) => state.setMarketItemListReducer.data);

    useEffect(() => {
        getMarketItemList('', 0).then((res) => {
            if (res.status === 200) {
                dispatch(setMarketItemList(res.data.content));
            }
        });
    }, []);

    return (
        <div className="marketList">
            {productList &&
                productList.map((it: Product) => (
                    <MarketListItem
                        productBrand={it.productBrand}
                        productCategory={it.productCategory}
                        productQuantity={it.productQuantity}
                        productImg={it.productImg}
                        productName={it.productName}
                        productPrice={it.productPrice}
                        productId={it.productId}
                        key={it.productId}
                    />
                ))}
        </div>
    );
}
