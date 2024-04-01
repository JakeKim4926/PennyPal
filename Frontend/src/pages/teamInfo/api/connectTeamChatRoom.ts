import { CompatClient, Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

type Message = {
    chatMessageId: number;
    memberNickname: string | null;
    message: string;
    createdAt: string;
};

export function connectTeamChatRoom(
    client: React.MutableRefObject<CompatClient | undefined>,
    chatRoomId: number,
    messageList: Message[],
    setMessageList: React.Dispatch<React.SetStateAction<Message[]>>,
) {
    const socket = new SockJS(`${process.env.REACT_APP_WS_URL}`);
    client.current = Stomp.over(socket);
    client.current.connect(
        {},
        () => {
            // 1. 연결 성공 시 콜백

            // 1-1. 채팅방 구독
            client.current?.subscribe(
                `/sub/chat/${chatRoomId}`,

                // 1-2. 메세지 수신 시 콜백 함수
                (message) => {
                    setMessageList((messageList) => {
                        const newMessage = JSON.parse(message.body);
                        return [...messageList, newMessage];
                    });
                },
            );
        },
        (error: Error) => {
            console.log('WebSocket 연결 에러: ', error);
        },
    );
}

// return () => {
//     // 컴포넌트가 언마운트될 때 클라이언트 연결 해제
//     if (client.current) {
//         client.current.disconnect();
//     }
// };
