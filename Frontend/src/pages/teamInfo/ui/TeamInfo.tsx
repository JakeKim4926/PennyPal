import { PageHeader } from '../../../shared';
import { TeamInformation } from './TeamInfomation/TeamInformation';
import { TeamInfoMember } from './TeamInfoMember/TeamInfoMember';
import { TeamInfoTeamExpenditure } from './TeamInfoTeamExpenditure/TeamInfoTeamExpenditure';
import { TeamInfoChatButton } from './TeamInfoChatButton/TeamInfoChatButton';
import { useSelector } from 'react-redux';
import { RootState } from '@/app/appProvider';
import { useEffect, useRef } from 'react';
import { connectTeamChatRoom } from '../api/connectTeamChatRoom';
import { CompatClient } from '@stomp/stompjs';

export function TeamInfo() {
    const teamData: any = useSelector((state: RootState) => state.setTeamInfoReducer.data);

    // client: 채팅 연결 주체
    const client = useRef<CompatClient>();

    return (
        <div className="container teamInfo__container">
            <div className="teamInfo">
                <PageHeader page="teamInfo" />
                <TeamInformation
                    teamName={teamData.teamName}
                    teamMembers={teamData.members}
                    teamLastTotalExpenses={teamData.teamLastTotalExpenses}
                    teamThisTotalExpenses={teamData.teamThisTotalExpenses}
                    teamInfo={teamData.teamInfo}
                    teamRankRealtime={teamData.teamRankRealtime}
                    teamLeaderId={teamData.teamLeaderId}
                    teamId={teamData.teamId}
                />
                <TeamInfoMember teamLeaderId={teamData.teamLeaderId} teamMembers={teamData.members} />
                <TeamInfoTeamExpenditure />
                <TeamInfoChatButton teamId={teamData.teamId} chatRoomId={teamData.chatRoomId} client={client} />
            </div>
        </div>
    );
}
