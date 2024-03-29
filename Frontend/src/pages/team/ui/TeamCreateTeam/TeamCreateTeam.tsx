import React from 'react';
import { useState, useEffect, useRef } from 'react';
import { scrollTeamCreateArea } from '../../model/scrollTeamCreateArea';
import { useDispatch } from 'react-redux';
import { createGroup } from '../../api/createGroup';
import { checkTeamName } from '../../model';
import { setTeamInfo } from '@/pages/teamRouting/model/setTeamInfo';
import { getTeamInfo } from '@/pages/teamRouting/api/getTeamInfo';
import { getCookie } from '@/shared';

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

    // teamDto: 팀 생성에 사용되는 데이터를 저장할 객체
    const teamDto = {
        teamName: '',
        teamIsAutoConfirm: false,
        teamLeaderId: getCookie('memberId'), // 추후작업: 로그인된 유저 id를 기본값으로
        teamInfo: '',
    };

    // registName: dto에 팀명 저장
    function registName(name: string) {
        teamDto.teamName = name;
    }

    // registinfo: dto에 팀 소개 저장
    function registInfo(info: string) {
        teamDto.teamInfo = info;
    }

    // registConfirm: dto에 팀원 가입 자동 승인 여부 저장
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
            <NameArea moveNext={() => moveNext(contentRef, 2)} registName={registName} />
            <DescArea
                moveBack={() => moveNext(contentRef, 1)}
                moveNext={() => moveNext(contentRef, 3)}
                registInfo={registInfo}
            />
            <ConfirmArea moveNext={() => moveNext(contentRef, 4)} registConfirm={registConfirm} teamDto={teamDto} />
            <LastArea />
        </div>
    );
}

type NameAreaProps = {
    moveNext: () => void;
    registName: (name: string) => void;
};

function NameArea({ moveNext, registName }: NameAreaProps) {
    enum VALIDATION_CHECK {
        PASS = 'PASS',
        LENGTH = 'LENGTH',
        CHAR = 'CHAR',
        VALID = 'VALID',
        DUP = 'DUP',
    }

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
                setCheck(VALIDATION_CHECK.LENGTH);
            } else if (!regex.test(newValue)) {
                setCheck(VALIDATION_CHECK.CHAR);
            } else {
                setCheck(VALIDATION_CHECK.VALID);
            }
        }, 300);
    }

    async function handleCheck() {
        // res: 팀명 중복 체크 결과
        const res = await checkTeamName(nameRef.current!.value);

        if (!res.data.data) {
            // 1. 중복이 아니라면
            setCheck(VALIDATION_CHECK.PASS);
        } else if (res.data.data) {
            // 2. 중복이라면
            setCheck(VALIDATION_CHECK.DUP);
        }
    }

    return (
        <div className="teamCreateTeam__content-inner">
            <div className="teamCreateTeam__content-inner-second">
                <div className="teamCreateTeam__content-inner-second-name">
                    <div>팀명을 입력해주세요</div>
                    <div className="teamCreateTeam__content-inner-second-name-input">
                        <input
                            type="text"
                            placeholder="팀명"
                            onChange={handleChange}
                            ref={nameRef}
                            maxLength={20}
                            onKeyDown={(e) => {
                                if (e.key === 'Enter') {
                                    if (check === VALIDATION_CHECK.VALID) {
                                        handleCheck();
                                    } else if (check === VALIDATION_CHECK.PASS) {
                                        moveNext();
                                        registName(nameRef.current!.value);
                                    }
                                }
                            }}
                        ></input>
                        <button
                            className={`teamCreateTeam__content-inner-second-name-input-button ${
                                check !== 'VALID' ? 'button-disabled' : 'button'
                            }`}
                            disabled={check !== 'VALID'}
                            onClick={() => {
                                handleCheck();
                            }}
                        >
                            중복 확인
                        </button>
                    </div>
                    <NameCheck state={check} />
                </div>
            </div>
            <button
                disabled={check !== VALIDATION_CHECK.PASS}
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
    moveBack: () => void;
    moveNext: () => void;
    registInfo: (info: string) => void;
};

function DescArea({ moveBack, moveNext, registInfo }: DescAreaProps) {
    enum VALIDATION_CHECK {
        VALID = 'VALID',
        INVALID = 'INVALID',
    }

    const [check, setCheck] = useState(VALIDATION_CHECK.VALID);
    const inputRef = useRef<HTMLInputElement>(null);

    // timer: 디바운싱에 사용할 타이머
    let timer: NodeJS.Timeout | null = null;

    // handleChange: 팀 소개 입력란의 value가 변할 때 동작
    function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
        // newValue: 입력된 문자열
        const newValue = e.target.value;

        // 1. 타이머가 존재할 때 타이머 제거
        if (timer) {
            clearTimeout(timer);
        }

        // 2. 타이머에 새로운 타이머 부착
        timer = setTimeout(() => {
            if (newValue.length > 40) {
                setCheck(VALIDATION_CHECK.INVALID);
            } else {
                if (check !== VALIDATION_CHECK.VALID) {
                    setCheck(VALIDATION_CHECK.VALID);
                }
            }
        }, 300);
    }

    return (
        <div className="teamCreateTeam__content-inner">
            <button
                onClick={() => {
                    moveBack();
                }}
            >
                이전으로
            </button>
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
                            onKeyDown={(e) => {
                                if (e.key === 'Enter') {
                                    registInfo(inputRef.current!.value.trim());
                                    moveNext();
                                }
                            }}
                        ></input>
                    </div>
                </div>
            </div>
            <button
                disabled={check === VALIDATION_CHECK.INVALID}
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
    teamDto: Object;
};

function ConfirmArea({ registConfirm, moveNext, teamDto }: ConfirmArea) {
    const dispatch = useDispatch();

    // handleRegist: 팀 생성 버튼 눌렀을 때동작
    async function handleRegist(value: boolean) {
        registConfirm(value);

        // res: 팀 생성 결과 반환
        const res = await createGroup(teamDto);

        // 1. 정상적으로 팀이 생성됐을 때
        if (res.status === 200) {
            // moveNext: 다음 페이지로 이동
            moveNext();

            // handleRoute: 일정시간 후 팀 페이지 전환
            handleRoute();
        }
    }

    // handleRoute: 팀 가입 여부를 변경해 내 팀 상세 페이지로 라우트
    async function handleRoute() {
        const res = await getTeamInfo('/team/3429');
        setTimeout(() => {
            dispatch(setTeamInfo(res.data.data));
        }, 3500);
    }

    return (
        <div className="teamCreateTeam__content-inner">
            <div className="teamCreateTeam__content-inner-second">
                <div className="teamCreateTeam__content-inner-second-name">
                    <div className="confirm">팀원 가입 신청을 자동으로 승인할까요?</div>
                    <div className="teamCreateTeam__content-inner-second-name-buttons">
                        <button
                            className="teamCreateTeam__content-inner-second-name-buttons-button yes"
                            onClick={() => {
                                handleRegist(true);
                            }}
                        >
                            네
                        </button>
                        <button
                            className="teamCreateTeam__content-inner-second-name-buttons-button no"
                            onClick={() => {
                                handleRegist(false);
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
            <div className="teamCreateTeam__content-inner-last">
                <div>팀 생성이 완료됐어요!</div>
                <div>잠시 후 내 팀 페이지로 이동합니다</div>
            </div>
        </div>
    );
}
