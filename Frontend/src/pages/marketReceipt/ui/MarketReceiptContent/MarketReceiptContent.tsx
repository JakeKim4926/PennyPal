import { useEffect, useState } from 'react';
import { getPurchaseHistory } from '../../api/getPurchaseHistory';
import { getCookie } from '@/shared';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faStore } from '@fortawesome/free-solid-svg-icons';

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
                    {record.map((it) => (
                        <li className="marketReceiptContent__main-list-item">
                            <div>{it.orderDate}</div>
                            <div>{it.productName}</div>
                            <div>{it.buyQuantity}</div>
                            <div>{it.priceSum}</div>
                        </li>
                    ))}
                </ul>
            </div>
            <div className="marketReceiptContent__footer">
                <div className="line">=====================================================================</div>
                <div className="marketReceiptContent__footer-total">
                    <div className="marketReceiptContent__footer-total-title">합계</div>
                    <div className="marketReceiptContent__footer-total-value">
                        [P] {record.reduce((prev, cur) => prev + cur.priceSum, 0)}
                    </div>
                </div>
                <div className="line">=====================================================================</div>
            </div>
            <div className="marketReceiptContent__pagenation">
                <div className="marketReceiptContent__pagenation-box">
                    <div>1</div>
                    <div>2</div>
                    <div>3</div>
                    <div>4</div>
                </div>
            </div>
            <div className="marketReceiptContent__deco top"></div>
            <div className="marketReceiptContent__deco bottom"></div>
        </div>
    );
}
