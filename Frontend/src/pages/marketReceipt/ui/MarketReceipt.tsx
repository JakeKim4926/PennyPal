import { PageHeader } from '@/shared';
import { MarketReceiptTop } from './MarketReceiptTop/MarketReceiptTop';
import { MarketReceiptContent } from './MarketReceiptContent/MarketReceiptContent';
import React from 'react';

export function MarketReceipt() {
    return (
        <div className="marketReceipt__container container">
            <div className="marketReceipt">
                <PageHeader page={'market'} />
                <MarketReceiptTop />
                <MarketReceiptContent />
            </div>
        </div>
    );
}
