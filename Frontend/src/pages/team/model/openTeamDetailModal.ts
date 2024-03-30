const OPEN_TEAM_DETAIL_MODAL = 'OPEN_TEAM_DETAIL_MODAL' as const;
const CLOSE_TEAM_DETAIL_MODAL = 'CLOSE_TEAM_DETAIL_MODAL' as const;

export function openTeamDetailModal(data: object) {
    return { type: OPEN_TEAM_DETAIL_MODAL, payload: data };
}

export function closeTeamDetailModal() {
    return { type: CLOSE_TEAM_DETAIL_MODAL };
}

type OpenModalAction = ReturnType<typeof openTeamDetailModal> | ReturnType<typeof closeTeamDetailModal>;

// 관리 할 상태(data)의 타입 지정
type OpenTeamDetailModalState = {
    data: boolean | object;
};

// 초기 상태 지정
const initialState: OpenTeamDetailModalState = {
    data: false,
};

export function openTeamDetailModalReducer(
    state: OpenTeamDetailModalState = initialState,
    action: OpenModalAction,
): OpenTeamDetailModalState {
    switch (action.type) {
        case OPEN_TEAM_DETAIL_MODAL:
            return { data: action.payload };
        case CLOSE_TEAM_DETAIL_MODAL:
            return { data: false };
        default:
            return state;
    }
}
