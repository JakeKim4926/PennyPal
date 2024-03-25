import { useState } from 'react';
import { Button } from '../../../shared';

interface UserData {
    email: string;
    password: string;
    confirmPassword: string;
    name: string;
    birthday: string;
    nickName: string;
}

export function SignUpForm() {
    const [userData, setUserData] = useState<UserData>({
        email: '',
        password: '',
        confirmPassword: '',
        name: '',
        birthday: '',
        nickName: '',
    });

    const [emailValid, setEmailValid] = useState<boolean | undefined>(undefined);
    const [passwordValid, setPasswordValid] = useState<boolean | undefined>(undefined);
    const [confirmPasswordValid, setConfirmPasswordValid] = useState<boolean | undefined>(undefined);
    const [nameValid, setNameValid] = useState<boolean | undefined>(undefined);
    const [birthdayValid, setBirthdayValid] = useState<boolean | undefined>(undefined);
    const [nickNameValid, setNickNameValid] = useState<boolean | undefined>(undefined);

    function onChangeEmail(value: string) {
        const emailPattern = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/; // 표준 이메일 양식
        setEmailValid(value === '' ? undefined : emailPattern.test(value));
    }

    function onChangePassword(value: string) {
        const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d!@#$%^&*()-=_+]{8,}$/; // 영문자와 숫자를 최소 한 개 이상 포함하고, 특수문자(!@#$%^&*()-=_+)를 허용하며, 총 길이가 8 이상
        setPasswordValid(value === '' ? undefined : passwordPattern.test(value));
    }

    function onChangeConfirmPassword(value: string) {
        setConfirmPasswordValid(value === '' ? undefined : value === userData.password);
    }

    function onChangeName(value: string) {
        const namePattern = /^[a-zA-Z가-힣]{1,20}$/; // 영어 대소문자와 한글로 이루어진 길이가 1에서 20
        setNameValid(value === '' ? undefined : namePattern.test(value));
    }

    function onChangeBirthday(value: string) {
        const birthdayPattern = /^(19\d{2}|20[0-2]\d)-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])$/; // 연도-월-일 형식으로, 연도는 1900부터 2029까지이고, 월은 01부터 12까지, 일은 해당 월의 유효한 일자 범위 내여야 함
        setBirthdayValid(value === '' ? undefined : birthdayPattern.test(value));
    }

    function onChangeNickName(value: string) {
        const nickNamePattern = /^[a-zA-Z가-힣0-9\s]{1,20}$/; // 영어 대소문자, 한글, 숫자, 공백으로 이루어진 길이가 1에서 20
        setNickNameValid(value === '' ? undefined : nickNamePattern.test(value));
    }

    function handleChange(e: React.ChangeEvent<HTMLInputElement>, field: keyof UserData) {
        const { value } = e.target;
        setUserData((prevData) => ({
            ...prevData,
            [field]: value,
        }));
        switch (field) {
            case 'email':
                onChangeEmail(value);
                break;
            case 'password':
                onChangePassword(value);
                break;
            case 'confirmPassword':
                onChangeConfirmPassword(value);
                break;
            case 'name':
                onChangeName(value);
                break;
            case 'birthday':
                onChangeBirthday(value);
                break;
            case 'nickName':
                onChangeNickName(value);
                break;
            default:
                break;
        }
    }

    const handleNext = () => {
        const allFieldsValid =
            emailValid && passwordValid && confirmPasswordValid && nameValid && birthdayValid && nickNameValid;

        if (allFieldsValid) {
            console.log('다음으로 넘어가는거 구현!!');
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
                <Button child={'NEXT'} color={'light'} onClick={handleNext} />
            </div>
        </div>
    );
}
