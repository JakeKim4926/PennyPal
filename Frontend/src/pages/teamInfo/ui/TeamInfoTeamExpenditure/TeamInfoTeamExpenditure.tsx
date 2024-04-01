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
            date: string;
            totalAmount: number;
        },
    ];
    teamThisEachTotalExpenses?: [
        {
            date: string;
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

    function calDailySpend(historyList: [{ date: string; totalAmount: number }]) {
        const arr = Array(7).fill(0);

        for (let history of historyList) {
            console.log(history);
            let dayOfWeekIdx = -1;

            const dateArr = history.date.split('-');

            dayOfWeekIdx = new Date(dateArr.join(',')).getDay();

            arr[dayOfWeekIdx] += history.totalAmount;
        }

        return arr;
    }

    useEffect(() => {
        const lastWeekSpend = calDailySpend(teamData?.teamLastEachTotalExpenses!);
        const thisWeekSpend = calDailySpend(teamData?.teamThisEachTotalExpenses!);
        setData([lastWeekSpend, thisWeekSpend]);
        console.log(lastWeekSpend, thisWeekSpend);
    }, []);

    return (
        <div className="teamInfoTeamExpenditure contentCard">
            <div className="teamInfoTeamExpenditure__title contentCard__title">
                <div className="teamInfoTeamExpenditure__title-text contentCard__title-text">TEAM EXPENDITURE</div>
            </div>
            <ChartArea data={data} />
        </div>
    );
}
