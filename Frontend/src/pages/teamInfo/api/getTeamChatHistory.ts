import { customAxios } from '@/shared';

export async function getTeamChatHistory(chatRoomId: number, memberId: number) {
    return await customAxios.get(`/chat/enter`, {
        params: {
            chatRoomId,
            memberId,
        },
    });
}
