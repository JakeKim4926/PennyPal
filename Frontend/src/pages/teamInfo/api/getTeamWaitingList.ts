import { customAxios } from '@/shared';

export async function getTeamWaitingList(postDto: object) {
    return await customAxios.post(`/team/waitingList`, postDto).catch((err) => err);
}
