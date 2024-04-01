// 액션 타입 정의
const SET_SIGN_UP_STEP = 'SET_SIGN_UP_STEP' as const;

// 액션 타입을 위한 TypeScript 인터페이스
interface SetSignUpStepAction {
    type: typeof SET_SIGN_UP_STEP;
    payload: number;
}

// 액션 생성 함수
export const setSignUpStep = (step: number) => ({
    type: SET_SIGN_UP_STEP,
    payload: step,
});

interface SignUpStepState {
    currentStep: number; // 0: SignUpForm, 1: Agreement, 2: SignUpDone
}

// 초기 상태
const initialState: SignUpStepState = {
    currentStep: 0,
};

// 리듀서
export function signUpStepReducer(state: SignUpStepState = initialState, action: SetSignUpStepAction): SignUpStepState {
    switch (action.type) {
        case SET_SIGN_UP_STEP:
            return {
                ...state,
                currentStep: action.payload,
            };
        default:
            return state;
    }
}
