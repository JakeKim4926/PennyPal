import { useLocation } from 'react-router-dom';
import { customAxios, PageHeader } from '../../../shared';
import { getCookie } from '../../../shared/lib/cookieHelper';
import { useState } from 'react';
import { ExpenditureWeekly } from './ExpenditureWeekly/ExpenditureWeekly';
import { ExpenditureGraph } from './ExpenditureGraph/ExpenditureGraph';
import { ExpenditureAnalyze } from './ExpenditureAnalyzeRecommend/ExpenditureAnalyze';
import { ExpenditureRecommend } from './ExpenditureAnalyzeRecommend/ExpenditureRecommend';

import {
    fetchBankKey,
    fetchAccountList,
    fetchAccountTransactions,
    fetchDailyExpense,
    fetchExpensePie,
    fetchFavCategory,
    fetchRecommendedCards,
} from '../model/fetchFunctions';

export function Expenditure() {
    const page = useLocation();
    const [ready, setReady] = useState<boolean>(false);

    // const exampleData = {
    //     items: [
    //         {
    //             id: 1,
    //             cardName: '카드명',
    //             cardCompany: '카드사명',
    //             cardImg: 'https://api.card-gorilla.com:8080/storage/card/508/card_img/21197/508card.png',
    //             cardTopCategory: [
    //                 {
    //                     label: '교통',
    //                 },
    //                 {
    //                     label: '교통',
    //                 },
    //                 {
    //                     label: '교통',
    //                 },
    //             ],
    //         },
    //         {
    //             id: 2,
    //             cardName: '카드명',
    //             cardCompany: '카드사명',
    //             cardImg: 'https://api.card-gorilla.com:8080/storage/card/508/card_img/21197/508card.png',
    //             cardTopCategory: [
    //                 {
    //                     label: '교통',
    //                 },
    //                 {
    //                     label: '교통',
    //                 },
    //                 {
    //                     label: '교통',
    //                 },
    //             ],
    //         },
    //         {
    //             id: 3,
    //             cardName: '카드명',
    //             cardCompany: '카드사명',
    //             cardImg: 'https://api.card-gorilla.com:8080/storage/card/508/card_img/21197/508card.png',
    //             cardTopCategory: [
    //                 {
    //                     label: '교통',
    //                 },
    //                 {
    //                     label: '교통',
    //                 },
    //                 {
    //                     label: '교통',
    //                 },
    //             ],
    //         },
    //         {
    //             id: 4,
    //             cardName: '카드명',
    //             cardCompany: '카드사명',
    //             cardImg: 'https://api.card-gorilla.com:8080/storage/card/508/card_img/21197/508card.png',
    //             cardTopCategory: [
    //                 {
    //                     label: '교통',
    //                 },
    //                 {
    //                     label: '교통',
    //                 },
    //                 {
    //                     label: '교통',
    //                 },
    //             ],
    //         },
    //     ],
    // };

    return (
        <div className="container expenditure__container ">
            <div className="expenditure">
                <PageHeader page={page.pathname.substring(1)} />
                <ExpenditureWeekly />
                <ExpenditureGraph />
                <div className="horizontal">
                    <ExpenditureAnalyze setReady={setReady} />
                    {/* <ExpenditureRecommend {...exampleData} ready={ready} /> */}
                    <ExpenditureRecommend ready={ready} />
                </div>
                <div className="contentCard">
                    <button onClick={fetchBankKey}>은행키조회</button>|||
                    <button onClick={fetchAccountList}>계좌목록조회</button>|||
                    <button onClick={fetchAccountTransactions}>계좌거래내역조회</button>|||
                    <button onClick={fetchDailyExpense}>일간지출조회</button>|||
                    <button onClick={fetchExpensePie}>지출비율조회</button>|||
                    <button onClick={fetchFavCategory}>최애카테고리조회</button>|||
                    <button onClick={fetchRecommendedCards}>유저추천카드조회</button>|||
                </div>
            </div>
        </div>
    );
}
