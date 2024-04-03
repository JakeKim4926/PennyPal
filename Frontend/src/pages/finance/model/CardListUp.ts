import { customAxios } from '@/shared';

export async function CardListUp(
    page?: number,
    size?: number,
    cardName?: string,
    cardCompany?: string,
    startCardRequired?: number,
    endCardRequired?: number,
    startCardDomestic?: number,
    endCardDomestic?: number,
) {
    // 쿼리 파라미터를 구성하는 로직
    const params = new URLSearchParams();

    if (page !== undefined) params.append('page', page.toString());
    if (size !== undefined) params.append('size', size.toString());
    if (cardName !== undefined) params.append('cardName', cardName);
    if (cardCompany !== undefined) params.append('cardCompany', cardCompany);
    if (startCardRequired !== undefined) params.append('startCardRequired', startCardRequired.toString());
    if (endCardRequired !== undefined) params.append('endCardRequired', endCardRequired.toString());
    if (startCardDomestic !== undefined) params.append('startCardDomestic', startCardDomestic.toString());
    if (endCardDomestic !== undefined) params.append('endCardDomestic', endCardDomestic.toString());

    return await customAxios.get(`/card?${params.toString()}`).catch((err) => {
        console.error('API 요청 중 오류 발생:', err);
        return err; // 여기서 에러를 반환하는 대신 다른 에러 처리 로직을 적용할 수 있습니다.
    });
}
