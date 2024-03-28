import { customAxios } from '@/shared';

export function getTeamList(name: string, page: number) {
    return customAxios.get(`/team?keyword=${name}&page=${page}`).catch((err) => err);
}
