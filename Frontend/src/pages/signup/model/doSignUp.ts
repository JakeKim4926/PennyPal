import { customAxios } from '@/shared';

export async function doSignUp(userData: object) {
    return await customAxios.post('/member/signup', userData).catch((err) => err);
}
