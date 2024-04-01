import React, { useCallback, useEffect, useState } from 'react';
import { TeamInfo } from '../../teamInfo/index';
import { Team } from '../../team/index';
import { useSelector } from 'react-redux';
import { RootState } from '@/app/appProvider';
import { getTeamInfo } from '../api/getTeamInfo';
import { API_CACHE_DATA, USER_ID, getCookie } from '@/shared';
import { useDispatch } from 'react-redux';
import { setTeamInfo } from '../model/setTeamInfo';
import { TeamRoutingInvitation } from './TeamRoutingInvitation.tsx/TeamRoutingInvitation';

interface TeamInfoData {
    teamId?: number;
    teamInfo?: string;
    teamLastEachTotalExpenses?: [];
    teamLastTotalExpenses?: number;
    teamLeaderId?: number;
    teamName?: string;
    teamRankRealtime?: number;
    teamScore?: number;
    teamThisEachTotalExpenses: [];
    teamThisTotalExpenses: number;
}

export function TeamRouting() {
    const teamInfo: any = useSelector((state: RootState) => state.setTeamInfoReducer.data);
    const dispatch = useDispatch();
    const memberId = getCookie('memberId');

    // fetchData: 해당 유저 팀 정보 가져오기
    const fetchData = useCallback((url: string) => getTeamInfo(`/team/${memberId}`), [memberId]);

    useEffect(() => {
        // REQUEST_URL: 요청 주소
        const REQUEST_URL = `/team/${memberId}`;

        // 1-1. API 요청하기
        fetchData(REQUEST_URL)
            .then((res) => {
                // 1-2. 응답 데이터로 리렌더링
                dispatch(setTeamInfo(res.data.data));
                // // 1-3. 응답 데이터 캐싱하기
                // API_CACHE_DATA.set(REQUEST_URL, {
                //     data: res.data.data,
                //     exp: new Date(new Date().getTime() + 1000 * 60),
                // });
            })
            .catch((err) => console.log(err));
    }, [memberId]);

    return (
        <div className="teamRouting">
            {
                // 1. 로그인 한 상태에서 아직 데이터 받아오기 전
                !teamInfo && (
                    <div className="container" style={{ backgroundColor: 'rgb(64, 64, 64)' }}>
                        로딩중
                    </div>
                )
            }
            {
                // 2. 로그인 후 팀이 있을 때
                teamInfo && teamInfo.teamId && <TeamInfo />
            }
            {
                // 3. 로그인 후 팀이 없을 때
                teamInfo && !teamInfo.teamId && <Team />
            }
        </div>
    );
}
