import { customAxios } from '@/shared';

export async function getRealtimeRanking(url: string) {
    return await customAxios.get(url).catch((err) => err);
}
