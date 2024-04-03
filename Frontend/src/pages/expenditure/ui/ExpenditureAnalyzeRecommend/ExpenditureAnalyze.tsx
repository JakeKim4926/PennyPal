import React, { useState, useEffect } from 'react';
import PieChart from '@/shared/ui/chartArea/PieChart';
import { MemberCategoryPercentage, fetchExpensePie } from '../../model/fetchFunctions';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';

function isMemberCategoryPercentage(obj: any): obj is MemberCategoryPercentage {
    return (
        'category_car' in obj &&
        'category_culture' in obj &&
        'category_education' in obj &&
        // 모든 필수 카테고리 키들에 대해 확인
        'category_travel' in obj
    );
}

const categoryLabels = {
    category_car: '자동차',
    category_culture: '문화',
    category_education: '교육',
    category_financial_insurance: '금융/보험',
    category_food: '식비',
    category_housing_communication: '주거/통신',
    category_medical: '의료',
    category_others: '기타',
    category_shopping: '쇼핑',
    category_transportation: '교통',
    category_travel: '항공/여행',
};

type Props = {
    setReady: any;
};
export function ExpenditureAnalyze({ setReady }: Props) {
    const [memberCategoryPercentage, setMemberCategoryPercentage] = useState<MemberCategoryPercentage | undefined>();
    const [isLoading, setIsLoading] = useState(true); // 로딩 상태를 추적하는 상태 추가

    useEffect(() => {
        const getExpensePie = async () => {
            setIsLoading(true); // 데이터 로딩 시작
            const result = await fetchExpensePie();
            setReady(true);

            if (result && isMemberCategoryPercentage(result.memberCategoryPercentage)) {
                setMemberCategoryPercentage(result.memberCategoryPercentage);
            } else {
                console.error('받아온 데이터가 예상한 타입과 일치하지 않습니다.');
            }
            setIsLoading(false); // 데이터 로딩 완료
        };

        getExpensePie();

        return () => {
            setReady(false);
        };
    }, []);

    const sortedCategories = memberCategoryPercentage
        ? Object.entries(memberCategoryPercentage).sort((a, b) => b[1] - a[1])
        : [];

    const datas = sortedCategories.map(([, value]) => parseFloat(value.toFixed(1)));
    const labels = sortedCategories.map(([key, value]) => {
        // categoryLabels에 key가 존재하는지 확인하고, 존재한다면 해당 값을, 그렇지 않다면 기본값으로 key를 사용합니다.
        const label =
            categoryLabels[key as keyof typeof categoryLabels] || key.replace('category_', '').replace(/_/g, ' ');
        // 레이블 뒤에 데이터 값을 백분율로 표시하고, '%' 기호를 붙입니다.
        const percentage = parseFloat(value.toFixed(1));
        return `${label} ${percentage}%`;
    });

    const largestCategoryLabel =
        sortedCategories.length > 0 ? categoryLabels[sortedCategories[0][0] as keyof typeof categoryLabels] : '';

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
            <div className="contentCard__title">
                <div className="contentCard__title-text">ANALYZE</div>
            </div>
            <div className="expenditureAnalyze__content">
                <div className="expenditureAnalyze__content-title">카테고리별 총 지출</div>
                <div className="expenditureAnalyze__content-data">
                    {isLoading ? ( // isLoading 상태에 따라 조건부 렌더링
                        <div className="loading">
                            <p>LOADING...</p>
                            <FontAwesomeIcon icon={faSpinner} spinPulse />
                        </div>
                    ) : (
                        <div className="expenditureAnalyze__content-data-chart">
                            <PieChart datas={datas} labels={labels} backgroundColors={backgroundColors} />
                        </div>
                    )}
                    <div className="expenditureAnalyze__content-data-legend"></div>
                </div>
                <div className="expenditureAnalyze__content-info">
                    누적 최다 지출 카테고리는 <span>{largestCategoryLabel}</span> 입니다!
                </div>
            </div>
        </div>
    );
}
