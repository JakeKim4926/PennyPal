import React, { useRef, useEffect, useState } from 'react';
import { ChartArea } from '@/shared';
import { useSelector } from 'react-redux';
import { RootState } from '@/app/appProvider';

type TeamData = {
    teamId?: number | null;
    chatRoomId?: number;
    teamName?: string;
    teamLeaderId?: number;
    teamInfo?: string;
    teamScore?: number;
    teamRankRealtime?: number;
    teamIsAutoConfirm?: boolean;
    teamLastTotalExpenses?: number;
    teamThisTotalExpenses?: number;
    teamLastEachTotalExpenses?: [
        {
            date: number[];
            totalAmount: number;
        },
    ];
    teamThisEachTotalExpenses?: [
        {
            date: number[];
            totalAmount: number;
        },
    ];
    members?: [
        {
            memberId: number;
            memberNickname: string;
            memberLastTotalExpenses: number;
            memberThisTotalExpenses: number;
        },
    ];
};

export function TeamInfoTeamExpenditure() {
    const teamData: TeamData | null = useSelector((state: RootState) => state.setTeamInfoReducer.data);

    // data: [0] -> 지난주 / [1] -> 이번주
    const [data, setData] = useState<number[][]>([[0], [0]]);

    function calDailySpend(history: [{ date: number[]; totalAmount: number }]) {}

    return (
        <div className="teamInfoTeamExpenditure contentCard">
            <div className="teamInfoTeamExpenditure__title contentCard__title">
                <div className="teamInfoTeamExpenditure__title-text contentCard__title-text">TEAM EXPENDITURE</div>
            </div>
            <ChartArea data={data} />
        </div>
    );
}
