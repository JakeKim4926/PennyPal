import { customAxios } from '@/shared';

export async function getMarketItemList(page: number, size?: number) {
    return await customAxios.get(`/market/products?&page=${page}`).catch((err) => err);
}
