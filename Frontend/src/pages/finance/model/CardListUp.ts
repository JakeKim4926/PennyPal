import { customAxios } from '@/shared';

export async function CardListUp(page?: number, size?: number) {
    return await customAxios.get(`/card?page=${page}&size=${size}`).catch((err) => err);
}
