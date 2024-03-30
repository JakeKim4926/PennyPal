import React from 'react';
import { TeamApplyModal } from '@/pages/team/index';
import { RootState } from '@/app/appProvider';
import { useSelector } from 'react-redux';
import { MarketItemModal } from '@/pages/market/index';
import { TeamLeaveModal } from '@/pages/teamInfo';
import { TeamSettingModal } from '@/pages/teamInfo';

export function ModalSpace() {
    return (
        <>
            <TeamApplyModalSpace />
            <MarketItemModalSpace />
            <TeamLeaveModalSpace />
            <TeamSettingModalSpace />
        </>
    );
}

function TeamApplyModalSpace() {
    const openTeamDetailModal = useSelector((state: RootState) => state.openTeamDetailModalReducer.data);

    if (openTeamDetailModal) return <TeamApplyModal team={openTeamDetailModal} />;
    return null;
}

function MarketItemModalSpace() {
    const openMarketItemModal = useSelector((state: RootState) => state.openMarketItem.data);

    if (openMarketItemModal) return <MarketItemModal />;
    return null;
}

function TeamLeaveModalSpace() {
    const isOpenTeamLeaveModal = useSelector((state: RootState) => state.openTeamLeaveModalReducer.data);

    if (isOpenTeamLeaveModal instanceof Object)
        return <TeamLeaveModal teamId={isOpenTeamLeaveModal.teamId} memberId={isOpenTeamLeaveModal.memberId} />;
    return null;
}

function TeamSettingModalSpace() {
    const isOpenTeamSettingModal = useSelector((state: RootState) => state.openTeamSettingModalReducer.data);

    if (isOpenTeamSettingModal instanceof Object)
        return <TeamSettingModal teamId={isOpenTeamSettingModal.teamId} memberId={isOpenTeamSettingModal.memberId} />;

    return null;
}
