import { SpendingItemProps } from '@/pages/expenditure/index';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMoneyBill1Wave } from '@fortawesome/free-solid-svg-icons';

export function SpendingItem({ spending }: SpendingItemProps) {
    const formattedBalance = new Intl.NumberFormat('ko-KR').format(parseInt(spending.transactionBalance));

    return (
        <div className="expenditureWeeklyDaily__spendings-expend">
            <FontAwesomeIcon icon={faMoneyBill1Wave} />{' '}
            <div className="expenditureWeeklyDaily__spendings-expend-info">
                <p className="expenditureWeeklyDaily__spendings-expend-info-label">{spending.transactionSummary}</p>
                <p className="expenditureWeeklyDaily__spendings-expend-info-amount">{formattedBalance}</p>
            </div>
        </div>
    );
}
