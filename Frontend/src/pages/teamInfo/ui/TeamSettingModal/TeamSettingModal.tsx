import {
    acceptRegist,
    banTeamMember,
    closeTeamSettingModal,
    deleteTeam,
    denyRegist,
    getTeamWaitingList,
    modifyTeamInfo,
} from '@/pages/teamInfo/index';
import { getCookie } from '@/shared';
import { memo, useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';

type TeamSettingModal = {
    teamId: number;
    memberId: number;
    teamName?: string;
    teamInfo?: string;
    members?: [];
};

type Member = {
    memberId: number;
    memberLastTotalExpenses: number;
    memberNickname: string;
    memberThisTotalExpenses: number;
};

export function TeamSettingModal({ teamId, memberId, teamName, teamInfo, members }: TeamSettingModal) {
    const [waitingList, setWaitingList] = useState([]);
    const [memberList, setMemberList] = useState<Member[]>(members!);
    const dispatch = useDispatch();

    useEffect(() => {
        const postDto = {
            teamId,
            memberId,
        };

        // if: 팀 가입이 수동 승인일 때
        // if (!teamIsAutoConfirm) {
        // 1. 가입 승인 대기자 리스트 가져오기
        getTeamWaitingList(postDto).then((res) => {
            if (res.data.code === 200) {
                setWaitingList(res.data.data);
            }
        });
        // }
    }, []);

    return (
        <div className="modalContainer">
            <div className="teamSettingModal">
                <div className="teamSettingModal__middle">
                    <div className="teamSettingModal__middle-info">
                        <div className="teamSettingModal__middle-info-teamName">
                            <div className="teamSettingModal__middle-info-teamName-key">팀명</div>
                            <div className="teamSettingModal__middle-info-teamName-value">{teamName}</div>
                        </div>
                        <div className="teamSettingModal__middle-info-teamInfo">
                            <div className="teamSettingModal__middle-info-teamInfo-key">팀소개</div>
                            <input
                                className="teamSettingModal__middle-info-teamInfo-value"
                                defaultValue={teamInfo ?? '팀 소개말이 없습니다.'}
                            />
                        </div>
                        <div className="teamSettingModal__middle-info-teamIsAutoConfirm">
                            <div className="teamSettingModal__middle-info-teamIsAutoConfirm-key">
                                가입 자동 승인 여부
                            </div>
                            <div className="teamSettingModal__middle-info-teamIsAutoConfirm-value">
                                <div>YES</div>
                                <div>NO</div>
                            </div>
                        </div>
                        <div className="teamSettingModal__middle-info-modify">
                            <button
                                className="teamSettingModal__middle-info-modify-button"
                                onClick={async () => {
                                    const dto = {
                                        memberId: getCookie('memberId'),
                                        teamIsAutoConfirm: true,
                                        teamInfo: '수정테스트!!',
                                    };
                                    const res = await modifyTeamInfo(dto, teamId);
                                    console.log(res);
                                }}
                            >
                                수정하기
                            </button>
                        </div>
                    </div>
                    <hr />
                    <div className="teamSettingModal__middle-personnel">
                        <div className="teamSettingModal__middle-personnel-current">
                            <div>팀원 현황</div>
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
                            <div className="teamSettingModal__middle-personnel-waiting-list">
                                {waitingList.map(
                                    (it: { memberId: number; memberNickname: string; memberMostCategory: string }) => (
                                        <WaitingMemberListItem
                                            memberId={it.memberId}
                                            memberNickname={it.memberNickname}
                                            memberMostCategory={it.memberMostCategory}
                                            teamId={teamId}
                                        />
                                    ),
                                )}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="teamSettingModal__bottom">
                    <div className="teamSettingModal__bottom-buttons">
                        <button
                            onClick={async () => {
                                const deleteDto = { teamId, memberId };
                                const res = await deleteTeam(deleteDto);
                            }}
                        >
                            팀삭제하기
                        </button>
                        <button
                            onClick={() => {
                                dispatch(closeTeamSettingModal());
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
                            const tmp = [...memberList].filter((it) => it.memberId !== memberId);

                            setMemberList(tmp);
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
};

function WaitingMemberListItem({ memberId, memberNickname, memberMostCategory, teamId }: WaitingMemberListItemProps) {
    return (
        <div className="waitingMemberListItem">
            {}
            <div className="waitingMemberListItem-name">{memberNickname}</div>
            <div className="waitingMemberListItem-buttons">
                <button
                    className="waitingMemberListItem-buttons-button"
                    onClick={async () => {
                        const dto = {
                            teamId: teamId,
                            memberId: memberId,
                        };
                        const res = await acceptRegist(dto).catch((err) => err);
                        console.log(res);
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
                        console.log(res);
                    }}
                >
                    거절
                </button>
            </div>
        </div>
    );
}
