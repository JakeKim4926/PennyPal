import React, { useCallback, useEffect, useState } from 'react';
import { TeamTeamListItem } from '@/pages/team/ui/TeamTeamListItem/TeamTeamListItem';
import { TeamTeamListPagenation } from '@/pages/team/ui/TeamTeamListPagenation/TeamTeamListPagenation';
import { getTeamList } from '@/pages/team/index';

type Team = {
    teamId: number;
    teamIsAutoConfirm: boolean;
    teamLeaderNickname: string;
    teamMembersNum: number;
    teamName: string;
};

export function TeamTeamList() {
    const [curPage, setCurPage] = useState(0);
    const [teamList, setTeamList] = useState<Team[]>([]);

    // fetchData: 팀 리스트 가져오기
    // const fetchData = useCallback(async () => await getTeamList(), []);
    const fetchData = getTeamList;

    useEffect(() => {
        const data = fetchData().then((res) => {
            // setTeamList(res.content);
            setTeamList(res.data.data.content);
        });
    }, []);

    return (
        <>
            <div className="teamTeamList">
                {teamList.map((it) => (
                    <TeamTeamListItem
                        name={it.teamName}
                        head={it.teamMembersNum + 1}
                        leader={it.teamLeaderNickname}
                        description={'소개'}
                        key={it.teamId}
                    />
                ))}
            </div>
            <TeamTeamListPagenation curPage={curPage} setCurPage={setCurPage} totalTeamCnt={8} />
        </>
    );
}
