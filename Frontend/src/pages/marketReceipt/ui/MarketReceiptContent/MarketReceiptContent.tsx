import { useEffect, useState } from 'react';
import { getPurchaseHistory } from '../../api/getPurchaseHistory';
import { getCookie } from '@/shared';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faStore } from '@fortawesome/free-solid-svg-icons';
import { CgSmileNone } from 'react-icons/cg';

type Record = {
    orderId: number;
    productId: number;
    productName: string;
    buyQuantity: number;
    priceSum: number;
    orderDate: string;
};

export function MarketReceiptContent() {
    const [record, setRecord] = useState<Record[]>([]);
    const memberId = getCookie('memberId');

    useEffect(() => {
        if (typeof memberId === 'number') {
            getPurchaseHistory(memberId)
                .catch((err) => err)
                .then((res) => {
                    if (res.status === 200) {
                        console.log(res.data.content);
                        setRecord(res.data.content);
                    }
                });
        }
    }, []);

    return (
        <div className="marketReceiptContent">
            <div className="marketReceiptContent__top">
                <FontAwesomeIcon className="marketReceiptContent__top-icon" icon={faStore} />
                <div className="marketReceiptContent__top-desc">POINT MARKET</div>
            </div>
            <div className="marketReceiptContent__header">
                <div className="line">=====================================================================</div>
                <div className="marketReceiptContent__header-desc">
                    <div className="marketReceiptContent__header-desc-data">날짜</div>
                    <div className="marketReceiptContent__header-desc-name">상품명</div>
                    <div className="marketReceiptContent__header-desc-quantity">수량</div>
                    <div className="marketReceiptContent__header-desc-price">가격</div>
                </div>
                <div className="line">=====================================================================</div>
            </div>
            <div className="marketReceiptContent__main">
                <ul className="marketReceiptContent__main-list">
                    {record.length > 0 ? (
                        record.map((it) => (
                            <li className="marketReceiptContent__main-list-item">
                                <div>{it.orderDate}</div>
                                <div>{it.productName}</div>
                                <div>{it.buyQuantity}</div>
                                <div>{it.priceSum}</div>
                            </li>
                        ))
                    ) : (
                        <div className="marketReceiptContent__main-list-none">
                            <div className="marketReceiptContent__main-list-none-icon">
                                <CgSmileNone />
                            </div>
                            <div className="marketReceiptContent__main-list-none-text">아직 구매 내역이 없습니다.</div>
                        </div>
                    )}
                </ul>
            </div>
            <div className="marketReceiptContent__footer">
                <div className="line">=====================================================================</div>
                <div className="marketReceiptContent__footer-total">
                    <div className="marketReceiptContent__footer-total-title">합계</div>
                    <div className="marketReceiptContent__footer-total-value">
                        <img src="assets/image/point.svg" height={20} />
                        <div>{record.reduce((prev, cur) => prev + cur.priceSum, 0).toLocaleString()}</div>
                    </div>
                </div>
                <div className="line">=====================================================================</div>
            </div>
            <div className="marketReceiptContent__pagenation">
                <div className="marketReceiptContent__pagenation-box"></div>
            </div>
            <div className="marketReceiptContent__deco top"></div>
            <div className="marketReceiptContent__deco bottom"></div>
        </div>
    );
}
