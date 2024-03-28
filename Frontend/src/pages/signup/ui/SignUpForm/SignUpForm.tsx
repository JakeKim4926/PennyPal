import { useSignUpFormModel } from '@/pages/signup/model/signUpFormModel';
import { Button } from '@/shared';
import { useDispatch } from 'react-redux';
import { setSignUpStep } from '@/pages/signup/model/signUpStepReducer';

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
    // 예: handleChange, handleNext 등
    const handleNext = () => {
        const allFieldsValid =
            emailValid && passwordValid && confirmPasswordValid && nameValid && birthdayValid && nickNameValid;

        if (allFieldsValid) {
            dispatch(setSignUpStep(1));
            // 리덕스 상태관리로 다음 창으로 이동.
        } else {
            console.error('입력한 값이 유효하지 않습니다.');
        }
    };

    return (
        <div>
            <div>
                <p>E-mail</p>
                <div className="input-container">
                    <img src="assets/image/icons_mini/Email.svg" />
                    <input type="text" placeholder="이메일" onChange={(e) => handleChange(e, 'email')} />
                </div>
                <div className="message-container">
                    {emailValid === true ? (
                        <p className="message-container--valid">유효한 이메일입니다.</p>
                    ) : emailValid === false ? (
                        <p className="message-container--invalid">유효하지 않은 이메일입니다.</p>
                    ) : null}
                </div>
            </div>
            <div>
                <p>Password</p>
                <div className="input-container">
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
            <div>
                <p>Password Check</p>
                <div className="input-container">
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
            <div>
                <p>Name</p>
                <div className="input-container">
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
            <div>
                <p>Birthdate</p>
                <div className="input-container">
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
            <div>
                <p>Nickname</p>
                <div className="input-container">
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
                <Button child={'NEXT'} color={'color-main'} onClick={handleNext} />
            </div>
        </div>
    );
}
