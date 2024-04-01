import { customAxios } from '@/shared';

export async function modifyTeamInfo(patchDto: object, teamId: number) {
    return await customAxios.patch(`/team/${teamId}`).catch((err) => err);
}
