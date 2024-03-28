import { useState } from 'react';
import { Button } from '@/shared';
import React from 'react';
import { useDispatch } from 'react-redux';
import { setSignUpStep } from '@/pages/signup/model/signUpStepReducer';

export function Agreement() {
    const [isChecked, setIsChecked] = useState(false);
    const dispatch = useDispatch();
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setIsChecked(e.target.checked);
    };
    const handleNext = () => {
        // 회원가입 요청 API

        // 리덕스 상태관리 다음으로 넘어감.
        dispatch(setSignUpStep(2));
    };

    return (
        <div>
            <div>
                Agreement
                <div className="contentCard">
                    <div>
                        <p>서비스 이용을 위한</p>
                        <p>사용자의 계좌내역 수집 및 활용에 동의합니다.</p>
                    </div>
                    <div>
                        <img src="assets/image/icons_mini/handShake.svg" />
                        <div>
                            <p>수집항목</p>
                            <ul>
                                <li>카드 계좌 및 사용내역</li>
                            </ul>
                        </div>
                    </div>
                    <div>
                        <img src="assets/image/icons_mini/handShake.svg" />
                        <div>
                            <p>이용목적</p>
                            <ul>
                                <li>
                                    서비스 사용에 필요한 지출내역 분석을 위해 활용하며, 목적 외의 용도로는 사용하지
                                    않습니다.
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <input type="checkbox" id="myCheckbox" checked={isChecked} onChange={handleChange} />
                <label htmlFor="myCheckbox">개인정보 수집 및 활용을 위한 위 사항에 동의합니다.</label>
            </div>
            <Button child={'REGISTER'} color={'light'} disabled={!isChecked} onClick={handleNext} />
        </div>
    );
}
