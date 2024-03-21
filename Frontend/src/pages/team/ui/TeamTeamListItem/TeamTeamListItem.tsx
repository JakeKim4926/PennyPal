import React from 'react';
import { useDispatch } from 'react-redux';
import { openApplyModal } from '../../model/openApplyModal';
import { createGroup } from '../../model/createGroup';

type TeamTeamListItemProps = {
    name: string;
    head: number;
    leader: string;
    description: string;
};

export function TeamTeamListItem({ name, head, leader, description }: TeamTeamListItemProps) {
    return (
        <div className="teamTeamListItem">
            <div className="teamTeamListItem__info">
                <div className="teamTeamListItem__info-first">
                    <div className="teamTeamListItem__info-first-name">{name}</div>
                    <div className="teamTeamListItem__info-first-head">
                        <img src="assets/image/person.svg" width={15} />
                        <div>{head}</div>
                    </div>
                    <div className="teamTeamListItem__info-first-leader">팀장 : {leader}</div>
                </div>
                <div className="teamTeamListItem__info-desc">{description}</div>
            </div>
            <div className="teamTeamListItem__apply">
                <ApplyButton />
            </div>
        </div>
    );
}

function ApplyButton() {
    const dispatch = useDispatch();
    const dto = {
        teamName: '팀이름',
        teamIsAutoConfirm: false,
        teamLeaderId: 1,
        teamInfo: '팀소개',
    };

    return (
        <button
            className="teamTeamListItem__apply-button button"
            // onClick={() => {
            //     dispatch(openApplyModal({ value: '123' }));
            // }}
            onClick={async () => {
                const a = await createGroup(dto);
                console.log(a);
                console.log(process.env);
            }}
        >
            가입신청
        </button>
    );
}
