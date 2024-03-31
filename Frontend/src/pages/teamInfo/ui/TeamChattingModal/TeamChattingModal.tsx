import { useEffect, useRef } from 'react';
import { connectTeamChatRoom } from '../../api/connectTeamChatRoom';
import { CompatClient } from '@stomp/stompjs';
import { sendTeamChat } from '../../api/sendTeamChat';

type TeamChattingModalProps = {
    teamId: number;
    memberId: number;
    chatRoomId: number;
};

export function TeamChattingModal({ teamId, memberId, chatRoomId }: TeamChattingModalProps) {
    const client = useRef<CompatClient>();
    const inputRef = useRef<HTMLInputElement>(null);

    useEffect(() => {
        connectTeamChatRoom(client, chatRoomId);

        return () => {
            if (client.current) {
                client.current.disconnect();
            }
        };
    });

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
