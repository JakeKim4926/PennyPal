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
            </div>
        </div>
    );
}
