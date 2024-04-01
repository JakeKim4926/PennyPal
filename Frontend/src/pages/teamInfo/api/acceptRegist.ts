import { customAxios } from '@/shared';

export async function acceptRegist(postDto: Object) {
    return await customAxios.post('/team/approve', postDto).catch((err) => err);
}
