import React from 'react';
import SpendingItem from './SpendingItem';
import { Spending, SpendingItemProps, ExpenditureWeeklyDailyProps } from '@/pages/expenditure/model/spending';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBus, faArrowRight } from '@fortawesome/free-solid-svg-icons';

function ExpenditureWeeklyDaily({ date, spendings }: ExpenditureWeeklyDailyProps) {
    const format = (date: Date): string => {
        const month = date.getMonth() + 1;
        const day = date.getDate();
        return `${month < 10 ? `0${month}` : month}.${day < 10 ? `0${day}` : day}`;
    };
    const dayOfWeek = date.toLocaleDateString('ko-KR', { weekday: 'short' });

    const totalAmount = spendings.reduce((sum, spending) => {
        const amountNumber = parseInt(spending.transactionBalance.replace(/,/g, '').replace('원', ''), 10);
        return sum + amountNumber;
    }, 0);

    // 숫자를 '10,000원' 형식으로 변환
    const formattedTotalAmount = `${totalAmount.toLocaleString()}원`;

    return (
        <div className="expenditureWeeklyDaily">
            <div className="expenditureWeeklyDaily__date">
                <span>{format(date)}</span>
                <span>{dayOfWeek.toUpperCase()}</span>
            </div>

            <div className="expenditureWeeklyDaily__spendings clickable-div">
                {spendings.map((spending) => (
                    <SpendingItem key={spending.transactionUniqueId} spending={spending} />
                ))}
            </div>

            <div className="expenditureWeeklyDaily__sum">
                <FontAwesomeIcon icon={faArrowRight} size="xs" />
                <span>{formattedTotalAmount}</span>
            </div>
        </div>
    );
}

export default ExpenditureWeeklyDaily;
