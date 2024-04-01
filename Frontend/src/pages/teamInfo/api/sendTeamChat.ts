import { CompatClient } from '@stomp/stompjs';

export function sendTeamChat(
    client: React.MutableRefObject<CompatClient | undefined>,
    senderId: number,
    content: string,
    chatRoomId: number,
) {
    const message = { senderId, content };

    if (client.current) {
        client.current.send(`/pub/${chatRoomId}`, {}, JSON.stringify(message));
    }
}
