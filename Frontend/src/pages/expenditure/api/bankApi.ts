import { customAxios } from '../../../shared';
import { getCookie } from '../../../shared/lib/cookieHelper';
import Swal from 'sweetalert2';

// * 이메일 조회
export const fetchMemberEmail = async (memberId: string): Promise<string> => {
    try {
        const response = await customAxios.get(`/member/${memberId}`);
        return response.data.data.memberEmail;
    } catch (error) {
        console.error('회원 정보 조회 중 오류가 발생했습니다:', error);
        throw error;
    }
};

// * 사용자 은행키 조회
export const fetchBankKey = async (): Promise<void> => {
    const memberId = getCookie('memberId');

    if (!memberId) {
        console.error('로그인이 필요합니다.');
        return;
    }

    try {
        const memberEmail = await fetchMemberEmail(memberId.toString());
        const response = await customAxios.get(`/bank/user/key/${memberEmail}`);
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
};

// * 사용자 계좌목록 조회
export const fetchAccountList = async (): Promise<string[]> => {
    const memberId = getCookie('memberId');

    if (!memberId) {
        console.error('로그인이 필요합니다.');
        return [];
    }

    try {
        const memberEmail = await fetchMemberEmail(memberId.toString());
        const response = await customAxios.get(`/bank/user/account/${memberEmail}`);

        const accountNumbers = response.data.data.rec.map((record: { accountNo: string }) => record.accountNo);

        console.log(response.data);

        return accountNumbers;
    } catch (error) {
        console.error(error);
        return [];
    }
};

// * 사용자 당일 거래내역 조회
export const fetchDailyAccountTransactions = async (): Promise<void> => {
    const memberIdRaw = getCookie('memberId');
    if (!memberIdRaw) {
        console.error('로그인이 필요합니다.');
        return;
    }
    const memberId = String(memberIdRaw);

    try {
        const memberEmail = await fetchMemberEmail(memberId);

        const accountNumbers = await fetchAccountList();
        if (accountNumbers.length === 0) {
            return;
        }
        const accountNo = accountNumbers[0];

        const currentDate = new Date().toISOString().slice(0, 10).replace(/-/g, '');
        const requestData = {
            userEmail: memberEmail,
            bankCode: '001',
            transactionType: 'A',
            accountNo: accountNo,
            startDate: currentDate,
            endDate: currentDate,
        };

        const response = await customAxios.post('/bank/user/account/transaction', requestData);
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
};

// * 사용자 전체 거래내역 조회
export const fetchAccountTransactions = async (): Promise<void> => {
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
        Swal.fire({ title: '지난 거래내역을 불러왔습니다!', icon: 'success' });
    } catch (error) {
        console.error(error);
    }
};
