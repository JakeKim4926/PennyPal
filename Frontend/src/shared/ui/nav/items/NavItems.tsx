import React from 'react';
import { Link } from 'react-router-dom';

type NavItemsProps = {
    size: number;
};

export function NavItems({ size }: NavItemsProps) {
    const items = [
        { name: 'Finance', link: 'finance' },
        { name: 'Team', link: 'team' },
        { name: 'My Spending', link: 'expenditure' },
        { name: 'Ranking', link: 'ranking' },
        { name: 'Market', link: 'market' },
    ];

    return (
        <>
            {items.map((it) => (
                <div className={`nav__items-${it.link}`}>
                    <Link to={`/${it.link}`}>
                        <img src={`assets/image/${it.link}.svg`} width={size} />
                        <div>{it.name}</div>
                    </Link>
                </div>
            ))}
        </>
    );
}
