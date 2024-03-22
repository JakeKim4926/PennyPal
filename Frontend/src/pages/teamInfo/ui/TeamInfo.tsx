import { PageHeader } from '../../../shared';
import { TeamInformation } from './TeamInfomation/TeamInformation';
import { TeamInfoMember } from './TeamInfoMember/TeamInfoMember';
import { TeamInfoTeamExpenditure } from './TeamInfoTeamExpenditure/TeamInfoTeamExpenditure';

export function TeamInfo() {
    return (
        <div className="container teamInfo__container">
            <div className="teamInfo">
                <PageHeader page="teamInfo" />
                <TeamInformation />
                <TeamInfoMember />
                <TeamInfoTeamExpenditure />
            </div>
        </div>
    );
}
