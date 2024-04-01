import React, { useRef, useEffect, useState } from 'react';
import { ChartArea } from '@/shared';
import { useSelector } from 'react-redux';
import { RootState } from '@/app/appProvider';

export function TeamInfoTeamExpenditure() {
    const teamData: any = useSelector((state: RootState) => state.setTeamInfoReducer.data);

    // data: [0] -> 지난주 / [1] -> 이번주
    const [data, setData] = useState<number[][]>([[0], [0]]);

    return (
        <div className="teamInfoTeamExpenditure contentCard">
            <div className="teamInfoTeamExpenditure__title contentCard__title">
                <div className="teamInfoTeamExpenditure__title-text contentCard__title-text">TEAM EXPENDITURE</div>
            </div>
            <ChartArea data={data} />
        </div>
    );
}
