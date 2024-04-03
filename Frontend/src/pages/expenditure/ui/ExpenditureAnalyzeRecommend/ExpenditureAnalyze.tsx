import React, { useState, useEffect } from 'react';
import PieChart from '@/shared/ui/chartArea/PieChart';
import { MemberCategoryPercentage, fetchExpensePie } from '../../model/fetchFunctions';

export function ExpenditureAnalyze() {
    const [memberCategoryPercentage, setMemberCategoryPercentage] = useState<MemberCategoryPercentage>();

    useEffect(() => {
        const getExpensePie = async () => {
            await fetchExpensePie()
                .then((data: any) => setMemberCategoryPercentage(data.memberCategoryPercentage))
                .catch((error: Error) => console.error(error));
        };

        getExpensePie();
    }, []);

    const sortedCategories = memberCategoryPercentage
        ? Object.entries(memberCategoryPercentage).sort((a, b) => b[1] - a[1])
        : [];

    const datas = [40, 20, 10, 8, 6, 6, 4, 2, 2, 1, 1];
    const labels = [
        '자동차',
        '문화',
        '교육',
        '금융/보험',
        '식비',
        '주거/통신',
        '의료',
        '기타',
        '쇼핑',
        '교통',
        '항공/여행',
    ]; // 레이블
    const backgroundColors = [
        '#88BA53',
        '#EBC069',
        '#404040',
        '#505050',
        '#606060',
        '#707070',
        '#808080',
        '#909090',
        '#bbbbbb',
        '#cccccc',
        '#dddddd',
    ]; // 배경 색상

    return (
        <div className="expenditureAnalyze contentCard">
            <div className="example">
                {sortedCategories.length > 0 ? (
                    sortedCategories.map(([category, percentage], index) => (
                        <p key={index}>{`${category.replace('category_', '').replace(/_/g, ' ')}: ${(
                            percentage * 100
                        ).toFixed(2)}%`}</p>
                    ))
                ) : (
                    <p>Loading or no data available...</p>
                )}
            </div>
            <div className="contentCard__title">
                <div className="contentCard__title-text">ANALYZE</div>
            </div>
            <div className="expenditureAnalyze__content">
                <div className="expenditureAnalyze__content-title">누적 지출 카테고리 TOP 11</div>
                <div className="expenditureAnalyze__content-data">
                    <div className="expenditureAnalyze__content-data-chart">
                        <PieChart datas={datas} labels={labels} backgroundColors={backgroundColors} />
                    </div>
                    <div className="expenditureAnalyze__content-data-legend"></div>
                </div>
                <div className="expenditureAnalyze__content-info">
                    00님의 누적 최다 지출 카테고리는 <span>식료품</span> 입니다!
                </div>
            </div>
        </div>
    );
}
