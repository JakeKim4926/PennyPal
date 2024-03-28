const SET_HAS_TEAM_TRUE = 'SET_HAS_TEAM_TRUE' as const;
const SET_HAS_TEAM_FALSE = 'SET_HAS_TEAM_FALSE' as const;

export function setHasTeamTrue() {
    return { type: SET_HAS_TEAM_TRUE };
}

export function setHasTeamFalse() {
    return { type: SET_HAS_TEAM_FALSE };
}

type setHasTeamAction = ReturnType<typeof setHasTeamTrue> | ReturnType<typeof setHasTeamFalse>;

// 관리 할 상태(data)의 타입 지정
type HasTeamState = {
    data: boolean;
};

// 초기 상태 지정
const initialState: HasTeamState = {
    data: false,
};

export function setHasTeam(state: HasTeamState = initialState, action: setHasTeamAction): HasTeamState {
    switch (action.type) {
        case SET_HAS_TEAM_TRUE:
            return { data: true };
        case SET_HAS_TEAM_FALSE:
            return { data: false };
        default:
            return state;
    }
}
