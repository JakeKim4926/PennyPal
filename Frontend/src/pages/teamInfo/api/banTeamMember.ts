import { customAxios } from '@/shared';

export async function banTeamMember(postDto: object) {
    return await customAxios.post('/team/ban', postDto);
}
