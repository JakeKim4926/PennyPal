import React from 'react';

type MarketTopProps = {
    point: number;
};

export function MarketTop({ point }: MarketTopProps) {
    return (
        <div className="marketTop">
            <div className="marketTop__point">
                <div className="marketTop__point-title">[아이콘] POINT</div>
                <div className="marketTop__point-value">{point.toLocaleString()}P</div>
            </div>
            <div className="marketTop__search">
                <input className="marketTop__search-input" placeholder="검색어를 입력해주세요."></input>
                <button className="marketTop__search-button">검색</button>
            </div>
            <div className="marketTop__record">
                <div className="marketTop__record-item">사용기록[아이콘]</div>
            </div>
        </div>
    );
}
