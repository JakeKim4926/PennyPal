const OPEN_TEAM_LEAVE_MODAL = 'OPEN_TEAM_LEAVE_MODAL' as const;
const CLOSE_TEAM_LEAVE_MODAL = 'CLOSE_TEAM_LEAVE_MODAL' as const;

export function openTeamLeaveModal(data: { teamId: number; memberId: number }) {
    return { type: OPEN_TEAM_LEAVE_MODAL, payload: data };
}

export function closeTeamLeaveModal() {
    return { type: CLOSE_TEAM_LEAVE_MODAL };
}

type OpenModalAction = ReturnType<typeof openTeamLeaveModal> | ReturnType<typeof closeTeamLeaveModal>;

// 관리 할 상태(data)의 타입 지정
type isTeamLeaveModalOpenState = {
    data: boolean | { teamId: number; memberId: number };
};

// 초기 상태 지정
const initialState: isTeamLeaveModalOpenState = {
    data: false,
};

export function openTeamLeaveModalReducer(
    state: isTeamLeaveModalOpenState = initialState,
    action: OpenModalAction,
): isTeamLeaveModalOpenState {
    switch (action.type) {
        case OPEN_TEAM_LEAVE_MODAL:
            return { data: action.payload };
        case CLOSE_TEAM_LEAVE_MODAL:
            return { data: false };
        default:
            return state;
    }
}
