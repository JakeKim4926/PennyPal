import React from 'react';
import { TeamTeamList } from '@/pages/team/ui/TeamTeamList/TeamTeamList';

export function TeamTeamSearch() {
    return (
        <div className="teamTeamSearch contentCard">
            <div className="contentCard__title">
                <div className="contentCard__title-text">TEAM SEARCH</div>
            </div>
            <div className="teamTeamSearch__searchBar">
                <input className="teamTeamSearch__searchBar-input" type="text" />
                <button className="teamTeamSearch__searchBar-submit">검색</button>
            </div>
            <div className="teamTeamSearch__teamList">
                <TeamTeamList />
            </div>
        </div>
    );
}
