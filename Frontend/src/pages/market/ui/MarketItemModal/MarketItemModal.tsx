import { useDispatch } from 'react-redux';
import { closeMarketItemModal } from '../../model';
import { useEffect, useCallback } from 'react';
import { purchaseItem } from '../../api/purchaseItem';
import { getCookie } from '@/shared';
import Swal from 'sweetalert2';

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
                        <img src={productImg} height={300}></img>
                    </div>
                </div>
                <div className="marketItemModal__middle">
                    <div className="marketItemModal__middle-left">
                        <div className="marketItemModal__middle-left-brand">{productBrand}</div>
                        <div className="marketItemModal__middle-left-name">{productName}</div>
                    </div>

                    <div className="marketItemModal__middle-right">
                        <img src={'assets/image/point.svg'} height={25} />
                        <div> {productPrice.toLocaleString()}</div>
                    </div>
                </div>
                <div className="marketItemModal__bottom">
                    <div className="marketItemModal__bottom-type">
                        <img src={'assets/image/barcode.svg'} />
                        <div>유형 모바일교환권(기프티콘)</div>
                    </div>
                    <div className="marketItemModal__bottom-exp">
                        <img src={'assets/image/calander.svg'} />
                        <div>유효기간 1개월</div>
                    </div>
                </div>
                <button
                    className="marketItemModal__button button"
                    onClick={async () => {
                        const dto = {
                            memberId: getCookie('memberId'),
                            productId: productId,
                            buyQuantity: 1,
                        };
                        const res = await purchaseItem(dto).catch((err) => err);
                        if (res.status === 201) {
                            Swal.fire({
                                title: '상품 구매 완료',
                                text: `상품을 구매했습니다.`,
                                icon: `success`,
                            });
                        }
                    }}
                >
                    PURCHASE
                </button>
            </div>
        </div>
    );
}
