import { customAxios } from '../../../shared';
import { getCookie } from '../../../shared/lib/cookieHelper';
import Swal from 'sweetalert2';

// * 출석
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
        Swal.fire({ title: '출석 인증 성공!', text: '일일 출석 포인트가 지급되었습니다.', icon: 'success' });
    } catch (error) {
        console.error('출석 인증 중 오류 발생:', error);
        Swal.fire({ title: '출석 인증 중 오류 발생', text: '새로고침 후 다시 시도해주세요.', icon: 'warning' });
    }
};

// * 출석여부 확인
export const fetchCheckMemberAttendance = async (): Promise<boolean> => {
    const memberId = getCookie('memberId');

    if (!memberId) {
        console.error('로그인이 필요합니다.');
        return false;
    }

    try {
        const response = await customAxios.get('/member/attend/state', { params: { memberId } });
        console.log('출석여부 :', response.data);

        if (response.data && response.data.data === false) {
            // 금일 출석을 진행하지 않았을 경우
            return true;
        } else {
            // 이미 출석을 한 경우
            return false;
        }
    } catch (error) {
        console.error(error);
        return false;
    }
};
