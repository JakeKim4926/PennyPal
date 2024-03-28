import React, { useMemo, useState } from 'react';

type TeamTeamListPagenationProps = {
    curPage: number;
    setCurPage: React.Dispatch<React.SetStateAction<number>>;
    maxPage: number;
};

export function TeamTeamListPagenation({ curPage, setCurPage, maxPage }: TeamTeamListPagenationProps) {
    const idxRange = [Math.floor(curPage / 5), Math.floor(curPage / 5) + 5];

    const [page, setPage] = useState(1);
    const pageNumList = useMemo(() => {
        const tmp = [];
        for (let i = 1; i <= maxPage; i++) {
            tmp.push(i);
        }
        return tmp;
    }, [maxPage]);

    return (
        <div className="teamTeamListPagenation">
            <div className="teamTeamListPagenation__value">
                <button>{`<<`}</button>
                <button>{`<`}</button>
                <div className="teamTeamListPagenation__value-wrapper">
                    {pageNumList.map((it) => (
                        <button
                            key={it}
                            onClick={() => {
                                setCurPage(it);
                            }}
                            className={`${it === curPage ? 'selected' : ''}`}
                        >
                            {it}
                        </button>
                    ))}
                </div>
                <button>{`>`}</button>
                <button>{`>>`}</button>
            </div>
        </div>
    );
}
