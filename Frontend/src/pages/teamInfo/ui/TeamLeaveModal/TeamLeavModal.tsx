import { customAxios } from '@/shared';
import { useDispatch } from 'react-redux';
import { closeTeamLeaveModal } from '../../model/openTeamLeaveModal';
import { forceRender, setTeamInfo } from '@/pages/teamRouting';
import Swal from 'sweetalert2';
import { useEffect } from 'react';

type TeamLeaveModal = {
    teamId: number;
    memberId: number;
};

export function TeamLeaveModal({ teamId, memberId }: TeamLeaveModal) {
    const dispatch = useDispatch();

    useEffect(() => {
        return () => {
            dispatch(forceRender());
        };
    }, []);
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
                                if (res.data.code === 200) {
                                    Swal.fire({
                                        title: '팀 탈퇴',
                                        text: '팀에서 탈퇴했습니다.\n팀 페이지를 벗어납니다.',
                                        icon: 'success',
                                    }).then(() => {
                                        dispatch(setTeamInfo(null));
                                        dispatch(closeTeamLeaveModal());
                                    });
                                }
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
