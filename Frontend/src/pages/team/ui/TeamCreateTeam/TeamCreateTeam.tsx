import React from 'react';
import { useState } from 'react';

export function TeamCreateTeam() {
    return (
        <div className="teamCreateTeam contentCard">
            <div className="teamCreateTeam__title contentCard__title">
                <div className="teamCreateTeam__title-text contentCard__title-text">CREATE TEAM</div>
            </div>
            <Content />
        </div>
    );
}

function Content() {
    const [curPage, setCurPage] = useState(0);

    return (
        <>
            <div className="teamCreateTeam__content">
                <div className="teamCreateTeam__content-top">팀을 생성해 함께할 동료들을 모아보아요!</div>
                <button className="teamCreateTeam__content-nextButton">다음으로</button>
            </div>
        </>
    );
}
