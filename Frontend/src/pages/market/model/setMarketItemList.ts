const SET_MARKET_ITEM_LIST = 'SET_MARKET_ITEM_LIST' as const;

type Product = {
    productBrand?: string;
    productCategory?: string;
    productId?: number;
    productImg?: string;
    productPrice?: number;
    productQuantity?: number;
    productName?: string;
};

export function setMarketItemList(data: Product[]) {
    return { type: SET_MARKET_ITEM_LIST, payload: data };
}

type SetMarketItemListAction = ReturnType<typeof setMarketItemList>;

// 관리 할 상태(data)의 타입 지정
type setMarketItemListState = {
    data: Product[] | [];
};

// 초기 상태 지정
const initialState: setMarketItemListState = {
    data: [],
};

export function setMarketItemListReducer(
    state: setMarketItemListState = initialState,
    action: SetMarketItemListAction,
): setMarketItemListState {
    switch (action.type) {
        case SET_MARKET_ITEM_LIST:
            return { data: action.payload };
        default:
            return state;
    }
}
