import { useNavigate } from 'react-router-dom';
import { Button } from '@/shared';
import { doLogin } from '@/pages/signin/model/doLogin';
import { useState, useEffect, useRef } from 'react';
import { setCookie } from '@/shared/lib'; // cookieHelper에서 setCookie 함수를 가져옵니다.
interface LoginData {
    memberEmail: string;
    memberPassword: string;
}

interface LoginResponse {
    memberId: number; // 사용자의 멤버 인덱스(PK)
    memberNickname: string; // 사용자의 닉네임
    memberToken: string; // 사용자 인증을 위한 JWT 토큰
}

// 로그인 후 서버로부터 받은 응답을 처리하는 함수입니다.
const handleLoginSuccess = (response: LoginResponse) => {
    // 받은 응답의 nickname을 쿠키에 저장합니다. 쿠키의 유효 기간은 7일입니다.
    setCookie('memberId', response.memberId.toString());
    // 받은 응답의 memberIndex를 쿠키에 저장합니다. memberIndex는 숫자이므로 문자열로 변환해 저장합니다. 쿠키의 유효 기간은 7일입니다.
    setCookie('memberNickname', response.memberNickname);
    // 받은 응답의 token(JWT 토큰)을 쿠키에 저장합니다. 쿠키의 유효 기간은 7일입니다.
    setCookie('memberToken', response.memberToken);
};

export function SignIn() {
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const emailRef = useRef<HTMLInputElement>(null); // autofocus용
    const navigate = useNavigate();

    const handleEmailChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(event.target.value);
    };

    async function handleSignIn() {
        const userData: LoginData = {
            memberEmail: email,
            memberPassword: password,
        };

        const res = await doLogin(userData).catch((err) => err);
        if (res.data.code === 200) {
            handleLoginSuccess(res.data.data);
            navigate('/expenditure');
        } else {
            alert(res.data.message);
        }
    }
    function handlePassword(e: React.MouseEvent<HTMLButtonElement>) {
        //패스워드 찾기 컴포넌트로 이동.
        navigate('/find-password');
    }
    function handleSignUp(e: React.MouseEvent<HTMLAnchorElement>) {
        // 회원가입 컴포넌트로 이동.
        navigate('/signup');
    }
    const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.key === 'Enter') {
            handleSignIn();
        }
    };
    function handleSocial(k: 'google' | 'kakao') {
        // 소셜로그인 api
        if (k === 'google') {
            //google api
        } else if (k === 'kakao') {
            //kakao api
        }
    }
    // 맨 처음 마운트 될 때 autofocus
    useEffect(() => {
        if (emailRef.current) {
            emailRef.current.focus();
        }
    }, []);
    return (
        <div className="signin container">
            <div className="welcomebox">
                <p className="welcomebox__text">WELCOME TO PENNYPAL !</p>
                <div className="welcomebox__logo">
                    <img src="assets/image/main-logo-colored.svg" />
                </div>
                <p className="welcomebox__singIn">SIGN IN</p>
            </div>
            <div className="signin-progress container">
                <div>
                    <p>E-mail</p>
                    <div className="input-container">
                        <img src="assets/image/icons_mini/Email.svg" />
                        <input
                            type="text"
                            placeholder="이메일"
                            ref={emailRef}
                            value={email}
                            onChange={handleEmailChange}
                            onKeyDown={handleKeyDown}
                        />
                    </div>
                    <p>Password</p>
                    <div className="input-container">
                        <img src="assets/image/icons_mini/password.svg" />
                        <input
                            type="password"
                            placeholder="패스워드"
                            value={password}
                            onChange={handlePasswordChange}
                            onKeyDown={handleKeyDown}
                        />
                    </div>
                    <div className="fpButton">
                        <button onClick={handlePassword}>fotgot Password ? &#9654;</button>
                    </div>
                    <div className="signInButton">
                        <Button child={'LOGIN'} color={'color-sub'} onClick={handleSignIn} />
                    </div>
                    <div className="signUpArea">
                        <span>No Account ? </span>
                        <a onClick={handleSignUp}>SIGN UP &#9654;</a>
                    </div>
                    {/* <div className="socialArea">
                        <Button
                            child={
                                <div>
                                    <img src={'assets/image/icons_mini/icon_google.svg'} />
                                    <span>GOOGLE Login</span>
                                </div>
                            }
                            onClick={() => {
                                handleSocial('google');
                            }}
                        ></Button>
                        <Button
                            child={
                                <div>
                                    <img src={'assets/image/icons_mini/icon_kakao.svg'} />
                                    <span>KAKAO Login</span>
                                </div>
                            }
                            onClick={() => {
                                handleSocial('kakao');
                            }}
                        ></Button>
                    </div> */}
                </div>
            </div>
        </div>
    );
}
