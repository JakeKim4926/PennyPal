const FORCE_RENDER = 'FORCE_RENDER' as const;

export function forceRender() {
    return { type: FORCE_RENDER };
}

type forceRenderAction = ReturnType<typeof forceRender>;

// 관리 할 상태(data)의 타입 지정
type forceRenderState = {
    data: boolean;
};

// 초기 상태 지정
const initialState: forceRenderState = {
    data: false,
};

export function forceRenderReducer(
    state: forceRenderState = initialState,
    action: forceRenderAction,
): forceRenderState {
    switch (action.type) {
        case FORCE_RENDER:
            return { data: !state };
        default:
            return state;
    }
}
