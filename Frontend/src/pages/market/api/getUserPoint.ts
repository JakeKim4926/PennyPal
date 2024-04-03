import { customAxios } from '@/shared';

export async function getUserPoint(memberId: number) {
    return await customAxios.get(`/market/points/${memberId}`).catch((err) => err);
}
