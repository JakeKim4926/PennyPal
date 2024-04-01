import { customAxios } from '@/shared';

export async function getPurchaseHistory(memberId: number) {
    return await customAxios.get(`/market/orders/member/${memberId}`).catch((err) => err);
}
