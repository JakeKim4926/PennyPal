import { customAxios } from '@/shared';

export async function getTeamDetail(teamId: number) {
    return await customAxios.get(`/team/detail/${teamId}`).catch((err) => err);
}
