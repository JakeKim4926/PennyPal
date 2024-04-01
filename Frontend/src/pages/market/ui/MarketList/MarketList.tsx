import React, { useEffect, useState } from 'react';
import { MarketListItem } from '../MarketListItem/MarketListItem';
import { getMarketItemList } from '../../model/getMarketItemList';

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
    const [productList, setProductList] = useState<Product[]>([]);

    useEffect(() => {
        getMarketItemList().then((res) => {
            if (res.status === 200) {
                setProductList(res.data.content);
                console.log(res.data.content);
            }
        });
    }, []);

    return (
        <div className="marketList">
            {productList &&
                productList.map((it) => (
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
