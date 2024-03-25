import React from 'react';
import { MarketTop } from '../MarketTop/MarketTop';

export function MarketList() {
    return (
        <div className="marketList">
            <MarketTop point={1230} />
            <Category />
            <div className="marketList__itemList"></div>
        </div>
    );
}

function Category() {
    return (
        <div className="marketList__category">
            <div className="marketList__category-menu">
                <div>전체</div>
                <div>음료</div>
                <div>과자</div>
                <div>초콜릿</div>
                <div>젤리</div>
                <div>사탕</div>
                <div>기타</div>
            </div>
        </div>
    );
}
