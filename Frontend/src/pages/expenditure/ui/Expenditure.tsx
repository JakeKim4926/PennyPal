import { useLocation } from 'react-router-dom';
import { customAxios, PageHeader } from '../../../shared';
import { getCookie } from '../../../shared/lib/cookieHelper';

import { ExpenditureWeekly } from './ExpenditureWeekly/ExpenditureWeekly';
import { ExpenditureGraph } from './ExpenditureGraph/ExpenditureGraph';
import { ExpenditureAnalyze } from './ExpenditureAnalyzeRecommend/ExpenditureAnalyze';
import { ExpenditureRecommend } from './ExpenditureAnalyzeRecommend/ExpenditureRecommend';

import {
    fetchRecommendedCards,
    fetchBankKey,
    fetchAccountList,
    fetchAccountTransactions,
} from '../model/fetchFunctions';

export function Expenditure() {
    const page = useLocation();

    const exampleData = {
        favCategory: '식료품',
        items: [
            {
                id: 1,
                cardName: '카드명',
                cardCompany: '카드사명',
                cardImg: 'https://api.card-gorilla.com:8080/storage/card/508/card_img/21197/508card.png',
                cardTopCategory: [
                    {
                        label: '교통',
                    },
                    {
                        label: '교통',
                    },
                    {
                        label: '교통',
                    },
                ],
            },
            {
                id: 2,
                cardName: '카드명',
                cardCompany: '카드사명',
                cardImg: 'https://api.card-gorilla.com:8080/storage/card/508/card_img/21197/508card.png',
                cardTopCategory: [
                    {
                        label: '교통',
                    },
                    {
                        label: '교통',
                    },
                    {
                        label: '교통',
                    },
                ],
            },
            {
                id: 3,
                cardName: '카드명',
                cardCompany: '카드사명',
                cardImg: 'https://api.card-gorilla.com:8080/storage/card/508/card_img/21197/508card.png',
                cardTopCategory: [
                    {
                        label: '교통',
                    },
                    {
                        label: '교통',
                    },
                    {
                        label: '교통',
                    },
                ],
            },
            {
                id: 4,
                cardName: '카드명',
                cardCompany: '카드사명',
                cardImg: 'https://api.card-gorilla.com:8080/storage/card/508/card_img/21197/508card.png',
                cardTopCategory: [
                    {
                        label: '교통',
                    },
                    {
                        label: '교통',
                    },
                    {
                        label: '교통',
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
                    <button onClick={fetchRecommendedCards}>유저추천카드조회</button>|
                    <button onClick={fetchBankKey}>은행키조회</button>|
                    <button onClick={fetchAccountList}>계좌목록조회</button>|
                    <button onClick={fetchAccountTransactions}>계좌거래내역조회</button>
                </div>
            </div>
        </div>
    );
}
