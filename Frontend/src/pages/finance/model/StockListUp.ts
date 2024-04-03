import { customAxios } from '@/shared';

export async function StockListUp(page?: number, size?: number, word?: string, startPrice?: number, endPrice?: number) {
    // 쿼리 파라미터를 구성하는 로직
    const params = new URLSearchParams();

    if (page !== undefined) params.append('page', page.toString());
    if (size !== undefined) params.append('size', size.toString());
    if (word !== undefined) params.append('stckIssuCmpyNm', word);
    if (startPrice !== undefined) params.append('startPrice', startPrice.toString());
    if (endPrice !== undefined) params.append('endPrice', endPrice.toString());

    return await customAxios.get(`/stock/list?${params.toString()}`).catch((err) => {
        console.error('API 요청 중 오류 발생:', err);
        return err; // 여기서 에러를 반환하는 대신 다른 에러 처리 로직을 적용할 수 있습니다.
    });
}

export async function StockDetail(stockId: number) {
    return await customAxios.get(`/stock/${stockId}`).catch((err) => err); // baseUrl확인
}
