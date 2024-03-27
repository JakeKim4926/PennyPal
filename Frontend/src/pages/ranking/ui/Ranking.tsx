import { PageHeader } from '@/shared';
import { RankingTable } from './RankingTable/RankingTable';

export function Ranking() {
    return (
        <div className="ranking__container container">
            <div className="ranking">
                <PageHeader page={'ranking'} />
                <RankingTable />
            </div>
        </div>
    );
}
