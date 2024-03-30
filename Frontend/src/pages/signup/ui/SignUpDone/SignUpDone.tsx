import { useEffect, useState } from 'react';
import { Button } from '@/shared';
import { useNavigate } from 'react-router-dom';

export function SignUpDone() {
    const navigate = useNavigate();
    const [countdown, setCountdown] = useState(5);

    useEffect(() => {
        // 컴포넌트가 마운트되고, 1초 간격으로 countdown 값을 업데이트합니다.
        const countdownInterval = setInterval(() => {
            setCountdown((prevCountdown) => prevCountdown - 1);
        }, 1000);
        // 컴포넌트가 언마운트될때 interval을 종료.
        return () => clearInterval(countdownInterval);
    }, []);

    useEffect(() => {
        // countdown 값이 0이 되면 '/main' 경로로 이동.
        if (countdown === 0) {
            navigate('/main');
        }
    }, [countdown]);

    return (
        <div>
            <div>
                <h1>회원가입 성공 !</h1>
                <p>당신도 이젠 절약칭구 PennyPal !</p>
            </div>
            <p>{countdown}초 뒤 메인으로 이동합니다...</p>
            <Button child={'MOVE NOW'} color={'color-main'} onClick={() => navigate('/main')} />
        </div>
    );
}
