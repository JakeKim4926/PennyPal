import React, { useEffect, useState } from 'react';
import { TeamTeamListItem } from '@/pages/team/ui/TeamTeamListItem/TeamTeamListItem';
import { TeamTeamListPagenation } from '@/pages/team/ui/TeamTeamListPagenation/TeamTeamListPagenation';

type Team = {
    id: number;
    name: string;
    head: number;
    leader: string;
    description: string;
};

export function TeamTeamList() {
    const [curPage, setCurPage] = useState(0);
    const [teamList, setTeamList] = useState<Team[]>([]);

    useEffect(() => {
        const tmp = [];
        for (let i = 0; i < 4; i++) {
            tmp.push({
                id: i,
                name: `team${i}`,
                head: i,
                leader: `leader${i}`,
                description: '우리팀은 이런 팀이다.',
            });
        }
        setTeamList(tmp);
    }, []);

    return (
        <>
            <div className="teamTeamList">
                {teamList.map((it) => (
                    <TeamTeamListItem
                        name={it.name}
                        head={it.head}
                        leader={it.leader}
                        description={it.description}
                        key={it.id}
                    />
                ))}
            </div>
            <TeamTeamListPagenation curPage={curPage} setCurPage={setCurPage} totalTeamCnt={8} />
        </>
    );
}
