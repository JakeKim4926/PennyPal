// import React from 'react';

import { Link } from 'react-router-dom';

type MenubarProps = {
    size: number;
};

export function Menubar({ size }: MenubarProps) {
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
        </>
    );
}
