import { getTeamInfo } from '@/pages/teamRouting';
import { API_CACHE_DATA, customAxios, getCookie } from '@/shared';
import { useEffect, useState } from 'react';
import { getRanking } from '../../api/getRanking';
import { getRealtimeRanking } from '../../api/getRealtimeRanking';

type Ranking = {
    rank: number;
    name: string;
    score: number;
};

type MyRanking = {
    myTeamName: string;
    myTeamRankNum: number | string;
    myTeamScore: number | string;
    myTeamRewardPoint?: number | string;
};
export function RankingTable() {
    const [ranking, setRanking] = useState<Ranking[]>([]);
    const [myRanking, setMyRanking] = useState<MyRanking>();
    const [curPage, setCurpage] = useState(0);
    const memberId = getCookie('memberId');

    useEffect(() => {
        const REQUEST_URL = `/team/${memberId}`;

        getTeamInfo(REQUEST_URL).then((res) => {
            if (res.data.code === 200) {
                if (!res.data.data.members) {
                    getRanking(`/team/rank/weekly/-1?page=${curPage}&size=5`).then((res) => {
                        const data = res.data.data.content[0];
                        setRanking(data.teamRanks);

                        setMyRanking({
                            myTeamName: '소속된 팀이 없습니다.',
                            myTeamRankNum: '-',
                            myTeamScore: '-',
                            myTeamRewardPoint: '-',
                        });
                    });
                } else if (res.data.data.members.some((it: any) => it.memberId === memberId)) {
                    getRanking(`/team/rank/weekly/${res.data.data.teamId}?page=${curPage}&size=5`).then((res) => {
                        const data = res.data.data.content[0];
                        setRanking(data.teamRanks);

                        setMyRanking({
                            myTeamName: data.myTeamName,
                            myTeamRankNum: data.myTeamRankNum,
                            myTeamScore: data.myTeamScore,
                            myTeamRewardPoint: data.myTeamRewardPoint,
                        });
                    });

                    // getRealtimeRanking(`/team/rank/realtime/${res.data.data.teamId}?page=${curPage}&size=5`).then(
                    //     (res) => console.log(res.data),
                    // );
                }
            }
        });
        // } else {
        //     const teamData = cacheData.get(REQUEST_URL);
        //     if (teamData.data.data.members.some((it: any) => it.memberId === memberId)) {
        //         getRanking(`/team/rank/weekly/${teamData.data.data.teamId}?page=${curPage}&size=5`).then((res) => {
        //             const data = res.data.data;
        //             setRanking(data.content[0].teamRanks);
        //             setMyRanking({
        //                 myTeamName: data.myTeamName,
        //                 myTeamRankNum: data.myTeamRankNum,
        //                 myTeamScore: data.myTeamScore,
        //                 myTeamRewardPoint: data.myTeamRewardPoint,
        //             });
        //         });
        //     } else {
        //     }
        // }
    }, [curPage]);

    return (
        <div className="rankingTable">
            <div className="rankingTable__header">
                <div>순위</div>
                <div>팀명</div>
                <div>점수</div>
                <div>보상 포인트</div>
            </div>
            <div className="rankingTable__content">
                <ul className="rankingTable__content-list">
                    {ranking.map((it: any) => (
                        <li className="rankingTable__content-list-item">
                            <div>{it.rankNum === 0 ? '-' : it.rankNum}</div>
                            <div>{it.teamName}</div>
                            <div>{it.teamScore}</div>
                            <div>{it.rewardPoint}</div>
                        </li>
                    ))}
                </ul>
            </div>
            <div className="rankingTable__mine">
                <div className="rankingTable__mine-rank">
                    {myRanking && myRanking.myTeamRankNum === 0 ? '-' : myRanking?.myTeamRankNum}
                </div>
                <div className="rankingTable__mine-name">{myRanking && myRanking.myTeamName}</div>
                <div className="rankingTable__mine-score">{myRanking && myRanking.myTeamScore + '점'}</div>
                <div className="rankingTable__mine-point">
                    {myRanking && myRanking.myTeamRewardPoint === null ? 0 : myRanking?.myTeamRewardPoint}
                </div>
            </div>
            <div className="rankingTable__pagenation">
                <div className="rankingTable__pagenation-box">
                    <div>1</div>
                    <div>2</div>
                    <div>3</div>
                    <div>4</div>
                    <div>5</div>
                </div>
            </div>
        </div>
    );
}
