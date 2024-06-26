import { useSignUpFormModel } from '@/pages/signup/model/useSignupFormmodel';
import { useDispatch } from 'react-redux';
import { setSignUpStep } from '@/pages/signup/model/signUpStepReducer';
import { doSignUp, createUserKey } from '@/pages/signup/model/doSignUp';
export function SignUpForm() {
    // 여기에 핸들러 함수 및 유효성 검사 로직 추가
    const {
        userData,
        emailValid,
        passwordValid,
        confirmPasswordValid,
        nameValid,
        birthdayValid,
        nickNameValid,
        handleChange,
    } = useSignUpFormModel(); // useSignUpFormModel 훅을 사용하여 필요한 상태와 함수를 가져옵니다.

    const dispatch = useDispatch();

    const allFieldsValid =
        emailValid && passwordValid && confirmPasswordValid && nameValid && birthdayValid && nickNameValid;
    const handleNext = async () => {
        const data = {
            memberEmail: userData.email,
            memberPassword: userData.password,
            memberName: userData.name,
            memberNickname: userData.nickName,
            memberBirthDate: userData.birthday,
        };
        try {
            const response = await doSignUp(data);
            const response2 = await createUserKey(data.memberEmail);
            if (response.data.status === 'OK' && response2.data.code === 200) {
                dispatch(setSignUpStep(2));
            } else alert(response.data.message); // 서버로부터 받은 응답 message 데이터 출력
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div className="signupForm">
            <div className="signupForm__top">
                <div className="signupForm__top-email">
                    <div className="signupForm__top-email-title">E-mail</div>
                    <div className="signupForm__top-email-sub">
                        <img src="assets/image/icons_mini/Email.svg" />
                        <input type="text" placeholder="이메일" onChange={(e) => handleChange(e, 'email')} />
                    </div>
                </div>
                <div className="message-container">
                    {emailValid === true ? (
                        <p className="message-container--valid">유효한 이메일입니다.</p>
                    ) : emailValid === false ? (
                        <p className="message-container--invalid">유효하지 않은 이메일입니다.</p>
                    ) : null}
                </div>
            </div>
            <div className="signupForm__pw">
                <div className="signupForm__pw-title">Password</div>
                <div className="signupForm__pw-sub">
                    <img src="assets/image/icons_mini/password.svg" />
                    <input type="password" placeholder="패스워드" onChange={(e) => handleChange(e, 'password')} />
                </div>
                <div className="message-container">
                    {passwordValid === true ? (
                        <p className="message-container--valid">유효한 패스워드입니다.</p>
                    ) : passwordValid === false ? (
                        <p className="message-container--invalid">유효하지 않은 패스워드입니다.</p>
                    ) : null}
                </div>
            </div>
            <div className="singupForm__pwcheck">
                <div className="signupForm__pwcheck-title">Password Check</div>
                <div className="signupForm__pwcheck-sub">
                    <img src="assets/image/icons_mini/password.svg" />
                    <input
                        type="password"
                        placeholder="패스워드 확인"
                        onChange={(e) => handleChange(e, 'confirmPassword')}
                    />
                </div>
                <div className="message-container">
                    {confirmPasswordValid === true ? (
                        <p className="message-container--valid">패스워드가 일치합니다.</p>
                    ) : confirmPasswordValid === false ? (
                        <p className="message-container--invalid">패스워드가 일치하지 않습니다.</p>
                    ) : null}
                </div>
            </div>
            <div className="signupForm__name">
                <div className="signupForm__name-title">Name</div>
                <div className="signupForm__name-sub">
                    <img src="assets/image/icons_mini/User.svg" />
                    <input type="text" placeholder="이름" onChange={(e) => handleChange(e, 'name')} />
                </div>
                <div className="message-container">
                    {nameValid === true ? (
                        <p className="message-container--valid">유효한 이름입니다.</p>
                    ) : nameValid === false ? (
                        <p className="message-container--invalid">유효하지 않은 이름입니다.</p>
                    ) : null}
                </div>
            </div>
            <div className="signupForm__birth">
                <div className="signupForm__birth-title">Birthdate</div>
                <div className="signupForm__birth-sub">
                    <img src="assets/image/icons_mini/BirthDate.svg" />
                    <input type="text" placeholder="생일" onChange={(e) => handleChange(e, 'birthday')} />
                </div>
                <div className="message-container">
                    {birthdayValid === true ? (
                        <p className="message-container--valid">유효한 생일입니다.</p>
                    ) : birthdayValid === false ? (
                        <p className="message-container--invalid">유효하지 않은 생일입니다.</p>
                    ) : null}
                </div>
            </div>
            <div className="signupForm__nickname">
                <div className="signupForm__nickname-title">Nickname</div>
                <div className="signupForm__nickname-sub">
                    <img src="assets/image/icons_mini/Name.svg" />
                    <input type="text" placeholder="닉네임" onChange={(e) => handleChange(e, 'nickName')} />
                </div>
                <div className="message-container">
                    {nickNameValid === true ? (
                        <p className="message-container--valid">유효한 닉네임입니다.</p>
                    ) : nickNameValid === false ? (
                        <p className="message-container--invalid">유효하지 않은 닉네임입니다.</p>
                    ) : null}
                </div>
            </div>
            <div className="nextButton">
                <button
                    className="nextButton__button button"
                    disabled={!allFieldsValid}
                    onClick={() => {
                        if (allFieldsValid) {
                            handleNext();
                        }
                    }}
                >
                    REGIST
                </button>
                {/* <Button child={'REGISTER'} color={'color-main'} disabled={!allFieldsValid} onClick={handleNext} /> */}
            </div>
        </div>
    );
}
