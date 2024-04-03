import { customAxios, dataAxios } from '../../../shared';
import { getCookie } from '../../../shared/lib/cookieHelper';

// 이메일 조회
export const fetchMemberEmail = async (memberId: string): Promise<string> => {
    try {
        const response = await customAxios.get(`/member/${memberId}`);
        return response.data.data.memberEmail;
    } catch (error) {
        console.error('회원 정보 조회 중 오류가 발생했습니다:', error);
        throw error;
    }
};

// 출석
export const fetchMemberAttendance = async (): Promise<void> => {
    const memberIndex = getCookie('memberId');

    if (!memberIndex) {
        console.error('로그인이 필요합니다.');
        return;
    }

    try {
        // 오늘 날짜를 "YYYY-MM-DD" 형식으로 포맷
        const today = new Date().toISOString().split('T')[0];

        const requestData = {
            memberId: memberIndex,
            memberDate: today,
        };

        const response = await customAxios.post('/member/attend', requestData);

        console.log(today);
        console.log(response.data); // 성공 응답 처리
        alert('출석 인증에 성공하셨습니다! 포인트가 지급됩니다!');
        // setCoverVisible은 React useState에서 정의된 상태 설정 함수를 가정
        // setCoverVisible(false); // 이 줄은 setCoverVisible이 정의된 컴포넌트의 컨텍스트 내에서 실행되어야 함
    } catch (error) {
        console.error('출석 인증 중 오류 발생:', error);
        alert('출석 인증 중 오류가 발생했습니다. 다시 시도해주세요.');
    }
};

// 출석여부 확인
export const fetchCheckMemberAttendance = async (): Promise<void> => {
    const memberId = getCookie('memberId');

    if (!memberId) {
        console.error('로그인이 필요합니다.');
        return;
    }

    try {
        const response = await customAxios.get('/member/attend/state', { params: { memberId } });
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
};

// 사용자 은행키 조회
export const fetchBankKey = async (): Promise<void> => {
    const memberId = getCookie('memberId');

    if (!memberId) {
        console.error('로그인이 필요합니다.');
        alert('로그인 필요');
        return;
    }

    try {
        const memberEmail = await fetchMemberEmail(memberId.toString());
        const response = await customAxios.get(`/bank/user/key/${memberEmail}`);
        console.log(response.data);
        alert('은행키 조회 성공');
    } catch (error) {
        console.error(error);
        alert('은행키 조회 실패');
    }
};

// 사용자 계좌목록 조회
export const fetchAccountList = async (): Promise<string[]> => {
    const memberId = getCookie('memberId');

    if (!memberId) {
        console.error('로그인이 필요합니다.');
        alert('로그인 필요');
        return [];
    }

    try {
        const memberEmail = await fetchMemberEmail(memberId.toString());
        const response = await customAxios.get(`/bank/user/account/${memberEmail}`);

        const accountNumbers = response.data.data.rec.map((record: { accountNo: string }) => record.accountNo);

        console.log(response.data);
        alert('계좌목록 불러오기 성공');

        return accountNumbers;
    } catch (error) {
        console.error(error);
        alert('계좌목록 불러오기 실패');
        return [];
    }
};

// 사용자 거래내역 조회
export const fetchTodayAccountTransactions = async (): Promise<void> => {
    const memberIdRaw = getCookie('memberId');
    if (!memberIdRaw) {
        console.error('로그인이 필요합니다.');
        alert('로그인이 필요합니다!');
        return;
    }
    const memberId = String(memberIdRaw);

    try {
        const memberEmail = await fetchMemberEmail(memberId);

        const accountNumbers = await fetchAccountList();
        if (accountNumbers.length === 0) {
            console.log('계좌 번호를 불러올 수 없습니다.');
            return;
        }
        const accountNo = accountNumbers[0];

        const currentDate = new Date().toISOString().slice(0, 10).replace(/-/g, '');
        const requestData = {
            userEmail: memberEmail,
            bankCode: '001',
            transactionType: 'A',
            accountNo: accountNo,
            startDate: '20000101',
            endDate: currentDate,
        };

        const response = await customAxios.post('/bank/user/account/transaction', requestData);
        console.log(response.data);
        alert('내역을 불러왔습니다!');
    } catch (error) {
        console.error(error);
        alert('계좌 불러오기 중 오류가 발생했습니다. 다시 시도해주세요.');
    }
};

// 사용자 전체 거래내역 조회
export interface Expense {
    transactionUniqueId: number;
    transactionType: number;
    transactionDate: string;
    transactionSummary: string;
    transactionBalance: string;
}

export const fetchAccountTransactions = async (): Promise<Expense[]> => {
    const memberIdRaw = getCookie('memberId');
    if (!memberIdRaw) {
        console.error('로그인이 필요합니다.');
        alert('로그인이 필요합니다!');
        return [];
    }
    const memberId = String(memberIdRaw);

    try {
        const memberEmail = await fetchMemberEmail(memberId);

        const accountNumbers = await fetchAccountList();
        if (accountNumbers.length === 0) {
            console.log('계좌 번호를 불러올 수 없습니다.');
            return [];
        }
        const accountNo = accountNumbers[0];

        const currentDate = new Date().toISOString().slice(0, 10).replace(/-/g, '');
        const requestData = {
            userEmail: memberEmail,
            bankCode: '001',
            transactionType: 'A',
            accountNo: accountNo,
            startDate: '20000101',
            endDate: currentDate,
        };

        const response = await customAxios.post('/bank/user/account/transaction', requestData);
        console.log(response.data);
        alert('내역을 불러왔습니다!');

        const expenses: Expense[] = response.data.data.rec.map((item: any) => ({
            transactionUniqueId: parseInt(item.transactionUniqueNo, 10),
            transactionType: parseInt(item.transactionType, 10),
            transactionDate: item.transactionDate,
            transactionSummary: item.transactionSummary,
            transactionBalance: item.transactionBalance,
        }));

        alert('내역 데이터를 가져왔습니다!');
        return expenses;
    } catch (error) {
        console.error(error);
        alert('계좌 불러오기 중 오류가 발생했습니다. 다시 시도해주세요.');
        return [];
    }
};

// 사용자 지출비율 조회
export interface MemberCategoryPercentage {
    category_car: number;
    category_culture: number;
    category_education: number;
    category_financial_insurance: number;
    category_food: number;
    category_housing_communication: number;
    category_medical: number;
    category_others: number;
    category_shopping: number;
    category_transportation: number;
    category_travel: number;
}

export const fetchExpensePie = async (): Promise<void> => {
    const memberIndex = getCookie('memberId');

    if (!memberIndex) {
        console.error('로그인이 필요합니다.');
        return;
    }

    try {
        const response = await dataAxios.get('/member/category', { params: { memberIndex } });
        console.log(response.data);
        return response.data;
    } catch (error) {
        console.error(error);
        return;
    }
};

// 사용자 최고지출 조회
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

// 사용자 카드 추천
export interface Card {
    cardId: number;
    cardCompany: string;
    cardName: string;
    cardTopCategory: string;
    cardImg: string;
}

export const fetchRecommendedCards = async (): Promise<Card[]> => {
    const memberIndex = getCookie('memberId');

    if (!memberIndex) {
        console.error('로그인이 필요합니다.');
        return [];
    }

    try {
        const response = await dataAxios.get('/card/recommend', { params: { memberIndex } });
        console.log(response.data);

        const dataWithCardId = response.data.map((item: any, index: number) => ({
            ...item,
            cardId: index + 1, // 인덱스에 1을 더해 순서대로 cardId를 할당
        }));
        console.log(dataWithCardId);

        return dataWithCardId;
    } catch (error) {
        console.error(error);
        return [];
    }
};
