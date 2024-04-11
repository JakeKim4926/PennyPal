import { useState } from 'react';
import { useLocation } from 'react-router-dom';

import { PageHeader } from '@/shared';
import {
    ExpenditureWeekly,
    ExpenditureGraph,
    ExpenditureAnalyze,
    ExpenditureRecommend,
} from '@/pages/expenditure/index';

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
