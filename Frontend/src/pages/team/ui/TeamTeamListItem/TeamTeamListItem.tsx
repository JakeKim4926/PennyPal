import React from 'react';

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
                <button className="teamTeamListItem__apply-button">가입신청</button>
            </div>
        </div>
    );
}
