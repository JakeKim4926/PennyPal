import { MemberCategoryPercentage } from '@/pages/expenditure/index';

export function isMemberCategoryPercentage(obj: any): obj is MemberCategoryPercentage {
    return (
        'category_car' in obj &&
        'category_culture' in obj &&
        'category_education' in obj &&
        // 모든 필수 카테고리 키들에 대해 확인
        'category_travel' in obj
    );
}

export const categoryLabels = {
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

export type Props = {
    setReady: any;
};
