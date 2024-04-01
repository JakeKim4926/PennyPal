import { customAxios } from '@/shared';

export async function createGroup(postDto: object) {
    return await customAxios.post('/team/create', postDto).catch((err) => err);
}
