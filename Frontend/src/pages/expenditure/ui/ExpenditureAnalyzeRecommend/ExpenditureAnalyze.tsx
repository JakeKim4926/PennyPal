import React from 'react';
import PieChart from '@/shared/ui/chartArea/PieChart';

export function ExpenditureAnalyze() {
    const datas = [40, 20, 10, 8, 6, 6, 4, 2, 2, 1];
    const labels = ['식료품', '생필품', '의류', '전자기기', '책', '가구', '장난감', '운동용품', '악기', '패션']; // 레이블
    const backgroundColors = [
        '#88BA53',
        '#EBC069',
        '#404040',
        '#565656',
        '#707070',
        '#868686',
        '#A0A0A0',
        '#B6B6B6',
        '#D0D0D0',
        '#E6E6E6',
    ]; // 배경 색상

    return (
        <div className="expenditureAnalyze contentCard">
            <div className="contentCard__title">
                <div className="contentCard__title-text">ANALYZE</div>
            </div>
            <div className="expenditureAnalyze__content">
                <div className="expenditureAnalyze__content-title">누적 지출 카테고리 TOP 10</div>
                <div className="expenditureAnalyze__content-data">
                    <div className="expenditureAnalyze__content-data-chart">
                        <PieChart datas={datas} labels={labels} backgroundColors={backgroundColors} />
                    </div>
                    <div className="expenditureAnalyze__content-data-legend">범례</div>
                </div>
                <div className="expenditureAnalyze__content-info">
                    00님의 누적 최다 지출 카테고리는 <span>식료품</span>, <span>외식</span> 입니다!
                </div>
            </div>
        </div>
    );
}
