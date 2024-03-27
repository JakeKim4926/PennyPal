import React from 'react';
import { usePenny } from '../../../../shared';

type TeamInfoMemberItem = {
    isEmpty: boolean;
    nickname?: string;
    isLeader?: boolean;
    spend?: number;
};

export function TeamInfoMemberItem({ isEmpty, nickname, isLeader, spend }: TeamInfoMemberItem) {
    const penny = usePenny();

    return (
        <div className="teamInfoMemberItem">
            {!isEmpty ? (
                <>
                    <div className="teamInfoMemberItem__top">
                        <div className="teamInfoMemberItem__top-name">{nickname}</div>
                        {isLeader ? <div className="teamInfoMemberItem__top-leader"></div> : null}
                    </div>
                    <div className="teamInfoMemberItem__bottom">
                        <div className="teamInfoMemberItem__bottom-spend">{penny.toFullNumber(spend!)}</div>
                    </div>
                </>
            ) : (
                <div className="teamInfoMemberItem__middle">
                    <button className="teamInfoMemberItem__middle-button">팀원 초대하기</button>
                </div>
            )}
        </div>
    );
}
