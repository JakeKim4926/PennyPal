import { dataAxios } from '@/shared';
import { getCookie } from '@/shared/lib/cookieHelper';

interface ExpensePieResponse {
    memberCategoryPercentage: {
        [category: string]: number;
    };
}

// * 사용자 지출비율 조회
export const fetchExpensePie = async (): Promise<ExpensePieResponse | null> => {
    const memberIndex = getCookie('memberId');

    if (!memberIndex) {
        console.error('로그인이 필요합니다.');
        return null; // 로그인이 필요한 경우 null 반환
    }

    try {
        const response = await dataAxios.get<ExpensePieResponse>('/member/category', { params: { memberIndex } });
        return response.data; // 실제 데이터 반환
    } catch (error) {
        console.error(error);
        return null; // 오류 발생 시 null 반환
    }
};

// * 사용자 최고지출 조회
export const fetchFavCategory = async (): Promise<void> => {
    const memberIndex = getCookie('memberId');

    if (!memberIndex) {
        console.error('로그인이 필요합니다.');
        return;
    }

    try {
        const response = await dataAxios.get('/member/mostCategory', { params: { memberIndex } });
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
};
