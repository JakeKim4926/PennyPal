import { customAxios } from '@/shared';

export async function purchaseItem(postDto: Object) {
    return await customAxios.post('/market/purchase', postDto).catch((err) => err);
}
