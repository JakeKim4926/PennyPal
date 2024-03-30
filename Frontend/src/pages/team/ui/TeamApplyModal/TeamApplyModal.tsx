import React, { useCallback, useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { closeTeamDetailModal, registGroup } from '../../model';
import { getTeamDetail } from '../../api/getTeamDetail';

type TeamDetailModalProps = {
    team: any;
};

type TeamInfo = {
    teamName?: string;
    teamInfo?: string;
    teamLeaderNickname?: 'string';
    lastRank?: number;
};

export function TeamApplyModal({ team }: TeamDetailModalProps) {
    const dispatch = useDispatch();
    const [teamInfo, setTeamInfo] = useState<TeamInfo | null>(null);

    useEffect(() => {
        function handleClick(e: MouseEvent) {
            e.stopPropagation();
            if (e.target instanceof Element) {
                if ([...e.target.classList].some((it) => it === 'modalContainer')) {
                    dispatch(closeTeamDetailModal());
                }
            }
        }
        window.addEventListener('click', handleClick);

        return () => {
            window.removeEventListener('click', handleClick);
        };
    }, []);

    useEffect(() => {
        getTeamDetail(team.teamId)
            .then((res) => {
                if (res.data.code === 200) {
                    setTeamInfo(res.data.data);
                }
            })
            .catch((err) => {
                console.log(err);
            });
    }, [team.teamId]);

    return (
        <div className="modalContainer">
            <div className="teamDetailModal">
                <div className="teamDetailModal__top">
                    <div className="teamDetailModal__top-title">TEAM INFO</div>
                </div>
                <div className="teamDetailModal__middle">
                    <div className="teamDetailModal__middle-content">
                        {teamInfo ? (
                            <>
                                <div className="teamDetailModal__middle-content-item">
                                    <div className="key">팀명</div>
                                    <div className="value">{teamInfo.teamName}</div>
                                </div>
                                <div className="teamDetailModal__middle-content-item flex">
                                    <div className="key">팀 소개</div>
                                    <div className="value">
                                        {teamInfo.teamInfo ? teamInfo.teamInfo : '팀 소개말이 없습니다.'}
                                    </div>
                                </div>
                                <div className="teamDetailModal__middle-content-item">
                                    <div className="key">팀장</div>
                                    <div className="value">{teamInfo.teamLeaderNickname}</div>
                                </div>
                                <div className="teamDetailModal__middle-content-item">
                                    <div className="key">주간 랭킹</div>
                                    <div className="value">{teamInfo.lastRank}</div>
                                </div>
                            </>
                        ) : null}
                    </div>
                </div>
                <div className="teamDetailModal__bottom">
                    <div className="teamDetailModal__bottom-buttons">
                        <button
                            className="button"
                            onClick={async () => {
                                const res = await registGroup({ teamId: team.teamId, memberId: 3859 });
                                console.log(res);
                            }}
                        >
                            가입 신청
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
