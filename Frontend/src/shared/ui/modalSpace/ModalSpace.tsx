import React from 'react';
import { TeamApplyModal } from '../../../pages/team/index';
import { RootState } from '../../../app/appProvider';
import { useSelector } from 'react-redux';
export function ModalSpace() {
    return (
        <>
            <TeamApplyModalSpace />
        </>
    );
}

function TeamApplyModalSpace() {
    const openModal = useSelector((state: RootState) => state.openModal.data);
    console.log(openModal);

    if (openModal) return <TeamApplyModal />;
    return null;
}
