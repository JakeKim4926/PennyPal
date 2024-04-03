import { customAxios } from '@/shared';

export async function denyRegist(postDto: object) {
    return await customAxios.post('/team/reject', postDto).catch((err) => err);
}
