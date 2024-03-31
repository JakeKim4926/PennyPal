import { getCookie } from '@/shared';
import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { openTeamChattingModal } from '../../model/openTeamChattingModal';

type TeamInfoChatButton = {
    teamId: number;
    chatRoomId: number;
};

export function TeamInfoChatButton({ teamId, chatRoomId }: TeamInfoChatButton) {
    const dispatch = useDispatch();
    const [memberId, setMemberId] = useState<number>(0);

    useEffect(() => {
        const cookieData = getCookie('memberId');

        if (typeof cookieData === 'number') {
            setMemberId(cookieData);
        }
    });

    return (
        <div className="teamInfoChatButton">
            <button
                className="teamInfoChatButton__button"
                onClick={() => dispatch(openTeamChattingModal({ teamId, memberId, chatRoomId }))}
            >
                채팅창
            </button>
        </div>
    );
}
