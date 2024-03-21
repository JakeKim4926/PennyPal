import React from 'react';

export function TeamApplyModal() {
    return (
        <div className="modalContainer">
            <div className="teamApplyModal">
                <div className="teamApplyModal__top">
                    <div className="teamApplyModal__top-title">가입 신청하기</div>
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
