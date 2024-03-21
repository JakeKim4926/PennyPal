import React from 'react';
import { TeamInfoMemberItem } from '../TeamInfoMemberItem/TeamInfoMemberItem';

export function TeamInfoMember() {
    const member = [
        { id: 1, nickname: '팀원1', isLeader: false, spend: 29300 },
        { id: 2, nickname: '팀원2', isLeader: true, spend: 293840 },
        { id: 3, nickname: '팀원3', isLeader: false, spend: 1000290 },
    ];
    return (
        <div className="contentCard">
            <div className="contentCard__title">
                <div className="contentCard__title-text">MEMBERS</div>
            </div>
            <div>
                {member.map((it) => (
                    <TeamInfoMemberItem nickname={it.nickname} isLeader={it.isLeader} spend={it.spend} key={it.id} />
                ))}
            </div>
        </div>
    );
}
