import { customAxios } from '@/shared';

export async function getTeamInfo(url: string) {
    return await customAxios.get(url).catch((err) => err);
}
