import { customAxios } from '@/shared';
import { useDispatch } from 'react-redux';
import { closeTeamLeaveModal } from '../../model/openTeamLeaveModal';

type TeamLeaveModal = {
    teamId: number;
    memberId: number;
};

export function TeamLeaveModal({ teamId, memberId }: TeamLeaveModal) {
    const dispatch = useDispatch();

    return (
        <div className="modalContainer">
            <div className="teamLeaveModal">
                {/* <div className="teamLeaveModal__top"></div> */}
                <div className="teamLeaveModal__middle">정말 팀을 탈퇴하시겠습니까?</div>
                <div className="teamLeaveModal__bottom">
                    <div className="teamLeaveModal__bottom-buttons">
                        <button
                            className="button"
                            onClick={async () => {
                                const postDto = { teamId, memberId };
                                const res = await customAxios.post('/team/leave', postDto);
                            }}
                        >
                            탈퇴하기
                        </button>
                        <button
                            className="button"
                            onClick={() => {
                                dispatch(closeTeamLeaveModal());
                            }}
                        >
                            취소
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
