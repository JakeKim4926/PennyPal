import React, { useCallback, useEffect, useState } from 'react';
import { TeamTeamListItem } from '@/pages/team/ui/TeamTeamListItem/TeamTeamListItem';
import { TeamTeamListPagenation } from '@/pages/team/ui/TeamTeamListPagenation/TeamTeamListPagenation';
import { getTeamList } from '@/pages/team/index';
import { API_CACHE_DATA } from '@/shared'; // API 응답 데이터 캐싱에 사용할 MAP
// API_CACHE_DATA: 페이지 변할 때 마다 API를 계속해서 요청하는 것 방지하기 위함

type Team = {
    teamId: number;
    teamIsAutoConfirm: boolean;
    teamLeaderNickname: string;
    teamMembersNum: number;
    teamName: string;
    teamInfo: string;
};

type TeamTeamListProps = {
    searchedPage: number;
    keyword: string;
};

export const TeamTeamList = React.memo(({ searchedPage, keyword }: TeamTeamListProps) => {
    const [curPage, setCurPage] = useState<number>(searchedPage);
    const [maxPage, setMaxPage] = useState<number>(0);
    const [teamList, setTeamList] = useState<Team[]>([]);

    // fetchData: 팀 리스트 가져오기
    const fetchData = useCallback((url: string) => getTeamList(url), []);

    useEffect(() => {
        // REQUEST_URL: 요청 URL
        const REQUEST_URL = `/team?keyword=${keyword}&page=${curPage - 1}`;

        // cacheData: 캐시된 데이터
        const cacheData = API_CACHE_DATA.get(REQUEST_URL);

        // 1. 캐시 데이터가 없거나 만료된 데이터라면
        if (!cacheData || cacheData.exp.getTime() < new Date().getTime()) {
            // 1-1. API 요청하기
            fetchData(REQUEST_URL)
                .then((res) => {
                    // 1-2. 응답 데이터로 리렌더링
                    setMaxPage(res.data.data.totalPages);
                    setTeamList(res.data.data.content);

                    // 1-3. 응답 데이터 캐싱하기
                    API_CACHE_DATA.set(REQUEST_URL, {
                        data: res.data.data,
                        exp: new Date(new Date().getTime() + 1000 * 60 * 10),
                    });
                })
                .catch((err) => console.log(err));
        } else {
            // 2. 캐시 데이터가 있다면 캐시 데이터 사용하기
            setMaxPage(cacheData.data.totalPages);
            setTeamList(cacheData.data.content);
        }
    }, [curPage, keyword]);

    return (
        <>
            <div className="teamTeamList">
                {teamList.map((it) => (
                    <TeamTeamListItem
                        teamId={it.teamId}
                        name={it.teamName}
                        head={it.teamMembersNum}
                        leader={it.teamLeaderNickname}
                        description={it.teamInfo ?? '소개말이 없습니다.'}
                        key={it.teamId}
                    />
                ))}
            </div>
            <TeamTeamListPagenation curPage={curPage} setCurPage={setCurPage} maxPage={maxPage} />
        </>
    );
});
