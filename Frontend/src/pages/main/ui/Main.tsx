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
        <main>
            <div className="slide1">
                <div className="slide1__content">
                    <h1 className="slide1__content-mainPhase">Save Now, Fire Later!</h1>
                    <FontAwesomeIcon icon={faFire} bounce />
                    <div className="slide1__content-subPhase"></div>
                </div>
            </div>
            <div className="container">
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
        </main>
    );
}
