const OPEN_TEAM_CHATTING_MODAL = 'OPEN_TEAM_CHATTING_MODAL' as const;
const CLOSE_TEAM_CHATTING_MODAL = 'CLOSE_TEAM_CHATTING_MODAL' as const;

export function openTeamChattingModal(data: { teamId: number; memberId: number; chatRoomId: number }) {
    return { type: OPEN_TEAM_CHATTING_MODAL, payload: data };
}

export function closeTeamChattingModal() {
    return { type: CLOSE_TEAM_CHATTING_MODAL };
}

type OpenModalAction = ReturnType<typeof openTeamChattingModal> | ReturnType<typeof closeTeamChattingModal>;

// 관리 할 상태(data)의 타입 지정
type isTeamChattingModalOpenState = {
    data: boolean | { teamId: number; memberId: number; chatRoomId: number };
};

// 초기 상태 지정
const initialState: isTeamChattingModalOpenState = {
    data: false,
};

export function openTeamChattingModalReducer(
    state: isTeamChattingModalOpenState = initialState,
    action: OpenModalAction,
): isTeamChattingModalOpenState {
    switch (action.type) {
        case OPEN_TEAM_CHATTING_MODAL:
            return { data: action.payload };
        case CLOSE_TEAM_CHATTING_MODAL:
            return { data: false };
        default:
            return state;
    }
}
