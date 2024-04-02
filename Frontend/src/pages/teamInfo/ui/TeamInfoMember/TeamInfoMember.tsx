import React, { useEffect, useState } from 'react';
import { TeamInfoMemberItem } from '../TeamInfoMemberItem/TeamInfoMemberItem';

type TeamInfoMember = {
    teamLeaderId: number;
    teamMembers: Member[];
};

type Member = {
    memberNickname: string;
    teamLeaderId: number;
    memberThisTotalExpenses: number;
    memberId: number;
};

export function TeamInfoMember({ teamLeaderId, teamMembers }: TeamInfoMember) {
    return (
        <div className="contentCard teamInfoMember">
            <div className="contentCard__title teamInfoMember__title">
                <div className="contentCard__title-text teamInfoMember__title_text">MEMBERS</div>
            </div>
            <div className="teamInfoMember__itemList">
                {teamMembers &&
                    teamMembers.map((it) => (
                        <TeamInfoMemberItem
                            nickname={it.memberNickname}
                            isLeader={it.memberId === teamLeaderId}
                            spend={it.memberThisTotalExpenses}
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
