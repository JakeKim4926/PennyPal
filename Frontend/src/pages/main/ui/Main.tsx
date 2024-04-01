import { Button } from '@/shared';
import { useNavigate } from 'react-router-dom';

export function Main() {
    const navigate = useNavigate();
    return (
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
            <br /> <br />
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
