import { Button, getCookie } from '@/shared';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFire } from '@fortawesome/free-solid-svg-icons';

export function Main() {
    const navigate = useNavigate();
    useEffect(() => {
        getCookie('memberToken') ? navigate('/expenditure') : null;
    }, []);

    return (
        <div className="container">
            <div className="slide1">
                <div className="slide1__content">
                    <h1 className="slide1__content-mainPhase">Save Now, Fire Later!</h1>
                    <FontAwesomeIcon icon={faFire} bounce />
                    <div className="slide1__content-subPhase">
                        <span>파이어족으로 가는 첫 걸음,</span>
                        <span>PennyPal과 함께 시작해요!</span>
                    </div>
                </div>
            </div>
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
            <Button
                child="시작하기"
                color="color-main"
                onClick={() => {
                    navigate('/signin');
                }}
            />
        </div>
    );
}
