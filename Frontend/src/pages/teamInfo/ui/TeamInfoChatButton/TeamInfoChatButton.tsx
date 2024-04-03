import { getCookie } from '@/shared';
import React, { useEffect, useRef, useState } from 'react';
import { useDispatch } from 'react-redux';
import { openTeamChattingModal } from '../../model/openTeamChattingModal';
import { CompatClient } from '@stomp/stompjs';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCommentDots } from '@fortawesome/free-regular-svg-icons';
import { useSelector } from 'react-redux';
import { RootState } from '@/app/appProvider';

type TeamInfoChatButton = {
    teamId: number;
    chatRoomId: number;
    client: React.MutableRefObject<CompatClient | undefined>;
};

export function TeamInfoChatButton({ teamId, chatRoomId, client }: TeamInfoChatButton) {
    const openTeamChattingModalState = useSelector((state: RootState) => state.openTeamChattingModalReducer.data);
    const dispatch = useDispatch();
    const [memberId, setMemberId] = useState<number>(0);
    const buttonRef = useRef<HTMLDivElement>(null);

    useEffect(() => {
        const cookieData = getCookie('memberId');

        if (typeof cookieData === 'number') {
            setMemberId(cookieData);
        }
    }, []);

    useEffect(() => {
        if (openTeamChattingModalState) {
            buttonRef.current!.classList.add('hiding');
        } else {
            buttonRef.current!.classList.remove('hiding');
        }
    }, [openTeamChattingModalState]);

    return (
        <div className="teamInfoChatButton floating" ref={buttonRef}>
            <button
                className="teamInfoChatButton__button"
                onClick={() => {
                    dispatch(openTeamChattingModal({ teamId, memberId, chatRoomId, client }));
                    buttonRef.current!.classList.add('hiding');
                }}
            >
                <FontAwesomeIcon icon={faCommentDots} />
            </button>
        </div>
    );
}
