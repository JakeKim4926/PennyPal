type TeamChattingModalProps = {
    teamId: number;
    memberId: number;
    chatRoomId: number;
};

export function TeamChattingModal({ teamId, memberId, chatRoomId }: TeamChattingModalProps) {
    return (
        <div className="teamChattingModal modalContainer">
            <div>채팅방인데요</div>
            <div>{teamId}</div>
            <div>{memberId}</div>
            <div>{chatRoomId}</div>
        </div>
    );
}
