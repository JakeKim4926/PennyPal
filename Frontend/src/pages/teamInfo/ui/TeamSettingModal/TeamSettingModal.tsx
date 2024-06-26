import {
    acceptRegist,
    banTeamMember,
    closeTeamSettingModal,
    deleteTeam,
    denyRegist,
    getTeamWaitingList,
    modifyTeamInfo,
} from '@/pages/teamInfo/index';
import { forceRender, setTeamInfo } from '@/pages/teamRouting';
import { getCookie } from '@/shared';
import { useEffect, useRef, useState } from 'react';

import { useDispatch } from 'react-redux';
import Swal from 'sweetalert2';

type TeamSettingModal = {
    teamId: number;
    memberId: number;
    teamName?: string;
    teamInfo?: string;
    members?: [];
    teamIsAutoConfirm?: boolean;
};

type Member = {
    memberId: number;
    memberLastTotalExpenses: number;
    memberNickname: string;
    memberThisTotalExpenses: number;
};

type WaitingMember = { memberId: number; memberNickname: string; memberMostCategory: string };

export function TeamSettingModal({
    teamId,
    memberId,
    teamName,
    teamInfo,
    members,
    teamIsAutoConfirm,
}: TeamSettingModal) {
    const [waitingList, setWaitingList] = useState<WaitingMember[]>([]);
    const [memberList, setMemberList] = useState<Member[]>(members!);

    const dispatch = useDispatch();

    const modalRef = useRef<HTMLDivElement>(null);
    const inputRef = useRef<HTMLInputElement>(null);
    const yesRef = useRef<HTMLButtonElement>(null);
    const noRef = useRef<HTMLButtonElement>(null);
    let isAutoConfirm = teamIsAutoConfirm;

    useEffect(() => {
        const postDto = {
            teamId,
            memberId,
        };

        // 1. 가입 승인 대기자 리스트 가져오기
        getTeamWaitingList(postDto).then((res) => {
            if (res.data.code === 200) {
                setWaitingList(res.data.data);
            }
        });

        function handleClick(e: MouseEvent) {
            if ([...(e.target as HTMLElement).classList].some((it) => it === 'modalContainer')) {
                closeModal();
            }
        }
        window.addEventListener('click', handleClick);

        return () => {
            dispatch(forceRender());
            window.removeEventListener('click', handleClick);
        };
    }, []);

    function closeModal(e?: MouseEvent) {
        modalRef.current?.classList.add('fadeOut');
        setTimeout(() => {
            dispatch(closeTeamSettingModal());
        }, 220);
    }

    return (
        <div className="modalContainer">
            <div className="teamSettingModal" ref={modalRef}>
                <div className="teamSettingModal__middle">
                    <div className="teamSettingModal__middle-info">
                        <div className="teamSettingModal__middle-info-teamName">
                            <div className="teamSettingModal__middle-info-teamName-key">팀명</div>
                            <div className="teamSettingModal__middle-info-teamName-value">{teamName}</div>
                        </div>
                        <div className="teamSettingModal__middle-info-teamInfo">
                            <div className="teamSettingModal__middle-info-teamInfo-key">팀소개</div>
                            <input
                                ref={inputRef}
                                className="teamSettingModal__middle-info-teamInfo-value"
                                defaultValue={teamInfo ?? '팀 소개말이 없습니다.'}
                                maxLength={40}
                            />
                        </div>
                        <div className="teamSettingModal__middle-info-teamIsAutoConfirm">
                            <div className="teamSettingModal__middle-info-teamIsAutoConfirm-key">
                                가입 자동 승인 여부
                            </div>
                            <div className="teamSettingModal__middle-info-teamIsAutoConfirm-value">
                                <button
                                    ref={yesRef}
                                    className={`${teamIsAutoConfirm && 'focus'}`}
                                    onClick={(e: any) => {
                                        e.target.classList.add('focus');
                                        noRef.current!.classList.remove('focus');
                                        isAutoConfirm = true;
                                    }}
                                >
                                    YES
                                </button>
                                <button
                                    ref={noRef}
                                    className={`${!teamIsAutoConfirm && 'focus'}`}
                                    onClick={(e: any) => {
                                        e.target.classList.add('focus');
                                        yesRef.current!.classList.remove('focus');
                                        isAutoConfirm = false;
                                    }}
                                >
                                    NO
                                </button>
                            </div>
                        </div>
                        <div className="teamSettingModal__middle-info-modify">
                            <button
                                className="teamSettingModal__middle-info-modify-button"
                                onClick={async () => {
                                    const dto = {
                                        memberId: getCookie('memberId'),
                                        teamIsAutoConfirm: isAutoConfirm,
                                        teamInfo: inputRef.current!.value,
                                    };
                                    const res = await modifyTeamInfo(dto, teamId);
                                    if (res.data.code === 200) {
                                        Swal.fire({
                                            title: '팀 정보 수정',
                                            text: '팀 정보 수정이 완료됐습니다.',
                                            icon: 'success',
                                        });
                                    }
                                }}
                            >
                                수정하기
                            </button>
                        </div>
                    </div>

                    <div className="teamSettingModal__middle-personnel">
                        <div className="teamSettingModal__middle-personnel-current">
                            <div>팀원 현황</div>
                            <hr />
                            <div className="teamSettingModal__middle-personnel-current-list">
                                {memberList?.map((member) => {
                                    return (
                                        <MemberListItem
                                            member={member}
                                            memberId={memberId}
                                            teamId={teamId}
                                            memberList={memberList}
                                            setMemberList={setMemberList}
                                        />
                                    );
                                })}
                            </div>
                        </div>
                        <div className="teamSettingModal__middle-personnel-waiting">
                            <div>가입 대기자</div>
                            <hr />
                            <div className="teamSettingModal__middle-personnel-waiting-list">
                                {waitingList.map((it: WaitingMember) => (
                                    <WaitingMemberListItem
                                        memberId={it.memberId}
                                        memberNickname={it.memberNickname}
                                        memberMostCategory={it.memberMostCategory}
                                        teamId={teamId}
                                        waitingMemberList={waitingList}
                                        setWaitingMemberList={setWaitingList}
                                        memberList={memberList}
                                        setMemberList={setMemberList}
                                    />
                                ))}
                            </div>
                        </div>
                    </div>
                </div>

                <div className="teamSettingModal__bottom">
                    <div className="teamSettingModal__bottom-buttons">
                        <button
                            onClick={async () => {
                                Swal.fire({
                                    title: '팀 삭제',
                                    text: '정말로 팀을 삭제할까요?',
                                    icon: 'warning',

                                    showCancelButton: true,
                                    confirmButtonText: '삭제',
                                    cancelButtonText: '취소',
                                }).then((step) => {
                                    if (step.isConfirmed) {
                                        const deleteDto = { teamId, memberId };
                                        deleteTeam(deleteDto)
                                            .then((res) =>
                                                Swal.fire({
                                                    title: '팀 삭제 완료',
                                                    text: '팀 삭제가 완료되었습니다.',
                                                }).then(() => {
                                                    dispatch(setTeamInfo(null));
                                                    dispatch(closeTeamSettingModal());
                                                }),
                                            )
                                            .catch((err) => err);
                                    }
                                });
                            }}
                        >
                            팀 삭제
                        </button>
                        <button
                            onClick={() => {
                                closeModal();
                            }}
                        >
                            나가기
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}

type MemberListItemProps = {
    member: Member;
    memberId: number;
    teamId: number;
    memberList: Member[];
    setMemberList: React.Dispatch<React.SetStateAction<Member[]>>;
};
function MemberListItem({ member, memberId, teamId, memberList, setMemberList }: MemberListItemProps) {
    return (
        <div className="memberListItem">
            <div className="memberListItem-name">{member.memberNickname}</div>
            {/* [조건부 렌더링] 로그인 유저(팀장)와 해당 memberId가 다를 경우에만 [추방] 버튼 활성화 */}
            {member.memberId !== memberId ? (
                <button
                    className="memberListItem-ban"
                    onClick={async () => {
                        const postDto = {
                            teamId: teamId,
                            teamLeaderId: memberId,
                            targetMemberId: member.memberId,
                        };

                        const res = await banTeamMember(postDto).catch((err) => err);

                        if (res.data.code === 200) {
                            const tmp = [...memberList].filter((it) => it.memberId !== postDto.targetMemberId);
                            setMemberList(tmp);
                            Swal.fire({
                                title: '팀원 추방',
                                text: '팀원을 추방했습니다.',
                                icon: 'success',
                            });
                        }
                    }}
                >
                    추방
                </button>
            ) : null}
        </div>
    );
}

type WaitingMemberListItemProps = {
    memberId: number;
    memberNickname: string;
    memberMostCategory: string | null;
    teamId: number;
    waitingMemberList: WaitingMember[];
    setWaitingMemberList: React.Dispatch<React.SetStateAction<WaitingMember[]>>;
    memberList: Member[];
    setMemberList: React.Dispatch<React.SetStateAction<Member[]>>;
};

function WaitingMemberListItem({
    memberId,
    memberNickname,
    memberMostCategory,
    teamId,
    waitingMemberList,
    setWaitingMemberList,
    memberList,
    setMemberList,
}: WaitingMemberListItemProps) {
    return (
        <div className="waitingMemberListItem">
            {}
            <div className="waitingMemberListItem-name">{memberNickname}</div>
            <div className="waitingMemberListItem-buttons">
                <button
                    className="waitingMemberListItem-buttons-button"
                    onClick={() => {
                        const dto = {
                            teamId: teamId,
                            memberId: memberId,
                        };
                        acceptRegist(dto)
                            .then((res: any) => {
                                if (res!.data.code === 200) {
                                    Swal.fire({
                                        title: '가입 승인',
                                        text: '가입 신청이 승인됐습니다.',
                                        icon: 'success',
                                    });

                                    const tmpWaitingMemberList = [...waitingMemberList].filter(
                                        (it) => it.memberId !== dto.memberId,
                                    );
                                    setWaitingMemberList(tmpWaitingMemberList);

                                    const tmpMemberList = [...memberList].filter((it) => it.memberId !== dto.memberId);
                                    setMemberList(tmpMemberList);
                                }
                            })
                            .catch((err) => {
                                Swal.fire({
                                    title: '가입 승인 실패',
                                    text: '팀원이 모두 채워졌습니다.',
                                    icon: 'warning',
                                });
                            });
                    }}
                >
                    승인
                </button>
                <button
                    className="waitingMemberListItem-buttons-button"
                    onClick={async () => {
                        const dto = {
                            teamId: teamId,
                            memberId: memberId,
                        };
                        const res = await denyRegist(dto).catch((err) => err);
                        if (res.data.code === 200) {
                            Swal.fire({
                                title: '가입 거절',
                                text: '가입 신청을 거절했습니다.',
                                icon: 'success',
                            });
                            const tmp = [...waitingMemberList].filter((it) => it.memberId !== dto.memberId);
                            setWaitingMemberList(tmp);
                        }
                    }}
                >
                    거절
                </button>
            </div>
        </div>
    );
}
