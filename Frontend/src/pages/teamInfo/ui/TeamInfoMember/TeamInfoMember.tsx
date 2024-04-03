import React, { useEffect, useState } from 'react';
import { TeamInfoMemberItem } from '../TeamInfoMemberItem/TeamInfoMemberItem';

type TeamInfoMember = {
    teamLeaderId: number;
    teamMembers: [];
};

type Member = {
    memberNickname: string;
    teamLeaderId: number;
    memberThisTotalExpenses: number;
    memberId: number;
    // id: number;
};

export function TeamInfoMember({ teamLeaderId, teamMembers }: TeamInfoMember) {
    const [memberList, setMemberList] = useState<Member[]>([]);

    useEffect(() => {
        let members = Array(6).fill({
            memberId: -1,
            memberNickname: '',
            memberLastTotalExpenses: -1,
            memberThisTotalExpenses: -1,
        });

        for (let i = 0; i < teamMembers.length; i++) {
            members[i] = teamMembers[i];
        }

        setMemberList([...members]);
    }, [teamLeaderId, teamMembers]);

    return (
        <div className="contentCard teamInfoMember">
            <div className="contentCard__title teamInfoMember__title">
                <div className="contentCard__title-text teamInfoMember__title_text">MEMBERS</div>
            </div>
            <div className="teamInfoMember__itemList">
                {memberList &&
                    memberList.map((it) => (
                        <TeamInfoMemberItem
                            nickname={it.memberNickname}
                            isLeader={it.memberId === teamLeaderId}
                            spend={it.memberThisTotalExpenses}
                            isEmpty={it.memberId === -1}
                            key={it.memberId}
                        />
                    ))}
            </div>
            <div className="teamInfoMember__invite">
                <button className="teamInfoMember__invite-button">팀원 초대 링크 공유하기</button>
            </div>
        </div>
    );
}
