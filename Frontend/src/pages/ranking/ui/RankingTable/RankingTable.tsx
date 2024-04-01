import { getTeamInfo } from '@/pages/teamRouting';
import { API_CACHE_DATA, getCookie } from '@/shared';
import { useEffect, useState } from 'react';
import { getRanking } from '../../api/getRanking';

type Ranking = {
    rank: number;
    name: string;
    score: number;
};
export function RankingTable() {
    const [ranking, setRanking] = useState<Ranking[]>([]);
    const [curPage, setCurpage] = useState(0);
    const memberId = getCookie('memberId');

    useEffect(() => {
        const REQUEST_URL = `/team/${memberId}`;

        const cacheData = API_CACHE_DATA.get(REQUEST_URL);

        if (!cacheData || cacheData.exp.getTime() < new Date().getTime()) {
            getTeamInfo(REQUEST_URL).then((res) => {
                if (res.data.code === 200) {
                    if (res.data.data.members.some((it: any) => it.memberId === memberId)) {
                        API_CACHE_DATA.set(REQUEST_URL, { data: res.data, exp: new Date().getTime() + 1000 * 60 * 10 });

                        getRanking(`/team/rank/weekly/${res.data.data.teamId}?page=${curPage}`).then((res) => {
                            setRanking(res.data.data.content[0].teamRanks);
                        });
                    }
                }
            });
        } else {
            const teamData = cacheData.get(REQUEST_URL);
            if (teamData.data.data.members.some((it: any) => it.memberId === memberId)) {
            } else {
            }
        }
    }, [curPage]);

    const myRanking = { id: 20, rank: 23, name: '팀이름', score: '85' };
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
                    {ranking.map((it) => (
                        <li className="rankingTable__content-list-item">
                            <div>{it.rank}</div>
                            <div>{it.name}</div>
                            <div>{it.score}</div>
                            <div>P 5,000</div>
                        </li>
                    ))}
                </ul>
            </div>
            <div className="rankingTable__mine">
                <div className="rankingTable__mine-rank">{myRanking.rank}</div>
                <div className="rankingTable__mine-name">{myRanking.name}</div>
                <div className="rankingTable__mine-score">{myRanking.score}</div>
                <div>보상 포인트</div>
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
