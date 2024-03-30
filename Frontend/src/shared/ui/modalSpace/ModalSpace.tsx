import React from 'react';
import { TeamApplyModal } from '@/pages/team/index';
import { RootState } from '@/app/appProvider';
import { useSelector } from 'react-redux';
import { MarketItemModal } from '@/pages/market/index';

export function ModalSpace() {
    return (
        <>
            <TeamApplyModalSpace />
            <MarketItemModalSpace />
        </>
    );
}

function TeamApplyModalSpace() {
    const openTeamDetailModal = useSelector((state: RootState) => state.openTeamDetailModalReducer.data);

    if (openTeamDetailModal) return <TeamApplyModal teamId={openTeamDetailModal} />;
    return null;
}

function MarketItemModalSpace() {
    const openMarketItemModal = useSelector((state: RootState) => state.openMarketItem.data);

    if (openMarketItemModal) return <MarketItemModal />;
    return null;
}
