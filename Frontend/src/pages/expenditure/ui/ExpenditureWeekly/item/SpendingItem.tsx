import React from 'react';
import { Spending, SpendingItemProps, ExpenditureWeeklyDailyProps } from '@/pages/expenditure/model/spending';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBus } from '@fortawesome/free-solid-svg-icons';

function SpendingItem({ spending }: SpendingItemProps) {
    return (
        <div className="expenditureWeeklyDaily__spendings-expend">
            <FontAwesomeIcon icon={faBus} />
            <div className="expenditureWeeklyDaily__spendings-expend-info">
                <p className="expenditureWeeklyDaily__spendings-expend-info-label">{spending.label}</p>
                <p className="expenditureWeeklyDaily__spendings-expend-info-amount">{spending.amount}</p>
            </div>
        </div>
    );
}

export default SpendingItem;
