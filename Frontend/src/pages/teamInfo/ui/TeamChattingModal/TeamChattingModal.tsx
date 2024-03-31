import { useCallback, useEffect, useRef, useState } from 'react';
import { CompatClient } from '@stomp/stompjs';
import { sendTeamChat } from '../../api/sendTeamChat';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPaperPlane } from '@fortawesome/free-regular-svg-icons';
import { faCircleXmark } from '@fortawesome/free-regular-svg-icons';
import { getTeamChatHistory } from '../../api/getTeamChatHistory';
import { getCookie } from '@/shared';

type TeamChattingModalProps = {
    teamId: number;
    memberId: number;
    chatRoomId: number;
    client: React.MutableRefObject<CompatClient | undefined>;
};

type Message = {
    chatMessageId: number;
    memberNickname: string | null;
    message: string;
    createdAt: string;
};

export function TeamChattingModal({ teamId, memberId, chatRoomId, client }: TeamChattingModalProps) {
    const inputRef = useRef<HTMLInputElement>(null);
    const [messageList, setMessageList] = useState<Message[]>([]);
    const [memberNickname, setMemberNickname] = useState<string>('');

    // handleSendMessage: 메세지 전송 및 인풋 비우기
    const handleSendMessage = useCallback(
        (client: React.MutableRefObject<CompatClient | undefined>, memberId: number, chatRoomId: number) => {
            if (inputRef.current!.value.trim().length > 0) {
                sendTeamChat(client, memberId, inputRef.current!.value, chatRoomId);
                inputRef.current!.value = '';
            }
        },
        [],
    );

    useEffect(() => {
        const cookieData = getCookie('memberNickname');
        if (typeof cookieData === 'string') {
            setMemberNickname(cookieData);
        }

        getTeamChatHistory(chatRoomId, memberId)
            .then((res) => {
                if (res.data.code === 200) {
                    setMessageList(res.data.data.messages);
                }
            })
            .catch((err) => console.log(err));
    }, []);

    return (
        <div className="teamChattingModal">
            <div className="teamChattingModal__top">
                <div className="teamChattingModal__top-left">팀 채팅방</div>
                <button className="teamChattingModal__top-right">
                    <FontAwesomeIcon icon={faCircleXmark} style={{ color: '#FFFFFF' }} />
                </button>
            </div>
            <div className="teamChattingModal__middle">
                {messageList.map((message: Message) => (
                    <MessageListItem message={message} memberNickname={memberNickname} />
                ))}
            </div>
            <div className="teamChattingModal__bottom">
                <input
                    ref={inputRef}
                    placeholder="메시지를 입력해주세요"
                    onKeyDown={(e) => {
                        if (e.key === 'Enter') {
                            handleSendMessage(client, memberId, chatRoomId);
                        }
                    }}
                ></input>
                <button
                    onClick={() => {
                        handleSendMessage(client, memberId, chatRoomId);
                    }}
                >
                    <FontAwesomeIcon icon={faPaperPlane} />
                </button>
            </div>
        </div>
    );
}

type MessageListItemProps = {
    message: Message;
    memberNickname: string;
};

function MessageListItem({ message, memberNickname }: MessageListItemProps) {
    console.log(memberNickname, message.memberNickname);
    if (memberNickname === message.memberNickname)
        return (
            <div className="messageListItem">
                <div className="messageListItem__nickname">{message.memberNickname}</div>
                <div className="messageListItem__message">{message.message}</div>
            </div>
        );
    else {
        return (
            <div className="messageListItemMine">
                <div className="messageListItemMine__nickname">{message.memberNickname}</div>
                <div className="messageListItemMine__message">{message.message}</div>
            </div>
        );
    }
}
