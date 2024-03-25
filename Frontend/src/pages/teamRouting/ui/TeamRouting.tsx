import React from 'react';
import { TeamInfo } from '../../teamInfo/index';
import { Team } from '../../team/index';

export function TeamRouting() {
    const hasTeam = false;

    return <div className="teamRouting">{hasTeam ? <TeamInfo /> : <Team />}</div>;
}
