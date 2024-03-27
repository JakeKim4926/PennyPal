import React, { useRef, useEffect } from 'react';
import { ChartArea } from '@/shared';

export function TeamInfoTeamExpenditure() {
    const data = [
        [100000, 150000, 170000, 180000, 100000, 2000, 90000],
        [50000, 100000, 50000, 50000, 50000, 40000, 60000],
    ];

    return (
        <div className="teamInfoTeamExpenditure contentCard">
            <div className="teamInfoTeamExpenditure__title contentCard__title">
                <div className="teamInfoTeamExpenditure__title-text contentCard__title-text">TEAM EXPENDITURE</div>
            </div>
            <ChartArea data={data} />
        </div>
    );
}
