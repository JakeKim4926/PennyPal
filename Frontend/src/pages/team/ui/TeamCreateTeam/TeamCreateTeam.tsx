import React from 'react';
import { useState, useEffect, useRef } from 'react';

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
    useEffect(() => {
        if (contentRef.current) {
            console.dir(contentRef.current.offsetWidth);
        }
    });
    const contentRef = useRef<HTMLDivElement>(null);

    return (
        <div className="teamCreateTeam__content" ref={contentRef}>
            <div className="teamCreateTeam__content-inner">
                <div className="teamCreateTeam__content-inner-top">팀을 생성해 함께할 동료들을 모아보아요!</div>
                <button
                    className="teamCreateTeam__content-inner-nextButton"
                    onClick={() => {
                        contentRef.current?.scrollTo({
                            top: contentRef.current?.offsetHeight,
                            behavior: 'smooth',
                        });
                    }}
                >
                    팀 생성하기
                </button>
            </div>
            <div className="teamCreateTeam__content-inner">
                <div className="teamCreateTeam__content-inner-second">
                    <div className="teamCreateTeam__content-inner-second-name">
                        <div>팀명을 입력해주세요</div>
                        <input type="text" placeholder="팀명"></input>
                    </div>
                </div>
            </div>
        </div>
    );
}
