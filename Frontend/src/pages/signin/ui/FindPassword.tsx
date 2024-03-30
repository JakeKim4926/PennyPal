import { useState, useRef } from 'react';
import { Button } from '@/shared';
import { useNavigate } from 'react-router-dom';

export function FindPassword() {
    const [isDone, setIsDone] = useState<boolean>(false);
    const emailRef = useRef<HTMLInputElement>(null);
    const navigate = useNavigate();
    function handleNext(e: React.MouseEvent<HTMLButtonElement>) {
        //비밀번호 재설정 API
        console.log(emailRef.current?.value);
        // 응답에 따라 state 변화.
        if ('success') {
            setIsDone(true);
        } else {
            alert('이메일을 확인해주세요.');
        }
    }

    return (
        <div className="signin container">
            <div className="welcomebox">
                <p className="welcomebox__text">WELCOME TO PENNYPAL !</p>
                <div className="welcomebox__logo">
                    <img src="assets/image/main-logo-colored.svg" />
                </div>
                <p className="welcomebox__singIn">RESET PASSWORD</p>
            </div>
            {isDone ? (
                <div>
                    <div>
                        <p>이메일 전송 완료 !</p>
                        <p>
                            임시 비밀번호가 이메일로 전송되었습니다.
                            <br />
                            해당 비밀번호를 사용하여 로그인 후,
                            <br />
                            <p style={{ textDecoration: 'underline' }}>즉시 비밀번호를 변경해주세요.</p>
                            <div className="signInButton">
                                <Button
                                    child={'SIGN IN'}
                                    color={'color-sub'}
                                    onClick={() => {
                                        navigate('/signin');
                                    }}
                                />
                            </div>
                        </p>
                    </div>
                    <div></div>
                </div>
            ) : (
                <div>
                    <p>Enter your E-mail Address</p>
                    <div className="input-container">
                        <span>&rarr;</span>
                        <input type="text" placeholder="이메일" ref={emailRef} />
                    </div>
                    <div className="nextButton">
                        <Button child={'NEXT'} color={'color-sub'} onClick={handleNext} />
                    </div>
                </div>
            )}
        </div>
    );
}
