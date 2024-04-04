// import React from 'react';

import { RootState } from '@/app/appProvider';
import { deleteCookie } from '@/shared/lib';
import { Link, useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import Swal from 'sweetalert2';

type MenubarProps = {
    size: number;
};
export function Menubar({ size }: MenubarProps) {
    const navigate = useNavigate();
    function handleSignOut() {
        deleteCookie('memberId');
        deleteCookie('memberToken');
        deleteCookie('memberNickname');
        Swal.fire({ title: '로그아웃', text: '로그아웃 되었습니다.' });
        navigate('/main');
    }
    const isInitPage = useSelector((state: RootState) => state.setIsInitPageReducer.data);

    if (isInitPage) return null;

    return (
        <>
            <div className="header__menubar-mission">
                <Link to="/mission">
                    <img src="assets/image/mission.svg" width={size} />
                </Link>
            </div>
            <div className="header__menubar-menu">
                <img src="assets/image/menu.svg" width={size + 10} />
            </div>
            <div className="header__menubar-user">
                <Link to="/my-page">
                    <img src="assets/image/user.svg" width={size} />
                </Link>
            </div>
            <a onClick={handleSignOut}>
                <img src="assets/image/logout.svg" />
            </a>
        </>
    );
}
