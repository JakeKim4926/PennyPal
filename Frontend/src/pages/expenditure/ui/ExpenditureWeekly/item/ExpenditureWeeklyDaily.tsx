import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBus, faArrowRight } from '@fortawesome/free-solid-svg-icons';

interface ExpenditureWeeklyDailyProps {
    date: Date;
    onClick: () => void;
}

export function ExpenditureWeeklyDaily({ date, onClick }: ExpenditureWeeklyDailyProps) {
    const format = (date: Date): string => {
        const month = date.getMonth() + 1;
        const day = date.getDate();
        return `${month < 10 ? `0${month}` : month}.${day < 10 ? `0${day}` : day}`;
    };
    const dayOfWeek = date.toLocaleDateString('ko-KR', { weekday: 'short' });

    return (
        <div className="expenditureWeeklyDaily" onClick={onClick}>
            <div className="expenditureWeeklyDaily__date">
                <span>{format(date)}</span>
                <span>{dayOfWeek.toUpperCase()}</span>
            </div>

            <div className="expenditureWeeklyDaily__spendings clickable-div">
                <div className="expenditureWeeklyDaily__spendings-expend">
                    <FontAwesomeIcon icon={faBus} />
                    <div className="expenditureWeeklyDaily__spendings-expend-info">
                        <p className="expenditureWeeklyDaily__spendings-expend-info-deposit">후불대중교통</p>
                        <p className="expenditureWeeklyDaily__spendings-expend-info-amount">10,480</p>
                    </div>
                </div>

                <div className="expenditureWeeklyDaily__spendings-expend">
                    <FontAwesomeIcon icon={faBus} />
                    <div className="expenditureWeeklyDaily__spendings-expend-info">
                        <p className="expenditureWeeklyDaily__spendings-expend-info-deposit">후불대중교통</p>
                        <p className="expenditureWeeklyDaily__spendings-expend-info-amount">10,480</p>
                    </div>
                </div>
            </div>

            <div className="expenditureWeeklyDaily__sum">
                <FontAwesomeIcon icon={faArrowRight} size="xs" />
                <span>50,680원</span>
            </div>
        </div>
    );
}
