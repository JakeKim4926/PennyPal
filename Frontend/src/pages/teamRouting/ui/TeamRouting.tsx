import React, { useCallback, useEffect, useState } from 'react';
import { TeamInfo } from '../../teamInfo/index';
import { Team } from '../../team/index';
import { useSelector } from 'react-redux';
import { RootState } from '@/app/appProvider';
import { getTeamInfo } from '../api/getTeamInfo';
import { API_CACHE_DATA } from '@/shared';
import { useDispatch } from 'react-redux';
import { setTeamInfo } from '../model/setTeamInfo';

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

    // 추후에 hasTeam 초기 값을 동적으로 설정해줄 수 있어야함 -> 팀 존재 여부에 따라
    // 여기 들어오면 가입한 팀 존재 여부 API 날린 다음 응답값에 따라 페이지 분기
    const memberId = 3859;

    // fetchData: 해당 유저 팀 정보 가져오기
    const fetchData = useCallback((url: string) => getTeamInfo(`/team/${memberId}`), [memberId]);

    useEffect(() => {
        // REQUEST_URL: 요청 주소
        const REQUEST_URL = `/team/${memberId}`;

        // // cacheData: 캐싱된 데이터
        // const cacheData = API_CACHE_DATA.get(REQUEST_URL);

        // // 1. 캐시 데이터가 없거나 만료된 데이터라면
        // if (!cacheData || cacheData.exp.getTime() < new Date().getTime()) {
        //     // 1-1. API 요청하기
        fetchData(REQUEST_URL)
            .then((res) => {
                // 1-2. 응답 데이터로 리렌더링
                dispatch(setTeamInfo(res.data.data));
                // // 1-3. 응답 데이터 캐싱하기
                API_CACHE_DATA.set(REQUEST_URL, {
                    data: res.data.data,
                    exp: new Date(new Date().getTime() + 1000 * 60),
                });
            })
            .catch((err) => console.log(err));
        // } else {
        //     // 2. 캐시 데이터가 있다면 캐시 데이터 사용하기
        //     dispatch(setTeamInfo(cacheData.data));
        // }
    }, []);

    return (
        <div className="teamRouting">
            {!teamInfo ? (
                <div className="container" style={{ backgroundColor: 'rgb(64, 64, 64)' }}>
                    로딩중
                </div>
            ) : teamInfo.teamId ? (
                <TeamInfo />
            ) : (
                <Team />
            )}
        </div>
    );
}
