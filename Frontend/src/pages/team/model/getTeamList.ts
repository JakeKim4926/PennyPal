import { customAxios } from '@/shared';

export async function getTeamList() {
    return await customAxios.get('/team').catch((err) => err);
}
