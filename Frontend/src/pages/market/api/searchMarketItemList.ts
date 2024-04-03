import { customAxios } from '@/shared';

export async function searchMarketItemList(keyword: string) {
    return await customAxios.get(`/market/products/search?keyword=${keyword}`).catch((err) => err);
}
