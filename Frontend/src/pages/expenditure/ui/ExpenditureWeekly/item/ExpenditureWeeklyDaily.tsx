import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBus, faArrowRight } from '@fortawesome/free-solid-svg-icons';

export function ExpenditureWeeklyDaily() {
    return (
        <div className="expenditureWeeklyDaily">
            <div className="expenditureWeeklyDaily__date">
                <span>03.24</span>
                <span>SAT</span>
            </div>

            <div className="expenditureWeeklyDaily__spendings">
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
