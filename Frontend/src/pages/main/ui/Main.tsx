import { Button, getCookie, setIsInitPage } from '@/shared';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFire } from '@fortawesome/free-solid-svg-icons';
import { useDispatch } from 'react-redux';

export function Main() {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    useEffect(() => {
        getCookie('memberToken') ? navigate('/expenditure') : null;

        dispatch(setIsInitPage(true));

        return () => {
            dispatch(setIsInitPage(false));
        };
    }, []);

    return (
        <div className="container">
            <div className="landing">
                <div className="slide1">
                    <div className="slide1__content">
                        <div className="slide1__content-mainPhase">
                            <h1 className="green">Save Now,</h1> <h1 className="yellow">Fire Later!</h1>
                        </div>
                        <div
                            className="slide1__content-btn"
                            onClick={() => {
                                navigate('/signin');
                            }}
                        >
                            <FontAwesomeIcon icon={faFire} bounce />
                        </div>
                        <div className="slide1__content-subPhase">
                            <p>
                                <span className="red">파이어족</span>으로 가는 첫 걸음,
                            </p>
                            <p>
                                <span className="green">Penny</span>
                                <span className="yellow">Pal</span>과 함께 시작해요!
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
