import { customAxios } from '@/shared';

export async function registGroup(postDto: object) {
    return await customAxios.post(`/team/join`, postDto).catch((err) => err);
}
