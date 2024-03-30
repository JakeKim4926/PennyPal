import { customAxios } from '@/shared';

export async function deleteTeam(postDto: { teamId: number; memberId: number }) {
    await customAxios.delete(`/team`, {
        data: postDto,
    });
}
