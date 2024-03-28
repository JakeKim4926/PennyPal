import { customAxios } from '@/shared';

export async function registGroup(postDto: object) {
    return await customAxios.post('/team/1', postDto).catch((err) => err);
}
