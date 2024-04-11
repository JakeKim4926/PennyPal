import { customAxios } from '../../../shared';
import { getCookie } from '../../../shared/lib/cookieHelper';

// 일간 지출 조회
export interface DailyExpense {
    expenseDate: string;
    expenseAmount: number;
}

export const fetchDailyExpense = async (): Promise<{ expenseDate: string; expenseAmount: number }[]> => {
    const memberId = getCookie('memberId');

    if (!memberId) {
        console.error('일간지출조회는 로그인 필요.');
        return [];
    }

    try {
        const response = await customAxios.get(`/team/expenditure/${memberId}`);
        console.log(response.data);
        if (response.data && response.data.data) {
            return response.data.data; // 실제 지출 데이터를 반환합니다.
        }
        return [];
    } catch (error) {
        console.error(error);
        return [];
    }
};
