import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleChevronLeft, faCircleChevronRight } from '@fortawesome/free-solid-svg-icons';

export function ExpenditureWeekly() {
    return (
        <div className="expenditureWeekly contentCard">
            <div className="contentCard__title">
                <div className="contentCard__title-text">WEEKLY SPENDINGS</div>
                <div className="contentCard__title-week">
                    <FontAwesomeIcon icon={faCircleChevronLeft} />
                    <span className="black">2024년 3월 1주차</span>
                    <FontAwesomeIcon icon={faCircleChevronRight} />
                </div>
                <div className="contentCard__title-today">
                    <span className="green">TODAY</span>
                    <span className="gray">03. 02 SAT</span>
                </div>
            </div>

            <div className="expenditureWeekly__content">
                <div className="expenditureWeekly__content-weekDay">
                    <div className="weekDay">
                        <span className="date">03.24</span>
                        <span className="day">MON</span>
                    </div>
                    <div className="weekDay">
                        <span className="date">03.24</span>
                        <span className="day">TUE</span>
                    </div>
                    <div className="weekDay">
                        <span className="date">03.24</span>
                        <span className="day">WED</span>
                    </div>
                    <div className="weekDay">
                        <span className="date">03.24</span>
                        <span className="day">THU</span>
                    </div>
                    <div className="weekDay">
                        <span className="date">03.24</span>
                        <span className="day">FRI</span>
                    </div>
                    <div className="weekDay">
                        <span className="date">03.24</span>
                        <span className="day">SAT</span>
                    </div>
                    <div className="weekDay">
                        <span className="date">03.24</span>
                        <span className="day">SUN</span>
                    </div>
                </div>

                <div className="expenditureWeekly__content-spendings">
                    <div>.</div>
                    <div>.</div>
                    <div>.</div>
                    <div>.</div>
                    <div>.</div>
                    <div>.</div>
                    <div>.</div>
                </div>

                <div className="expenditureWeekly__content-sum">
                    <div>.</div>
                    <div>.</div>
                    <div>.</div>
                    <div>.</div>
                    <div>.</div>
                    <div>.</div>
                    <div>.</div>
                </div>
            </div>

            <div className="expenditureWeekly__footer">
                <div className="expenditureWeekly__footer-alert">alert</div>
                <div className="expenditureWeekly__footer-reload">reload</div>
            </div>
        </div>
    );
}
