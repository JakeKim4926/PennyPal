const SET_TEAM_INFO = 'SET_TEAM_INFO' as const;

export function setTeamInfo(teamInfo: object) {
    return { type: SET_TEAM_INFO, data: teamInfo };
}

type setTeamInfoAction = ReturnType<typeof setTeamInfo>;

// 관리 할 상태(data)의 타입 지정
type TeamInfoState = {
    data: object | null;
};

// 초기 상태 지정
const initialState: TeamInfoState = {
    data: null,
};

export function setTeamInfoReducer(state: TeamInfoState = initialState, action: setTeamInfoAction): TeamInfoState {
    switch (action.type) {
        case SET_TEAM_INFO:
            return { data: action.data };
        default:
            return state;
    }
}
