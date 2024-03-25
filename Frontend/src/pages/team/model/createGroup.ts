import { customAxios } from '@/shared';

export async function createGroup(postDto: object) {
    return await customAxios.post('/api/team/create', postDto).catch((err) => err);
}
