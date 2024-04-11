import { dataAxios } from '@/shared';
import { getCookie } from '@/shared/lib/cookieHelper';

export interface Card {
    cardId: number;
    cardCompany: string;
    cardName: string;
    cardTopCategory: string;
    cardImg: string;
}

// * 사용자 카드 추천
export const fetchRecommendedCards = async (): Promise<Card[]> => {
    const memberIndex = getCookie('memberId');

    if (!memberIndex) {
        console.error('로그인이 필요합니다.');
        return [];
    }

    try {
        const response = await dataAxios.get('/card/recommend', { params: { memberIndex } });

        const dataWithCardId = response.data.map((item: any, index: number) => ({
            ...item,
            cardId: index + 1, // 인덱스에 1을 더해 순서대로 cardId를 할당
        }));
        console.log(dataWithCardId);

        return dataWithCardId;
    } catch (error) {
        console.error(error);
        return [];
    }
};
