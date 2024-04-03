import { customAxios } from '@/shared';

export async function getMarketItemByCategory(category: string) {
    return await customAxios.get(`/market/products/category/${category}`).catch((err) => err);
}
