import { customAxios } from '@/shared';

export async function doLogin(userData: object) {
    return await customAxios.post('/member/login', userData).catch((err) => err);
}
