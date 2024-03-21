import React from 'react';

type TeamInfoMemberItem = {
    nickname: string;
    isLeader: boolean;
    spend: number;
};

export function TeamInfoMemberItem({ nickname, isLeader, spend }: TeamInfoMemberItem) {
    return (
        <div className="teamInfoMemberItem">
            <div className="teamInfoMemberItem__top">
                <div className="teamInfoMemberItem__top-name">{nickname}</div>
                {isLeader ? <div className="teamInfoMemberItem__top-leader"></div> : null}
            </div>
            <div className="teamInfoMemberItem__bottom">
                <div className="teamInfoMemberItem__bottom-spend">{spend.toLocaleString()}</div>
            </div>
        </div>
    );
}
