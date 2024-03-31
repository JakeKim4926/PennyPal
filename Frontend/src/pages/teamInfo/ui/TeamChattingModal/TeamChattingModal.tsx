import { useRef } from 'react';
import { CompatClient } from '@stomp/stompjs';
import { sendTeamChat } from '../../api/sendTeamChat';

type TeamChattingModalProps = {
    teamId: number;
    memberId: number;
    chatRoomId: number;
    client: React.MutableRefObject<CompatClient | undefined>;
};

export function TeamChattingModal({ teamId, memberId, chatRoomId, client }: TeamChattingModalProps) {
    const inputRef = useRef<HTMLInputElement>(null);

    return (
        <div className="teamChattingModal modalContainer">
            <div>채팅방인데요</div>
            <div>{teamId}</div>
            <div>{memberId}</div>
            <div>{chatRoomId}</div>
            <input ref={inputRef}></input>
            <button
                onClick={() => {
                    sendTeamChat(client, memberId, '하하하하', chatRoomId);
                }}
            >
                보내기
            </button>
        </div>
    );
}
