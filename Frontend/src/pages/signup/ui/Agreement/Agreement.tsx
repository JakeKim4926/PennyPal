import { useState } from 'react';
import { Button } from '../../../../shared';
import React from 'react';
import { useDispatch } from 'react-redux';
import { setSignUpStep } from '../../model/signUpStepReducer';

export function Agreement() {
    const [isChecked, setIsChecked] = useState(false);
    const dispatch = useDispatch();
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setIsChecked(e.target.checked);
    };
    const handleNext = () => {
        // 리덕스 상태관리 다음으로 넘어감.
        dispatch(setSignUpStep(1));
    };

    return (
        <div className="agreement">
            <div className="agreement__top">
                <div className="agreement__top-title">Agreement</div>
                <div className="agreement__top-content">
                    <div className="agreement__top-content-top">
                        <div>서비스 이용을 위한</div>
                        <div>사용자의 계좌내역 수집 및 활용에 동의합니다.</div>
                    </div>
                    <div className="agreement__top-content-middle">
                        <div>
                            <p>
                                <img src="assets/image/icons_mini/handShake.svg" />
                                수집항목
                            </p>
                            <ul>
                                <li>카드 계좌 및 사용내역</li>
                            </ul>
                        </div>
                    </div>
                    <div className="agreement__top-content-bottom">
                        <div>
                            <p>
                                <img src="assets/image/icons_mini/handShake.svg" />
                                이용목적
                            </p>
                            <ul>
                                <li>
                                    서비스 사용에 필요한 지출내역 분석을 위해 활용하며, 목적 외의 용도로는 사용하지
                                    않습니다.
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div className="agreement__top-input">
                    <input type="checkbox" id="myCheckbox" checked={isChecked} onChange={handleChange} />
                    <label htmlFor="myCheckbox">개인정보 수집 및 활용을 위한 위 사항에 동의합니다.</label>
                </div>
            </div>
            <button
                className="agreement-button button"
                disabled={!isChecked}
                onClick={() => {
                    if (isChecked) {
                        handleNext();
                    }
                }}
            >
                NEXT
            </button>
            {/* <Button child={'NEXT'} color={'light'} disabled={!isChecked} onClick={handleNext} /> */}
        </div>
    );
}
