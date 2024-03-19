import React from 'react';
import { NavItems } from './items/NavItems';

export function Nav() {
    return (
        <div className="nav__container">
            <div className="nav">
                <div className="nav__items">
                    <NavItems size={75} />
                </div>
            </div>
        </div>
    );
}
