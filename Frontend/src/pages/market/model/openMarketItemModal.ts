const OPEN_MARKET_ITEM_MODAL = 'OPEN_MARKET_ITEM_MODAL' as const;
const CLOSE_MARKET_ITEM_MODAL = 'CLOSE_MARKET_ITEM_MODAL' as const;

export function openMarketItemModal(data: object) {
    return { type: OPEN_MARKET_ITEM_MODAL, payload: data };
}

export function closeMarketItemModal() {
    return { type: CLOSE_MARKET_ITEM_MODAL };
}

type OpenMarketItemModalAction = ReturnType<typeof openMarketItemModal> | ReturnType<typeof closeMarketItemModal>;

// 관리 할 상태(data)의 타입 지정
type isMarketItemModalOpenState = {
    data: boolean | object;
};

// 초기 상태 지정
const initialState: isMarketItemModalOpenState = {
    data: false,
};

export function openMarketItem(
    state: isMarketItemModalOpenState = initialState,
    action: OpenMarketItemModalAction,
): isMarketItemModalOpenState {
    switch (action.type) {
        case OPEN_MARKET_ITEM_MODAL:
            return { data: action.payload };
        case CLOSE_MARKET_ITEM_MODAL:
            return { data: false };
        default:
            return state;
    }
}
