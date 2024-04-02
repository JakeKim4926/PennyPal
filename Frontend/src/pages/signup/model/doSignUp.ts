import { customAxios } from '@/shared';

export async function doSignUp(userData: object) {
    return await customAxios.post('/member/signup', userData).catch((err) => err);
}

export async function createUserKey(email: string) {
    return await customAxios.post(`/bank/user/key/${email}`).catch((err) => err);
}
