import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { closeTeamDetailModal } from '../../model';

export function TeamApplyModal() {
    const dispatch = useDispatch();

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

    return (
        <div className="modalContainer">
            <div className="teamApplyModal">
                <div className="teamApplyModal__top">
                    <div className="teamApplyModal__top-title">가입 신청하기</div>
                    <button className="teamApplyModal__top-button"></button>
                </div>
                <div className="teamApplyModal__middle">
                    <div className="teamApplyModal__middle-content"></div>
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
