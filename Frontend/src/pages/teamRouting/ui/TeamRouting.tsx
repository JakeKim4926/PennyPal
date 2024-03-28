import React, { useState } from 'react';
import { TeamInfo } from '../../teamInfo/index';
import { Team } from '../../team/index';
import { useSelector } from 'react-redux';
import { RootState } from '@/app/appProvider';

export function TeamRouting() {
    const hasTeam = useSelector((state: RootState) => state.setHasTeam.data);
    // 추후에 hasTeam 초기 값을 동적으로 설정해줄 수 있어야함 -> 팀 존재 여부에 따라

    return <div className="teamRouting">{hasTeam ? <TeamInfo /> : <Team />}</div>;
}
