import { customAxios } from '@/shared';

export async function getMarketItemList() {
    return await customAxios.get('/market/products').catch((err) => err);
}
