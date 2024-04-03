export type TeamTeamListPagenationProps = {
    curPage: number;
    setCurPage: React.Dispatch<React.SetStateAction<number>>;
    maxPage: number;
};

export type TeamTeamListItemProps = {
    teamId: number;
    name: string;
    head: number;
    leader: string;
    description: string;
    teamIsAutoConfirm: boolean;
};

// TeamTeamList.tsx
export type Team = {
    teamId: number;
    teamIsAutoConfirm: boolean;
    teamLeaderNickname: string;
    teamMembersNum: number;
    teamName: string;
    teamInfo: string;
};

export type TeamTeamListProps = {
    searchedPage: number;
    keyword: string;
};

// TeamCreateTeam.tsx
export type NameAreaProps = {
    moveNext: () => void;
    registName: (name: string) => void;
};
