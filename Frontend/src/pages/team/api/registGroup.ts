import { customAxios } from '@/shared';

export async function registGroup(postDto: object) {
    return await customAxios.post(`/team/join`, postDto).catch((err) => {
        alert('예기치 못한 에러가 발생했습니다.');

        return err;
    });
}
