const IS_INIT_PAGE = 'IS_INIT_PAGE' as const;

export function setIsInitPage(data: boolean) {
    return { type: IS_INIT_PAGE, payload: data };
}

type isInitPageAction = ReturnType<typeof setIsInitPage>;

type isInitPageState = {
    data: boolean;
};

const initialState: isInitPageState = {
    data: false,
};

export function setIsInitPageReducer(state: isInitPageState = initialState, action: isInitPageAction): isInitPageState {
    switch (action.type) {
        case IS_INIT_PAGE:
            return { data: action.payload };
        default:
            return state;
    }
}
