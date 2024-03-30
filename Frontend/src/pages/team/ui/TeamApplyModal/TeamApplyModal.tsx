import React, { useCallback, useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { closeTeamDetailModal } from '../../model';
import { getTeamDetail } from '../../api/getTeamDetail';

type TeamDetailModalProps = {
    teamId: any;
};

type TeamInfo = {
    teamName?: string;
    teamInfo?: string;
    teamLeaderNickname?: 'string';
    lastRank?: number;
};
export function TeamApplyModal({ teamId }: TeamDetailModalProps) {
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

    // fetchData: 팀 상세 정보를 가져오는 함수. useCallback을 활용해 같은 함수가 재선언되지 않도록 메모이제이션
    const fetchData = useCallback((teamId: number) => getTeamDetail(teamId), []);

    useEffect(() => {
        fetchData(teamId)
            .then((res) => {
                if (res.data.code === 200) {
                    setTeamInfo(res.data.data);
                    console.log(res.data.data);
                }
            })
            .catch((err) => {
                console.log(err);
            });
    }, [teamId]);

    return (
        <div className="modalContainer">
            <div className="teamApplyModal">
                <div className="teamApplyModal__top">
                    <div className="teamApplyModal__top-title">팀 정보</div>
                    <button className="teamApplyModal__top-button"></button>
                </div>
                <div className="teamApplyModal__middle">
                    <div className="teamApplyModal__middle-content">{teamInfo ? teamInfo!.teamName : null}</div>
                </div>
                <div className="teamApplyModal__bottom">
                    <div className="teamApplyModal__buttons">
                        <button>신청</button>
                        <button>취소</button>
                    </div>
                </div>
            </div>
        </div>
    );
}
