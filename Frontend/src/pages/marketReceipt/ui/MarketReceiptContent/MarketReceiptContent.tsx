import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faStore } from '@fortawesome/free-solid-svg-icons';

export function MarketReceiptContent() {
    const recordList = [
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
    ];

    let sum = 0;
    recordList.forEach((it) => {
        sum += it.price;
    });

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
                    {recordList.map((it) => (
                        <li className="marketReceiptContent__main-list-item">
                            <div>{it.date}</div>
                            <div>{it.name}</div>
                            <div>{it.quantity}</div>
                            <div>{it.price}</div>
                        </li>
                    ))}
                </ul>
            </div>
            <div className="marketReceiptContent__footer">
                <div className="line">=====================================================================</div>
                <div className="marketReceiptContent__footer-total">
                    <div className="marketReceiptContent__footer-total-title">합계</div>
                    <div className="marketReceiptContent__footer-total-value">[P] {sum}</div>
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
