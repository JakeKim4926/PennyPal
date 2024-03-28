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
    const openModal = useSelector((state: RootState) => state.openModal.data);
    console.log(openModal);

    if (openModal) return <TeamApplyModal />;
    return null;
}

function MarketItemModalSpace() {
    const openMarketItemModal = useSelector((state: RootState) => state.openMarketItem.data);
    console.log(openMarketItemModal);

    if (openMarketItemModal) return <MarketItemModal />;
    return null;
}
