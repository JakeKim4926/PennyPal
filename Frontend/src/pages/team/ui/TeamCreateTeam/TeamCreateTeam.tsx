import React from 'react';
import { useState, useEffect, useRef } from 'react';
import { scrollTeamCreateArea } from '../../model/scrollTeamCreateArea';

export function TeamCreateTeam() {
    return (
        <div className="teamCreateTeam contentCard">
            <div className="teamCreateTeam__title contentCard__title">
                <div className="teamCreateTeam__title-text contentCard__title-text">CREATE TEAM</div>
            </div>
            <Content />
        </div>
    );
}

function Content() {
    useEffect(() => {
        if (contentRef.current) {
            console.dir(contentRef.current.offsetWidth);
        }
    });
    const contentRef = useRef<HTMLDivElement>(null);

    // moveNext: 다음 페이지로 넘기는 함수
    const moveNext = scrollTeamCreateArea;

    return (
        <div className="teamCreateTeam__content" ref={contentRef}>
            <div className="teamCreateTeam__content-inner">
                <div className="teamCreateTeam__content-inner-top">팀을 생성해 함께할 동료들을 모아보아요!</div>
                <button
                    className="teamCreateTeam__content-inner-nextButton"
                    onClick={() => {
                        moveNext(contentRef, 1);
                    }}
                >
                    팀 생성하기
                </button>
            </div>
            <NameArea moveNext={() => moveNext(contentRef, 2)} />
            <div className="teamCreateTeam__content-inner">
                <div className="teamCreateTeam__content-inner-second">
                    <div className="teamCreateTeam__content-inner-second-name">
                        <div>우리 팀을 한 줄로 소개해주세요</div>
                        <input type="text" placeholder="소개 (40자 이내)"></input>
                    </div>
                </div>
            </div>
            <div className="teamCreateTeam__content-inner">
                <div className="teamCreateTeam__content-inner-second">
                    <div className="teamCreateTeam__content-inner-second-name">
                        <div>우리 팀을 한 줄로 소개해주세요</div>
                        <input type="text"></input>
                    </div>
                </div>
                <button
                    onClick={() => {
                        moveNext(contentRef, 2);
                    }}
                >
                    다음으로
                </button>
            </div>
        </div>
    );
}

type NameAreaProps = {
    moveNext: () => void;
};

function NameArea({ moveNext }: NameAreaProps) {
    const [check, setCheck] = useState('INVALID');

    let timer: any = '';
    const regex = /^[가-힣|a-z|A-Z|0-9|]+$/;

    function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
        const newValue = e.target.value;

        if (timer) {
            clearTimeout(timer);
        }

        timer = setTimeout(() => {
            console.log(newValue, newValue.length);
            if (newValue.length >= 4 && newValue.length <= 20 && regex.test(newValue)) {
                setCheck('VALID');
            } else {
                if (check !== 'INVALID') {
                    setCheck('INVALID');
                }
            }
        }, 500);
    }

    return (
        <div className="teamCreateTeam__content-inner">
            <div className="teamCreateTeam__content-inner-second">
                <div className="teamCreateTeam__content-inner-second-name">
                    <div>팀명을 입력해주세요</div>
                    <div className="teamCreateTeam__content-inner-second-name-input">
                        <input type="text" placeholder="팀명" onChange={handleChange}></input>
                        <button
                            className={`teamCreateTeam__content-inner-second-name-input-button ${
                                check !== 'VALID' ? 'button-disabled' : 'button'
                            }`}
                        >
                            중복 확인
                        </button>
                    </div>
                    <NameCheck state={check} />
                </div>
            </div>
            <button
                disabled={check !== 'PASS'}
                onClick={() => {
                    moveNext();
                }}
            >
                다음으로
            </button>
        </div>
    );
}

type NameCheckProps = {
    state: string;
};
function NameCheck({ state }: NameCheckProps) {
    switch (state) {
        case 'INVALID':
            return <div className="teamCreateTeam__content-inner-second-name">유효하지 않은 팀명입니다.</div>;
        case 'VALID':
            return <div className="teamCreateTeam__content-inner-second-name">유효한 팀명입니다.</div>;
        case 'PASS':
            return <div className="teamCreateTeam__content-inner-second-name">사용 가능한 팀명입니다.</div>;
    }
}
