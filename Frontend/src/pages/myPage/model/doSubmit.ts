import { customAxios } from '@/shared';

export async function doSubmit(type: 'nickname' | 'password', data: object) {
    return await customAxios.patch(`/member/${type}`, data).catch((err) => err);
}
