import { customAxios } from '@/shared';

export async function getRanking(url: string) {
    return await customAxios.get(url).catch((err) => err);
}
