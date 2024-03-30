import { PageHeader } from '@/shared';
import { MarketReceiptTop } from './MarketReceiptTop/MarketReceiptTop';
import { MarketReceiptContent } from './MarketReceiptContent/MarketReceiptContent';
import React from 'react';
import { useLocation } from 'react-router-dom';

export function MarketReceipt() {
    const page = useLocation();

    return (
        <div className="marketReceipt__container container">
            <div className="marketReceipt">
                <PageHeader page={page.pathname.substring(1)} />
                <MarketReceiptTop />
                <MarketReceiptContent />
            </div>
        </div>
    );
}
