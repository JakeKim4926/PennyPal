import React from 'react';
import PieChart from '@/shared/ui/chartArea/PieChart';

export function ExpenditureAnalyzeRecommend() {
    return (
        <>
            <div className="contentCard">
                <div className="contentCard__title">
                    <div className="contentCard__title-text">ANALYZE</div>
                </div>
                <PieChart />
            </div>
            <div className="contentCard">
                <div className="contentCard__title">
                    <div className="contentCard__title-text">BIGDATA RECOMMENDING</div>
                </div>
            </div>
        </>
    );
}
