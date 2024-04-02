import { useDispatch } from 'react-redux';
import { closeMarketItemModal } from '../../model';
import { useEffect, useCallback } from 'react';
import { purchaseItem } from '../../api/purchaseItem';
import { getCookie } from '@/shared';
type Product = {
    productBrand: string;
    productCategory: string;
    productId: number;
    productImg: string;
    productPrice: number;
    productQuantity: number;
    productName: string;
};

export function MarketItemModal({
    productBrand,
    productCategory,
    productId,
    productImg,
    productPrice,
    productQuantity,
    productName,
}: Product) {
    const dispatch = useDispatch();

    useEffect(() => {
        function handleClick(e: MouseEvent) {
            e.stopPropagation();
            if (e.target instanceof Element) {
                if ([...e.target.classList].some((it) => it === 'modalContainer')) {
                    dispatch(closeMarketItemModal());
                }
            }
        }
        window.addEventListener('click', handleClick);

        return () => {
            window.removeEventListener('click', handleClick);
        };
    });

    return (
        <div className="modalContainer">
            <div className="marketItemModal contentCard">
                <div className="marketItemModal__top">
                    <div className="marketItemModal__top-image">
                        <img src={productImg}></img>
                    </div>
                </div>
                <div className="marketItemModal__middle">
                    <div className="marketItemModal__middle-left">
                        <div className="marketItemModal__middle-left-brand">{productBrand}</div>
                        <div className="marketItemModal__middle-left-name">{productName}</div>
                    </div>

                    <div className="marketItemModal__middle-right">[P] {productPrice.toLocaleString()}</div>
                </div>
                <div className="marketItemModal__bottom">
                    <div className="marketItemModal__bottom-type">유형 모바일교환권(기프티콘)</div>
                    <div className="marketItemModal__bottom-exp">유효기간 1개월</div>
                </div>
                <button
                    className="marketItemModal__button button"
                    onClick={async () => {
                        const dto = {
                            member: getCookie('memberId'),
                            product: productId,
                            buyQuantity: 1,
                        };
                        const res = await purchaseItem(dto).catch((err) => err);
                        console.log(res);
                    }}
                >
                    PURCHASE
                </button>
            </div>
        </div>
    );
}
