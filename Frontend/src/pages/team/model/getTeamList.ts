import { customAxios } from '@/shared';

export function getTeamList(url: string) {
    return customAxios.get(url).catch((err) => err);
}
