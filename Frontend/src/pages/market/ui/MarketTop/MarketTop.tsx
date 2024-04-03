import React from 'react';
import { Link } from 'react-router-dom';
import { setMarketItemList } from '../../model';
import { useRef } from 'react';
import { useDispatch } from 'react-redux';
import { searchMarketItemList } from '../../api/searchMarketItemList';

type MarketTopProps = {
    point: number;
};

export function MarketTop({ point }: MarketTopProps) {
    const inputRef = useRef<HTMLInputElement>(null);
    const dispatch = useDispatch();
    return (
        <div className="marketTop">
            <div className="marketTop__point">
                <div className="marketTop__point-title">
                    <img src={'assets/image/point.svg'} width={25} />
                    <div> POINT</div>
                </div>
                <div className="marketTop__point-value">{point.toLocaleString()}P</div>
            </div>
            <div className="marketTop__search">
                <input
                    className="marketTop__search-input"
                    placeholder="검색어를 입력해주세요."
                    ref={inputRef}
                    onKeyDown={async (e) => {
                        if (e.key === 'Enter') {
                            const res = await searchMarketItemList(inputRef.current!.value);
                            if (res.data.code === 200) {
                                dispatch(setMarketItemList(res.data.content));
                            }
                        }
                    }}
                ></input>
                <button
                    className="marketTop__search-button"
                    onClick={async () => {
                        console.log(inputRef.current?.value);
                        const res = await searchMarketItemList(inputRef.current!.value);
                        console.log(res.data.content);
                        dispatch(setMarketItemList(res.data.content));
                    }}
                >
                    검색
                </button>
            </div>
            <div className="marketTop__record">
                <Link to="/receipt">
                    <button className="marketTop__record-item button">사용 내역[I]</button>
                </Link>
            </div>
        </div>
    );
}
