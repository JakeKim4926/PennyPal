const OPEN_TEAM_SETTING_MODAL = 'OPEN_TEAM_SETTING_MODAL' as const;
const CLOSE_TEAM_SETTING_MODAL = 'CLOSE_TEAM_SETTING_MODAL' as const;

type Data = {
    teamId: number;
    memberId: number;
    teamName?: string;
    teamInfo?: string;
    members?: [];
    teamIsAutoConfirm?: boolean;
};

export function openTeamSettingModal(data: Data) {
    return { type: OPEN_TEAM_SETTING_MODAL, payload: data };
}

export function closeTeamSettingModal() {
    return { type: CLOSE_TEAM_SETTING_MODAL };
}

type OpenModalAction = ReturnType<typeof openTeamSettingModal> | ReturnType<typeof closeTeamSettingModal>;

// 관리 할 상태(data)의 타입 지정
type isTeamSettingModalState = {
    data: boolean | Data;
};

// 초기 상태 지정
const initialState: isTeamSettingModalState = {
    data: false,
};

export function openTeamSettingModalReducer(
    state: isTeamSettingModalState = initialState,
    action: OpenModalAction,
): isTeamSettingModalState {
    switch (action.type) {
        case OPEN_TEAM_SETTING_MODAL:
            return { data: action.payload };
        case CLOSE_TEAM_SETTING_MODAL:
            return { data: false };
        default:
            return state;
    }
}
