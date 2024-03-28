import { useLocation } from 'react-router-dom';
import { PageHeader } from '@/shared';
import { TeamTeamSearch } from '@/pages/team/ui/TeamTeamSearch/TeamTeamSearch';
import { TeamCreateTeam } from '@/pages/team/ui/TeamCreateTeam/TeamCreateTeam';
export function Team() {
    const page = useLocation();

    return (
        <div className="container team__container ">
            <div className="team">
                <PageHeader page={page.pathname.substring(1)} />
                <TeamCreateTeam />
                <TeamTeamSearch />
            </div>
        </div>
    );
}
