import { useCallback, useRef } from 'react';
import { CompatClient } from '@stomp/stompjs';
import { sendTeamChat } from '../../api/sendTeamChat';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPaperPlane } from '@fortawesome/free-regular-svg-icons';
import { faCircleXmark } from '@fortawesome/free-regular-svg-icons';

type TeamChattingModalProps = {
    teamId: number;
    memberId: number;
    chatRoomId: number;
    client: React.MutableRefObject<CompatClient | undefined>;
};

export function TeamChattingModal({ teamId, memberId, chatRoomId, client }: TeamChattingModalProps) {
    const inputRef = useRef<HTMLInputElement>(null);
    const handleSendMessage = useCallback(
        (client: React.MutableRefObject<CompatClient | undefined>, memberId: number, chatRoomId: number) => {
            if (inputRef.current!.value.trim().length > 0) {
                sendTeamChat(client, memberId, inputRef.current!.value, chatRoomId);
                inputRef.current!.value = '';
            }
        },
        [],
    );
    return (
        <div className="teamChattingModal">
            <div className="teamChattingModal__top">
                <div className="teamChattingModal__top-left">팀 채팅방</div>
                <button className="teamChattingModal__top-right">
                    <FontAwesomeIcon icon={faCircleXmark} style={{ color: '#FFFFFF' }} />
                </button>
            </div>
            <div className="teamChattingModal__middle"></div>
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
