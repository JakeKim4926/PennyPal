import React from 'react';

type TeamTeamListPagenationProps = {
    curPage: number;
    setCurPage: React.Dispatch<React.SetStateAction<number>>;
    totalTeamCnt: number;
};

export function TeamTeamListPagenation({ curPage, setCurPage, totalTeamCnt }: TeamTeamListPagenationProps) {
    const idxRange = [Math.floor(curPage / 5), Math.floor(curPage / 5) + 5];

    return (
        <div className="teamTeamListPagenation">
            <div className="teamTeamListPagenation__value">
                <button>{`<<`}</button>
                <button>{`<`}</button>
                <div className="teamTeamListPagenation__value-wrapper">
                    <button className="selected">1</button>
                    <button>2</button>
                    <button>3</button>
                    <button>4</button>
                    <button>5</button>
                </div>
                <button>{`>`}</button>
                <button>{`>>`}</button>
            </div>
        </div>
    );
}
