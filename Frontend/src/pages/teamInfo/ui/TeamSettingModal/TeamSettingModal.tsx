import { deleteTeam, getTeamWaitingList } from '@/pages/teamInfo/index';
import { useEffect, useState } from 'react';

type TeamSettingModal = {
    teamId: number;
    memberId: number;
    teamName?: string;
    teamInfo?: string;
    members?: [];
};

export function TeamSettingModal({ teamId, memberId, teamName, teamInfo, members }: TeamSettingModal) {
    const [waitingList, setWaitingList] = useState([]);

    useEffect(() => {
        const postDto = {
            teamId,
            memberId,
        };

        // if: 팀 가입이 수동 승인일 때
        // if (!teamIsAutoConfirm) {
        // 1. 가입 승인 대기자 리스트 가져오기
        getTeamWaitingList(postDto).then((res) => {
            if (res.data.code === 200) {
                setWaitingList(res.data.data);
            }
        });
        // }
    }, []);

    return (
        <div className="modalContainer">
            <div className="teamSettingModal">
                <div className="teamSettingModal__top"></div>
                <div className="teamSettingModal__middle">
                    <div className="teamSettingModal__middle-teamName">
                        <div className="teamSettingModal__middle-teamName-key">팀명</div>
                        <div className="teamSettingModal__middle-teamName-value">{teamName}</div>
                    </div>
                    <div className="teamSettingModal__middle-teamInfo">
                        <div className="teamSettingModal__middle-teamInfo-key">팀소개</div>
                        <input
                            className="teamSettingModal__middle-teamInfo-value"
                            defaultValue={teamInfo ?? '팀 소개말이 없습니다.'}
                        />
                    </div>
                    <div className="teamSettingModal__middle-teamIsAutoConfirm">
                        <div className="teamSettingModal__middle-teamIsAutoConfirm-key">가입 자동 승인 여부</div>
                        <div className="teamSettingModal__middle-teamIsAutoConfirm-value">
                            <div>YES</div>
                            <div>NO</div>
                        </div>
                    </div>
                    <div className="teamSettingModal__middle-modify">
                        <button className="teamSettingModal__middle-modify-button">수정하기</button>
                    </div>
                    <div className="teamSettingModal__middle-personnel">
                        <div className="teamSettingModal__middle-personnel-current">현재 팀원</div>
                        <div className="teamSettingModal__middle-personnel-waiting">가입 대기자</div>
                    </div>
                </div>
                <div className="teamSettingModal__bottom">
                    <div className="teamSettingModal__bottom-buttons">
                        <button
                            onClick={async () => {
                                const deleteDto = { teamId, memberId };
                                const res = await deleteTeam(deleteDto);
                            }}
                        >
                            팀삭제하기
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
