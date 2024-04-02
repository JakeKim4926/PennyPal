import { useDispatch } from 'react-redux';
import { openTeamDetailModal } from '@/pages/team/model/openTeamDetailModal';
import { TeamTeamListItemProps } from '@/entities/';

export function TeamTeamListItem({
    teamId,
    name,
    head,
    leader,
    description,
    teamIsAutoConfirm,
}: TeamTeamListItemProps) {
    const dispatch = useDispatch();

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
                <button
                    className="teamTeamListItem__apply-button button"
                    onClick={() => {
                        dispatch(openTeamDetailModal({ teamId: teamId, teamIsAutoConfirm: teamIsAutoConfirm }));
                    }}
                >
                    상세 정보
                </button>
            </div>
        </div>
    );
}
