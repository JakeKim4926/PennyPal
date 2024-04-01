import { useLocation } from 'react-router-dom';
import { customAxios, PageHeader } from '../../../shared';
import { getCookie } from '../../../shared/lib/cookieHelper';
import { ExpenditureWeekly } from './ExpenditureWeekly/ExpenditureWeekly';
import { ExpenditureGraph } from './ExpenditureGraph/ExpenditureGraph';
import { ExpenditureAnalyze } from './ExpenditureAnalyzeRecommend/ExpenditureAnalyze';
import { ExpenditureRecommend } from './ExpenditureAnalyzeRecommend/ExpenditureRecommend';

export function Expenditure() {
    const page = useLocation();

    const exampleData = {
        favCategory1: '식료품',
        favCategory2: '외식',
        items: [
            {
                id: 1,
                stats: [
                    {
                        icon: '/path/to/icon1.png',
                        label: '혜택',
                        value: '최대 5%',
                    },
                    {
                        icon: '/path/to/icon2.png',
                        label: '연회비',
                        value: '무료',
                    },
                ],
            },
            {
                id: 2,
                stats: [
                    {
                        icon: '/path/to/icon3.png',
                        label: '캐시백',
                        value: '2%',
                    },
                    {
                        icon: '/path/to/icon4.png',
                        label: '특별 할인',
                        value: '영화관 30% 할인',
                    },
                ],
            },
            {
                id: 3,
                stats: [
                    {
                        icon: '/path/to/icon3.png',
                        label: '캐시백',
                        value: '2%',
                    },
                    {
                        icon: '/path/to/icon4.png',
                        label: '특별 할인',
                        value: '영화관 30% 할인',
                    },
                ],
            },
            {
                id: 4,
                stats: [
                    {
                        icon: '/path/to/icon3.png',
                        label: '캐시백',
                        value: '2%',
                    },
                    {
                        icon: '/path/to/icon4.png',
                        label: '특별 할인',
                        value: '영화관 30% 할인',
                    },
                ],
            },
        ],
    };

    const fetchRecommendedCards = async () => {
        // getCookie 함수를 사용하여 쿠키에서 memberIndex를 가져옵니다.
        const memberIndex = getCookie('memberId'); // 쿠키에서 memberId(=memberIndex) 값을 읽습니다.

        if (memberIndex === null) {
            console.error('로그인이 필요합니다.'); // memberIndex가 없으면 오류 메시지를 출력
            return;
        }

        const requestData = {
            // memberIndex: memberIndex, // 여기에 읽어온 memberIndex를 사용
            memberIndex: 96, // 여기에 읽어온 memberIndex를 사용
        };

        try {
            const response = await customAxios.get('/card/recommend', { params: requestData });
            console.log(response.data); // 응답 데이터를 콘솔에 출력
        } catch (error) {
            console.error(error); // 오류 발생 시 콘솔에 오류를 출력
        }
    };

    const fetchBankKey = async () => {
        const memberId = getCookie('memberId');
        if (!memberId) {
            console.error('로그인이 필요합니다.');
            return;
        }

        try {
            const response = await customAxios.get(`/bank/user/key/${memberId}`);
            console.log(response.data);
        } catch (err) {
            console.error(err);
        }
    };

    const fetchAccountList = async () => {
        const memberId = getCookie('memberId');
        if (!memberId) {
            console.error('로그인이 필요합니다.');
            return;
        }

        try {
            const response = await customAxios.get(`/bank/user/account/${memberId}`);
            console.log(response.data);
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <div className="container expenditure__container ">
            <div className="expenditure">
                <PageHeader page={page.pathname.substring(1)} />
                <ExpenditureWeekly />
                <ExpenditureGraph />
                <div className="horizontal">
                    <ExpenditureAnalyze />
                    <ExpenditureRecommend {...exampleData} />
                </div>
                <div className="contentCard">
                    <button onClick={fetchRecommendedCards}>유저추천카드조회</button>|
                    <button onClick={fetchBankKey}>은행키조회</button>|
                    <button onClick={fetchAccountList}>계좌목록조회</button>|
                    <button
                        onClick={async () => {
                            const requestData = {
                                userEmail: 'mine1402@naver.com',
                                bankCode: '001',
                                transactionType: 'A',
                                accountNo: '0013386179553277',
                                startDate: '20240101',
                                endDate: '20241231',
                            };

                            try {
                                // POST 요청으로 변경, 두 번째 인자에 requestData를 본문으로 전달
                                const response = await customAxios.post('/bank/user/account/transaction', requestData);
                                console.log(response.data); // 응답 데이터를 콘솔에 출력
                            } catch (error) {
                                console.error(error); // 오류 발생 시 콘솔에 오류를 출력
                            }
                        }}
                    >
                        계좌거래내역조회
                    </button>
                </div>
            </div>
        </div>
    );
}
