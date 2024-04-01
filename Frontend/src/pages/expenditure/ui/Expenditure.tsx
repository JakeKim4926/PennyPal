import { useLocation } from 'react-router-dom';
import { customAxios, PageHeader } from '../../../shared';
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
                    <button
                        onClick={async () => {
                            const requestData = {
                                memberIndex: 1,
                            };

                            try {
                                const response = await customAxios.get('/card/recommend', {
                                    params: requestData,
                                });
                                console.log(response.data); // 응답 데이터를 콘솔에 출력
                            } catch (error) {
                                console.error(error); // 오류 발생 시 콘솔에 오류를 출력
                            }
                        }}
                    >
                        유저추천카드조회
                    </button>

                    <button
                        onClick={async () => {
                            const c = await customAxios
                                .get('/bank/user/key/mine702@naver.com')
                                .catch((err) => console.log(err));
                            console.log(c);
                        }}
                    >
                        은행키조회
                    </button>

                    <button
                        onClick={async () => {
                            const d = await customAxios
                                .get('/bank/user/account/mine702@naver.com')
                                .catch((err) => console.log(err));
                            console.log(d);
                        }}
                    >
                        계좌목록조회
                    </button>

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
