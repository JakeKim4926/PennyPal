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
                <div className="teamSettingModal__middle"></div>
                <div className="teamSettingModal__bottom">
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
    );
}
