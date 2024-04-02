const SET_TEAM_INFO = 'SET_TEAM_INFO' as const;

type TeamData = {
    teamId?: number | null;
    chatRoomId?: number;
    teamName?: string;
    teamLeaderId?: number;
    teamInfo?: string;
    teamScore?: number;
    teamRankRealtime?: number;
    teamIsAutoConfirm?: boolean;
    teamLastTotalExpenses?: number;
    teamThisTotalExpenses?: number;
    teamLastEachTotalExpenses?: [
        {
            date: string;
            totalAmount: number;
        },
    ];
    teamThisEachTotalExpenses?: [
        {
            date: string;
            totalAmount: number;
        },
    ];
    members?: [
        {
            memberId: number;
            memberNickname: string;
            memberLastTotalExpenses: number;
            memberThisTotalExpenses: number;
        },
    ];
};

export function setTeamInfo(teamInfo: TeamData | null) {
    return { type: SET_TEAM_INFO, data: teamInfo };
}

type setTeamInfoAction = ReturnType<typeof setTeamInfo>;

// 관리 할 상태(data)의 타입 지정
type TeamInfoState = {
    data: TeamData | null;
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
