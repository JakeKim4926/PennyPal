import { deleteTeam } from '@/pages/teamInfo/index';

type TeamSettingModal = {
    teamId: number;
    memberId: number;
};
export function TeamSettingModal({ teamId, memberId }: TeamSettingModal) {
    return (
        <div className="modalContainer">
            <div>
                <button
                    onClick={async () => {
                        const deleteDto = { teamId, memberId };
                        const res = await deleteTeam(deleteDto);
                        console.log(res);
                    }}
                >
                    팀삭제하기
                </button>
            </div>
        </div>
    );
}
