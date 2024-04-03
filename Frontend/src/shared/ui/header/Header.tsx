import React from 'react';
import { Logo } from '../logo/Logo';
import { Menubar } from './menubar/Menubar';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { RootState } from '@/app/appProvider';

export function Header() {
    const isInitPage = useSelector((state: RootState) => state.setIsInitPageReducer.data);

    return (
        <div className="header__container">
            <div className="header">
                <div className="header__logo">
                    <Link to="/main">
                        <Logo />
                    </Link>
                </div>
                <div className="header__menubar">
                    <Menubar size={22} />
                </div>
            </div>
        </div>
    );
}
