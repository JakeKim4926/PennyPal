import { useLocation } from 'react-router-dom';
import { PageHeader } from '@/shared';
import { TeamTeamSearch } from '@/pages/team/ui/TeamTeamSearch/TeamTeamSearch';
import { TeamCreateTeam } from '@/pages/team/ui/TeamCreateTeam/TeamCreateTeam';
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { closeTeamDetailModal } from '../model';

export function Team() {
    const page = useLocation();
    const dispatch = useDispatch();

    useEffect(() => {
        return () => {
            dispatch(closeTeamDetailModal());
        };
    });

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
