import { Link } from 'react-router-dom';

export function MarketReceiptTop() {
    return (
        <div className="marketReceiptTop">
            <Link to="/market">
                <div className="marketReceiptTop__left">◀ 마켓으로 돌아가기</div>
            </Link>
            <div className="marketReceiptTop__right">
                <button className="marketReceiptTop__right-button button">[P] 포인트 모으러 가기 →</button>
            </div>
        </div>
    );
}
