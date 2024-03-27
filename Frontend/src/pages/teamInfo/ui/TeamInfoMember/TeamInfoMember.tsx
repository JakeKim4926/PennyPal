import React from 'react';
import { TeamInfoMemberItem } from '../TeamInfoMemberItem/TeamInfoMemberItem';

export function TeamInfoMember() {
    let member = Array(6).fill({ isEmpty: true });

    const response = [
        { id: 1, nickname: '팀원1', isLeader: true, spend: 29300, isEmpty: false },
        { id: 2, nickname: '팀원2', isLeader: false, spend: 293840, isEmpty: false },
        { id: 3, nickname: '팀원3', isLeader: false, spend: 1000290, isEmpty: false },
        { id: 4, nickname: '팀원4', isLeader: false, spend: 1000290, isEmpty: false },
        // { id: 5, nickname: '팀원5', isLeader: false, spend: 1000290, isEmpty: false },
    ];

    for (let i = 0; i < response.length; i++) {
        member[i] = response[i];
    }

    return (
        <div className="contentCard teamInfoMember">
            <div className="contentCard__title teamInfoMember__title">
                <div className="contentCard__title-text teamInfoMember__title_text">MEMBERS</div>
            </div>
            <div className="teamInfoMember__itemList">
                {member.map((it) => (
                    <TeamInfoMemberItem
                        nickname={it.nickname}
                        isLeader={it.isLeader}
                        spend={it.spend}
                        isEmpty={it.isEmpty}
                        key={it.id}
                    />
                ))}
            </div>
        </div>
    );
}
