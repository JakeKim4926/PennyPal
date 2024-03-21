const OPEN_APPLY = 'OPEN_APPLY' as const;
const CLOSE_MODAL = 'CLOSE_MODAL' as const;

export function openApply(data: object) {
    return { type: OPEN_APPLY, payload: data };
}

export function closeModal() {
    return { type: CLOSE_MODAL };
}

type OpenModalAction = ReturnType<typeof openApply> | ReturnType<typeof closeModal>;

// 관리 할 상태(data)의 타입 지정
type OpenModalState = {
    data: boolean | object;
};

// 초기 상태 지정
const initialState: OpenModalState = {
    data: false,
};

export function openModal(state: OpenModalState = initialState, action: OpenModalAction): OpenModalState {
    switch (action.type) {
        case OPEN_APPLY:
            return { data: action.payload };
        case CLOSE_MODAL:
            return { data: false };
        default:
            return state;
    }
}
