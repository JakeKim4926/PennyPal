import React from 'react';

type TeamTeamInfoProps = {
    hasTeam: boolean;
};

export function TeamTeamInfo({ hasTeam = false }: TeamTeamInfoProps) {
    if (!hasTeam) {
        return <div>팀 없음</div>;
    }

    const teamInfo = {
        name: '팀명123456',
        head: 5,
        ranking: 20,
        prev: 800000,
        pres: 400000,
    };

    return (
        <div className="teamTeamInfo">
            <div className="teamTeamInfo__title">
                <div className="teamTeamInfo__title-text">TEAM INFO</div>
            </div>
            <div className="teamTeamInfo__top">
                <div className="teamTeamInfo__top-name">
                    <div className="teamTeamInfo__top-name-title">{teamInfo.name}</div>
                    <div className="teamTeamInfo__top-name-head">
                        <img src="assets/image/person.svg" width={20} />
                        <div>{teamInfo.head}</div>
                    </div>
                </div>
                <div className="teamTeamInfo__top-ranking">
                    주간 랭킹 {teamInfo.ranking}
                    <span className="unit">위</span>
                </div>
            </div>
            <div className="teamTeamInfo__middle">
                <div className="teamTeamInfo__middle-prev">
                    <div className="teamTeamInfo__middle-prev-title subtitle">지난주</div>
                    <div className="teamTeamInfo__middle-prev-value value">
                        {teamInfo.prev}
                        <span className="unit">원</span>
                    </div>
                </div>
                <div className="teamTeamInfo__middle-pres">
                    <div className="teamTeamInfo__middle-pres-title subtitle">이번주</div>
                    <div className="teamTeamInfo__middle-pres-value value">
                        {teamInfo.pres}
                        <span className="unit">원</span>
                    </div>
                </div>
                <div className="teamTeamInfo__middle-ratio">
                    <div className="teamTeamInfo__middle-ratio-title subtitle">절감률</div>
                    <div className="teamTeamInfo__middle-ratio-value value">
                        {((teamInfo.pres / teamInfo.prev) * 100).toFixed(1)}
                        <span className="unit">%</span>
                    </div>
                </div>
            </div>
            <div className="teamTeamInfo__bottom">
                <button className="teamTeamInfo__bottom-button">
                    <svg width="40" height="41" viewBox="0 0 40 41" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path
                            className="teamTeamInfo__bottom-button-abc"
                            fill-rule="evenodd"
                            clip-rule="evenodd"
                            d="M21.8228 4.23873V0.905396L40.0046 5.9054V35.9054L21.8228 40.9054V37.5721H5.45918V25.9054H7.27737V35.9054H21.8228V5.9054H7.27737V17.5721H5.45918V4.23873H21.8228ZM23.641 38.6654L38.1865 34.6654V7.1454L23.641 3.1454V38.6654ZM16.4646 20.9054L10.4774 15.4171L11.7628 14.2387L19.9446 21.7387L11.7628 29.2387L10.4774 28.0604L16.4646 22.5721H0.00463867V20.9054H16.4646Z"
                            fill="#FCFCFC"
                        />
                    </svg>
                    <div>입장하기</div>
                </button>
            </div>
        </div>
    );
}
