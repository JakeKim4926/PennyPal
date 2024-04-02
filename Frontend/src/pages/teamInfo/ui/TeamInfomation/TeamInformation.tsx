import { getCookie } from '@/shared';
import React, { useEffect, useState, useTransition } from 'react';
import { useDispatch } from 'react-redux';
import { openTeamLeaveModal } from '../../model/openTeamLeaveModal';
import { openTeamSettingModal } from '../../model/openTeamSettingModal';

type TeamInformationProps = {
    teamName: string;
    teamMembers: [];
    teamLastTotalExpenses: number;
    teamThisTotalExpenses: number;
    teamInfo?: 'string';
    teamRankRealtime: number;
    teamLeaderId: number;
    teamId: number;
    teamIsAutoConfirm: boolean;
};

export function TeamInformation({
    teamName,
    teamMembers,
    teamLastTotalExpenses,
    teamThisTotalExpenses,
    teamInfo,
    teamRankRealtime,
    teamLeaderId,
    teamId,
    teamIsAutoConfirm,
}: TeamInformationProps) {
    const dispatch = useDispatch();
    const [memberId, setMemberId] = useState(0);

    useEffect(() => {
        const cookieData = getCookie('memberId');

        if (typeof cookieData === 'number') {
            setMemberId(cookieData);
        }
    }, []);

    return (
        <div className="teamTeamInfo contentCard">
            <div className="teamTeamInfo__title contentCard__title">
                <div className="teamTeamInfo__title-text contentCard__title-text">
                    <div>TEAM INFO</div>
                    {/* SETTING 버튼 -> 추후 팀장만 보이게끔 조건부 렌더링 */}
                    {memberId === teamLeaderId ? (
                        <button
                            className="teamTeamInfo__title-text-button button"
                            onClick={() => {
                                dispatch(
                                    openTeamSettingModal({
                                        teamId,
                                        memberId,
                                        teamName,
                                        teamInfo,
                                        members: teamMembers,
                                        teamIsAutoConfirm,
                                    }),
                                );
                            }}
                        >
                            SETTING
                        </button>
                    ) : (
                        <button
                            className="teamTeamInfo__title-text-button button"
                            onClick={() => {
                                dispatch(
                                    openTeamLeaveModal({
                                        teamId: teamId,
                                        memberId: memberId,
                                    }),
                                );
                            }}
                        >
                            탈퇴
                        </button>
                    )}
                </div>
            </div>
            <div className="teamTeamInfo__top">
                <div className="teamTeamInfo__top-name">
                    <div className="teamTeamInfo__top-name-title">{teamName}</div>
                    <div className="teamTeamInfo__top-name-head">
                        <img src="assets/image/person.svg" width={20} />
                        <div>{teamMembers.length}</div>
                    </div>
                </div>
                <div className="teamTeamInfo__top-ranking">
                    {teamRankRealtime !== 0 ? (
                        <>
                            주간 랭킹 {teamRankRealtime}
                            <span className="unit">위</span>
                        </>
                    ) : (
                        <>순위 없음</>
                    )}
                </div>
            </div>
            <div className="teamTeamInfo__middle">
                <div className="teamTeamInfo__middle-prev">
                    <div className="teamTeamInfo__middle-prev-title subtitle">지난주</div>
                    <div className="teamTeamInfo__middle-prev-value value">
                        {teamLastTotalExpenses.toLocaleString()}
                        <span className="unit">원</span>
                    </div>
                </div>
                <div className="teamTeamInfo__middle-pres">
                    <div className="teamTeamInfo__middle-pres-title subtitle">이번주</div>
                    <div className="teamTeamInfo__middle-pres-value value">
                        {teamThisTotalExpenses.toLocaleString()}
                        <span className="unit">원</span>
                    </div>
                </div>
                <div className="teamTeamInfo__middle-ratio">
                    <div className="teamTeamInfo__middle-ratio-title subtitle">절감률</div>
                    <div className="teamTeamInfo__middle-ratio-value value">
                        {isNaN(teamThisTotalExpenses / teamLastTotalExpenses)
                            ? '0.0'
                            : teamThisTotalExpenses / teamLastTotalExpenses === Infinity
                            ? '-'
                            : (100 - (teamThisTotalExpenses / teamLastTotalExpenses) * 100).toFixed(1)}
                        <span className="unit">%</span>
                    </div>
                </div>
            </div>
            <div className="teamTeamInfo__bottom">
                <div className="teamTeamInfo__bottom-desc">{teamInfo ?? '팀 소개말이 없습니다.'}</div>
            </div>
        </div>
    );
}
