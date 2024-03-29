import { customAxios } from '@/shared';

export async function checkTeamName(teamName: string) {
    return await customAxios.post(`/team/create/${teamName}`).catch((err) => err);
}
