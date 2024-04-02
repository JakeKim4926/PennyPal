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
                <RankingTable />
                <button
                    onClick={async () => {
                        const re1 = await customAxios.post('/team/rankRealtime').catch((err) => err);
                        const re2 = await customAxios.post('/team/rank').catch((err) => err);

                        console.log(re1);
                        console.log(re2);
                    }}
                    style={{ backgroundColor: 'white' }}
                >
                    랭킹 산정 API
                </button>
            </div>
        </div>
    );
}
