import { customAxios } from '@/shared';

export async function StockListUp(page?: number, size?: number) {
    return await customAxios.get(`/stock/list?page=${page}&size=${size}`).catch((err) => err);
}

export async function StockDetail(stockId: number) {
    return await customAxios.get(`/stock/${stockId}`).catch((err) => err);
}
