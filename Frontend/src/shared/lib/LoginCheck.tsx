import React, { ReactNode, useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { getCookie } from './cookieHelper';

interface LoginCheckProps {
    children: ReactNode;
}

export const LoginCheck: React.FC<LoginCheckProps> = ({ children }) => {
    const navigate = useNavigate();
    const [isAllowed, setIsAllowed] = useState<boolean>(true);
    const location = useLocation();
    useEffect(() => {
        const isLogin: boolean = !!getCookie('memberToken');

        // 현재 페이지의 경로를 통해 조건부 리디렉션 처리
        const pathname = location.pathname;

        // 로그인하지 않은 상태에서 로그인, 회원가입, 비밀번호 찾기 페이지 외 접근 시 로그인 페이지로 리디렉션
        if (!isLogin && !['/signin', '/signup', '/find-password', '/main'].includes(pathname)) {
            navigate('/signin');
            setIsAllowed(false);
        }
        // 로그인한 상태에서 로그인, 회원가입, 비밀번호 찾기 페이지 접근 시 지출 페이지로 리디렉션
        else if (isLogin && ['/signin', '/signup', '/find-password'].includes(pathname)) {
            navigate('/expenditure');
            setIsAllowed(false);
        }
    }, [navigate]);

    // 조건에 따라 자식 컴포넌트 렌더링 허용 여부 결정
    return <>{children}</>;
};
