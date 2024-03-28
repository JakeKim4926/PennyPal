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

type teamInfo = {
    teamName: string;
    teamIsAutoConfirm: boolean;
    teamLeaderId: number | null;
    teamInfo: string;
};

function Content() {
    useEffect(() => {
        if (contentRef.current) {
            console.dir(contentRef.current.offsetWidth);
        }
    });
    const contentRef = useRef<HTMLDivElement>(null);

    // moveNext: 다음 페이지로 넘기는 함수
    const moveNext = scrollTeamCreateArea;

    // teamDto: 팀 생성에 사용되는 데이터를 저장할 객체
    const teamDto = {
        teamName: '',
        teamIsAutoConfirm: false,
        teamLeaderId: '', // 추후작업: 로그인된 유저 id를 기본값으로
        teamInfo: '',
    };

    function registName(name: string) {
        teamDto.teamName = name;
    }

    function registInfo(info: string) {
        teamDto.teamInfo = info;
    }

    function registConfirm(confirm: boolean) {
        teamDto.teamIsAutoConfirm = confirm;
    }

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
            <NameArea moveNext={() => moveNext(contentRef, 2)} teamDto={teamDto} registName={registName} />
            <DescArea moveNext={() => moveNext(contentRef, 3)} teamDto={teamDto} registInfo={registInfo} />
            <ConfirmArea moveNext={() => moveNext(contentRef, 4)} registConfirm={registConfirm} teamInfo={teamDto} />
        </div>
    );
}

type NameAreaProps = {
    moveNext: () => void;
    teamDto: Object;
    registName: (name: string) => void;
};

function NameArea({ moveNext, teamDto, registName }: NameAreaProps) {
    const PASS = 'PASS';
    const LENGTH = 'LENGTH';
    const CHAR = 'CHAR';
    const VALID = 'VALID';
    const DUP = 'DUP';

    const [check, setCheck] = useState('LENGTH');
    const nameRef = useRef<HTMLInputElement>(null);

    let timer: NodeJS.Timeout | null = null;
    const regex = /^[가-힣|a-z|A-Z|0-9|]+$/;

    function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
        const newValue = e.target.value;

        if (timer) {
            clearTimeout(timer);
        }

        timer = setTimeout(() => {
            if (newValue.length < 4 || newValue.length > 20) {
                setCheck(LENGTH);
            } else if (!regex.test(newValue)) {
                setCheck(CHAR);
            } else {
                setCheck(VALID);
            }
        }, 300);
    }

    return (
        <div className="teamCreateTeam__content-inner">
            <div className="teamCreateTeam__content-inner-second">
                <div className="teamCreateTeam__content-inner-second-name">
                    <div>팀명을 입력해주세요</div>
                    <div className="teamCreateTeam__content-inner-second-name-input">
                        <input type="text" placeholder="팀명" onChange={handleChange} ref={nameRef}></input>
                        <button
                            className={`teamCreateTeam__content-inner-second-name-input-button ${
                                check !== 'VALID' ? 'button-disabled' : 'button'
                            }`}
                            disabled={check !== 'VALID'}
                            onClick={() => {
                                // 추후 작성 예정
                                // 메일 중복체크하는 API 호출

                                if (nameRef.current!.value) {
                                    // 중복이 아니라면
                                    setCheck(PASS);
                                } else {
                                    // 중복이라면
                                    setCheck(DUP);
                                }
                            }}
                        >
                            중복 확인
                        </button>
                    </div>
                    <NameCheck state={check} />
                </div>
            </div>
            <button
                disabled={check !== PASS}
                onClick={() => {
                    moveNext();
                    registName(nameRef.current!.value);
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
    const text: () => string | JSX.Element = () => {
        switch (state) {
            case 'CHAR':
                return '팀명은 한글, 숫자, 영문만 허용됩니다.';
            case 'LENGTH':
                return '팀명은 4자 이상, 20자 이하만 허용됩니다.';
            case 'VALID':
                return <span className="green">유효한 팀명입니다. 중복체크를 진행해주세요.</span>;
            case 'PASS':
                return <span className="green">사용 가능한 팀명입니다.</span>;
            case 'DUP':
                return '중복된 팀명입니다.';
            default:
                return '팀명은 한글, 숫자, 영문만 허용됩니다.';
        }
    };

    return <div className="teamCreateTeam__content-inner-second-name">{text()}</div>;
}

type DescAreaProps = {
    moveNext: () => void;
    teamDto: Object;
    registInfo: (info: string) => void;
};

function DescArea({ moveNext, teamDto, registInfo }: DescAreaProps) {
    const [VALID, INVALID] = ['VALID', 'INVALID'];
    const [check, setCheck] = useState(VALID);
    const inputRef = useRef<HTMLInputElement>(null);

    let timer: NodeJS.Timeout | null = null;
    function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
        const newValue = e.target.value;

        if (timer) {
            clearTimeout(timer);
        }

        timer = setTimeout(() => {
            if (newValue.length > 20) {
                setCheck(INVALID);
            } else {
                if (check !== VALID) {
                    setCheck(VALID);
                }
            }
        }, 300);
    }

    return (
        <div className="teamCreateTeam__content-inner">
            <div className="teamCreateTeam__content-inner-second">
                <div className="teamCreateTeam__content-inner-second-name">
                    <div>우리 팀을 한 줄로 소개해주세요</div>
                    <div className="teamCreateTeam__content-inner-second-name-input">
                        <input
                            type="text"
                            placeholder="소개 (40자 이내)"
                            onChange={handleChange}
                            maxLength={40}
                            ref={inputRef}
                        ></input>
                    </div>
                </div>
            </div>
            <button
                disabled={check === INVALID}
                onClick={() => {
                    registInfo(inputRef.current!.value.trim());
                    moveNext();
                }}
            >
                다음으로
            </button>
        </div>
    );
}

type ConfirmArea = {
    registConfirm: (confirm: boolean) => void;
    moveNext: () => void;
    teamInfo: Object;
};

function ConfirmArea({ registConfirm, moveNext, teamInfo }: ConfirmArea) {
    return (
        <div className="teamCreateTeam__content-inner">
            <div className="teamCreateTeam__content-inner-second">
                <div className="teamCreateTeam__content-inner-second-name">
                    <div className="confirm">팀원 가입 신청을 자동으로 승인할까요?</div>
                    <div className="teamCreateTeam__content-inner-second-name-buttons">
                        <button
                            className="teamCreateTeam__content-inner-second-name-buttons-button yes"
                            onClick={() => {
                                registConfirm(true);
                                console.log(teamInfo);
                            }}
                        >
                            네
                        </button>
                        <button
                            className="teamCreateTeam__content-inner-second-name-buttons-button no"
                            onClick={() => {
                                registConfirm(false);
                                console.log(teamInfo);
                            }}
                        >
                            아니오
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}

function LastArea() {
    return (
        <div className="teamCreateTeam__content-inner">
            <div className="teamCreateTeam__content-inner-second">
                <div className="teamCreateTeam__content-inner-second-name">
                    <div className="confirm">팀원 가입 신청을 자동으로 승인할까요?</div>
                    <div className="teamCreateTeam__content-inner-second-name-buttons">
                        <button className="teamCreateTeam__content-inner-second-name-buttons-button yes">네</button>
                        <button className="teamCreateTeam__content-inner-second-name-buttons-button no">아니오</button>
                    </div>
                </div>
            </div>
        </div>
    );
}
