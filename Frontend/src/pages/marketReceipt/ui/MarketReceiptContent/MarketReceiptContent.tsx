export function MarketReceiptContent() {
    const recordList = [
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
        { date: '2024-03-22 22:22:22', name: '빙그레) 바나나맛 우유', quantity: 3, price: 5100 },
    ];
    return (
        <div className="marketReceiptContent">
            <div className="marketReceiptContent__top">
                <div className="marketReceiptContent__top-icon">[아이콘]</div>
                <div className="marketReceiptContent__top-desc">POINT MARKET</div>
            </div>
            <div className="marketReceiptContent__header">
                <div className="">===================================</div>
                <div className="marketReceiptContent__header-desc">
                    <div className="marketReceiptContent__header-desc-data">날짜</div>
                    <div className="marketReceiptContent__header-desc-name">상품명</div>
                    <div className="marketReceiptContent__header-desc-quantity">수량</div>
                    <div className="marketReceiptContent__header-desc-price">가격</div>
                </div>
                <div className="">===================================</div>
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
            <div className="marketReceiptContent__footer"></div>
            <div className="marketReceiptContent__pagenation"></div>
        </div>
    );
}
