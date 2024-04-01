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
                    onClick={() => {
                        customAxios.post('/team/rank');
                        customAxios.post('/team/rankRealtime');
                    }}
                >
                    랭킹
                </button>
                <RankingTable />
            </div>
        </div>
    );
}
