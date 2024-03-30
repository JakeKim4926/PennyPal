import { customAxios } from '@/shared';

type TeamLeaveModal = {
    teamId: number;
    memberId: number;
};

export function TeamLeaveModal({ teamId, memberId }: TeamLeaveModal) {
    return (
        <div className="modalContainer">
            <button
                onClick={async () => {
                    const postDto = { teamId, memberId };
                    const res = await customAxios.post('/team/leave', postDto);
                    console.log(res);
                }}
            >
                네
            </button>
        </div>
    );
}
