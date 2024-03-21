import { PageHeader } from '../../../shared';
import { TeamInformation } from './TeamInfomation/TeamInformation';
import { TeamInfoMember } from './TeamInfoMember/TeamInfoMember';

export function TeamInfo() {
    return (
        <div className="container teamInfo__container">
            <div className="teamInfo">
                <PageHeader page="teamInfo" />
                <TeamInformation />
                <TeamInfoMember />
            </div>
        </div>
    );
}
