import { getTeamInfo } from '@/pages/teamRouting';
import { API_CACHE_DATA, customAxios, getCookie } from '@/shared';
import { useEffect, useState } from 'react';
import { getRanking } from '../../api/getRanking';
import { getRealtimeRanking } from '../../api/getRealtimeRanking';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFaceSadTear } from '@fortawesome/free-regular-svg-icons';

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
                // if (!res.data.data.members) {
                //     getRanking(`/team/rank/weekly/-1?page=${curPage}&size=5`).then((res) => {
                //         const data = res.data.data.content[0];
                //         setRanking(data.teamRanks);
                //         setMyRanking({
                //             myTeamName: '소속된 팀이 없습니다.',
                //             myTeamRankNum: '-',
                //             myTeamScore: '-',
                //             myTeamRewardPoint: '-',
                //         });
                //     });
                //     getRealtimeRanking(`/team/rank/realtime/${res.data.data.teamId}?page=${curPage}&size=5`).then(
                //         (res) => console.log('리얼타임 랭킹: ', res.data),
                //     );
                // } else if (res.data.data.members.some((it: any) => it.memberId === memberId)) {
                //     getRanking(`/team/rank/weekly/${res.data.data.teamId}?page=${curPage}&size=5`).then((res) => {
                //         const data = res.data.data.content[0];
                //         setRanking(data.teamRanks);
                //         setMyRanking({
                //             myTeamName: data.myTeamName,
                //             myTeamRankNum: data.myTeamRankNum,
                //             myTeamScore: data.myTeamScore,
                //             myTeamRewardPoint: data.myTeamRewardPoint,
                //         });
                //     });
                //     getRealtimeRanking(`/team/rank/realtime/${res.data.data.teamId}?page=${curPage}&size=5`).then(
                //         (res) => console.log('리얼타임 랭킹: ', res.data),
                //     );
                // }
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
                    {ranking.length > 0 ? (
                        ranking.map((it: any) => (
                            <li
                                className={`rankingTable__content-list-item ${it.rankNum === 1 ? 'first' : ''} ${
                                    it.rankNum === 2 ? 'second' : ''
                                }`}
                            >
                                <div>{it.rankNum === 0 ? '-' : it.rankNum}</div>
                                <div>{it.teamName}</div>
                                <div>{it.teamScore} 점</div>
                                <div>{it.rewardPoint}</div>
                            </li>
                        ))
                    ) : (
                        <>
                            <li className="rankingTable__content-list-none">
                                {/* <div>
                                    <FontAwesomeIcon
                                        icon={faFaceSadTear}
                                        className="rankingTable__content-list-none-icon"
                                    />
                                </div> */}
                                {/* <div>아직 랭킹 산정이 이루어진 팀이 없어요!</div> */}
                                <div className="loading"></div>
                                <div>랭킹 내역 불러오는 중...</div>
                            </li>
                        </>
                    )}
                </ul>
            </div>
            {myRanking?.myTeamScore === '-' ? (
                <div className="rankingTable__mine-noteam">소속된 팀이 없습니다</div>
            ) : (
                <div className="rankingTable__mine">
                    <>
                        <div className="rankingTable__mine-rank">
                            {myRanking && myRanking.myTeamRankNum === 0 ? '-' : myRanking?.myTeamRankNum}
                        </div>
                        <div className="rankingTable__mine-name">{myRanking && myRanking.myTeamName}</div>
                        <div className="rankingTable__mine-score">
                            {myRanking && myRanking.myTeamScore !== '-' ? `${myRanking.myTeamScore}점` : '-'}
                        </div>
                        <div className="rankingTable__mine-point">
                            {myRanking && myRanking.myTeamRewardPoint === null ? 0 : myRanking?.myTeamRewardPoint}
                        </div>
                    </>
                </div>
            )}
            <div className="rankingTable__pagenation">
                <div className="rankingTable__pagenation-box">
                    <div>1</div>
                </div>
            </div>
        </div>
    );
}
