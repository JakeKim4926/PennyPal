import { USER_ID, customAxios } from '@/shared';
import { CompatClient, Stomp } from '@stomp/stompjs';
import { useEffect, useRef } from 'react';
import SockJS from 'sockjs-client';
import StompJS from '@stomp/stompjs';

export function Finance() {
    const client = useRef<CompatClient>();

    useEffect(() => {
        const connectHandler = () => {
            const socket = new SockJS('http://localhost:8080/');
            // client.current = Stomp.over(socket);
            // client.current!.connect(
            //     {},
            //     () => {
            //         // 연결 성공 시 실행될 콜백 함수
            //         client.current!.subscribe(
            //             '/sub/chat/22', // 수신
            //             (message) => {
            //                 // 메시지 수신 시 실행될 콜백 함수
            //                 alert(message.body);
            //             },
            //         );
            //     },
            //     (error: any) => {
            //         // 에러 처리
            //         console.error('WebSocket 연결 에러:', error);
            //     },
            // );
        };

        connectHandler(); // 컴포넌트가 마운트될 때 한 번 호출

        return () => {
            // 컴포넌트가 언마운트될 때 클라이언트 연결 해제
            if (client.current) {
                client.current.disconnect();
            }
        };
    }, []);

    function send() {
        const message = {
            senderId: USER_ID,
            content: '하하채팅',
        };
        client.current!.send('/pub/22', {}, JSON.stringify(message));
    }

    return (
        <div className="container">
            <div>websocket 연결 설정 중</div>
            <button
                onClick={async () => {
                    const a = await customAxios.get(`/chat/enter?chatRoomId=24&memberId=3859`);
                    console.log(a);
                }}
            >
                참여중인 채팅방 상세 조회 테스트
            </button>
            <button
                onClick={async () => {
                    send();
                }}
            >
                메세지 보내기
            </button>
            <button
                onClick={() => {
                    customAxios.post('/team/approve', {
                        teamId: 1783,
                        memberId: 3861,
                    });
                }}
            >
                테스트
            </button>
        </div>
    );
}
