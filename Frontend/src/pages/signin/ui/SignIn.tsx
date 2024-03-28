import { useNavigate } from 'react-router-dom';
import { Button } from '@/shared';

export function SignIn() {
    const navigate = useNavigate();
    function handleSignIn(e: React.MouseEvent<HTMLButtonElement>) {
        //로그인 API들어와야.
    }
    function handlePassword(e: React.MouseEvent<HTMLButtonElement>) {
        //패스워드 찾기 컴포넌트로 이동.
        navigate('/find-password');
    }
    function handleSignUp(e: React.MouseEvent<HTMLAnchorElement>) {
        // 회원가입 컴포넌트로 이동.
        navigate('/signup');
    }

    function handleSocial(k: 'google' | 'kakao') {
        // 소셜로그인 api
        if (k === 'google') {
            //google api
        } else if (k === 'kakao') {
            //kakao api
        }
    }

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
                        <input type="text" placeholder="이메일" onChange={() => {}} />
                    </div>
                    <p>Password</p>
                    <div className="input-container">
                        <img src="assets/image/icons_mini/password.svg" />
                        <input type="password" placeholder="패스워드" onChange={() => {}} />
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
                    <div className="socialArea">
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
                    </div>
                </div>
            </div>
        </div>
    );
}
