import { useLocation } from 'react-router-dom';
import { PageHeader } from '../../../shared';
import { TeamTeamInfo } from './TeamTeamInfo/TeamTeamInfo';

export function Team() {
    const page = useLocation();

    return (
        <div className="container team__container ">
            <div className="team">
                <PageHeader page={page.pathname.substring(1)} />
                <TeamTeamInfo hasTeam={true} />
            </div>
        </div>
    );
}
