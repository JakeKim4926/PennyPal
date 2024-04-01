import { Button, getCookie } from '@/shared';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

export function Main() {
    const navigate = useNavigate();
    useEffect(() => {
        getCookie('memberToken') ? navigate('/expenditure') : null;
    }, []);
    return (
        <div>
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
        </div>
    );
}
