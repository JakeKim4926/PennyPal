import { Button, getCookie } from '@/shared';
import { useSignUpFormModel } from '@/pages/signup/model/useSignupFormmodel';
import { ChangeEvent, useState } from 'react';
import { doSubmit } from '../model/doSubmit';

export function MyPage() {
    const { userData, passwordValid, confirmPasswordValid, nickNameValid, handleChange } = useSignUpFormModel();
    const [nowpassword, setNowPassword] = useState<string>('');
    // const nowNick :string = getCookie('memberNickname') === ;
    async function handleSubmit(type: 'nickname' | 'password') {
        let data = {};
        if (type === 'nickname') {
            //nickName 수정 api
            data = {
                memberId: getCookie('memberId'),
                memberNickname: userData.nickName,
            };
            console.log(data);
        } else {
            //password 수정 api
            data = {
                memberId: getCookie('memberId'),
                memberOriginPassword: nowpassword,
                memberChangePassword: userData.password,
            };
            console.log(data);
        }
        const res = await doSubmit(type, data);
        console.log(res);
    }

    function handleWithdraw() {
        // 탈퇴 aPI
    }

    return (
        <div className="container contentCard">
            <div className="contentCard__title">
                <h1 className="contentCard__title-text">MyPage</h1>
            </div>
            <div className="container contentCard">
                <div className="contentCard__title">
                    <h3 className="contentCard__title-text">회원 정보</h3>
                </div>
                <div>
                    <p>Nickname</p>
                    <div className="input-container">
                        <img src="assets/image/icons_mini/Name.svg" />
                        <input
                            type="text"
                            placeholder={'새로운 닉네임'}
                            onChange={(e) => handleChange(e, 'nickName')}
                        />
                        <div className="message-container">
                            {nickNameValid === true ? (
                                <p className="message-container--valid">유효한 닉네임입니다.</p>
                            ) : nickNameValid === false ? (
                                <p className="message-container--invalid">유효하지 않은 닉네임입니다.</p>
                            ) : null}
                        </div>
                    </div>
                    <div className="">
                        <Button
                            child={'닉네임 변경'}
                            color={'color-main'}
                            disabled={!nickNameValid}
                            onClick={() => {
                                handleSubmit('nickname');
                            }}
                        />
                    </div>
                </div>
                <div>
                    <p>현재 Password</p>
                    <div className="input-container">
                        <img src="assets/image/icons_mini/password.svg" />
                        <input
                            type="password"
                            placeholder="현재패스워드"
                            value={nowpassword}
                            onChange={(event: ChangeEvent<HTMLInputElement>) => {
                                setNowPassword(event.target.value);
                            }}
                        />
                    </div>
                </div>
                <div>
                    <p>새로운 Password</p>
                    <div className="input-container">
                        <img src="assets/image/icons_mini/password.svg" />
                        <input
                            type="password"
                            placeholder="새 패스워드"
                            onChange={(e) => handleChange(e, 'password')}
                        />
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
                    <div className="">
                        <Button
                            child={'비밀번호 변경'}
                            color={'color-main'}
                            disabled={!(passwordValid && confirmPasswordValid)}
                            onClick={() => {
                                handleSubmit('password');
                            }}
                        />
                    </div>
                    <br />
                    <div className="">
                        <Button
                            child={'회원탈퇴'}
                            color={'color-sub'}
                            disabled={!(passwordValid && confirmPasswordValid)}
                            onClick={() => {
                                handleWithdraw;
                            }}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
}
