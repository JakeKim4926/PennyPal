import { PageHeader, customAxios, getCookie } from '@/shared';
import { RankingTable } from './RankingTable/RankingTable';
import { API_CACHE_DATA } from '@/shared';
import { useEffect } from 'react';
import { getTeamInfo } from '@/pages/teamRouting';

export function Ranking() {
    return (
        <div className="ranking__container container">
            <div className="ranking">
                <PageHeader page={'ranking'} />
                <button
                    onClick={async () => {
                        const res1 = await customAxios.post('/team/rank').catch((err) => err);
                        const res2 = await customAxios.post('/team/rankRealtime').catch((err) => err);

                        console.log(res1);
                        console.log(res2);
                    }}
                >
                    랭킹
                </button>
                <RankingTable />
            </div>
        </div>
    );
}
