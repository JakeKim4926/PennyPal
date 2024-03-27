import { useLocation } from 'react-router-dom';
import { PageHeader } from '../../../shared';
import { ExpenditureWeekly } from './ExpenditureWeekly/ExpenditureWeekly';
import { ExpenditureGraph } from './ExpenditureGraph/ExpenditureGraph';
import { ExpenditureAnalyzeRecommend } from './ExpenditureAnalyzeRecommend/ExpenditureAnalyzeRecommend';

export function Expenditure() {
    const page = useLocation();

    return (
        <div className="container expenditure__container ">
            <div className="expenditure">
                <PageHeader page={page.pathname.substring(1)} />
                <ExpenditureWeekly />
                <ExpenditureGraph />
                <div className="horizontal">
                    <ExpenditureAnalyzeRecommend />
                </div>
            </div>
        </div>
    );
}
