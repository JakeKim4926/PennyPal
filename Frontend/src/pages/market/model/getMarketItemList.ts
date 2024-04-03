import { customAxios } from '@/shared';

export async function getMarketItemList(keyword: string, page: number, size?: number) {
    return await customAxios.get(`/market/products?keyword=${keyword}&page=${page}`).catch((err) => err);
}
