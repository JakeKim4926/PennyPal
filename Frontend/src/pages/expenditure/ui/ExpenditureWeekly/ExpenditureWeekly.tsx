import React from 'react';
import { ExpenditureWeeklyDaily } from './item/ExpenditureWeeklyDaily';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faCircleChevronLeft,
    faCircleChevronRight,
    faCircleExclamation,
    faArrowsRotate,
} from '@fortawesome/free-solid-svg-icons';

export function ExpenditureWeekly() {
    return (
        <div className="expenditureWeekly contentCard">
            <div className="contentCard__title">
                <div className="contentCard__title-text">WEEKLY SPENDINGS</div>
                <div className="contentCard__title-week">
                    <FontAwesomeIcon icon={faCircleChevronLeft} />
                    <span className="contentCard__title-week-text">2024년 3월 1주차</span>
                    <FontAwesomeIcon icon={faCircleChevronRight} />
                </div>
                <div className="contentCard__title-today">
                    <span className="contentCard__title-today-text">TODAY</span>
                    <span className="contentCard__title-today-date">03. 02 SAT</span>
                </div>
            </div>

            <div className="expenditureWeekly__content">
                <ExpenditureWeeklyDaily />
                <ExpenditureWeeklyDaily />
                <ExpenditureWeeklyDaily />
                <ExpenditureWeeklyDaily />
                <ExpenditureWeeklyDaily />
                <ExpenditureWeeklyDaily />
                <ExpenditureWeeklyDaily />
            </div>

            <div className="expenditureWeekly__footer">
                <div className="expenditureWeekly__footer-alert">
                    <FontAwesomeIcon icon={faCircleExclamation} size="lg" />
                    <span>카테고리가 미설정된 소비내역을 편집해주세요!</span>
                </div>
                <div className="expenditureWeekly__footer-reload">
                    <span className="expenditureWeekly__footer-reload-text">금주의 지출내역 불러오기</span>
                    <div className="expenditureWeekly__footer-reload-button">
                        <FontAwesomeIcon icon={faArrowsRotate} size="2xl" />
                    </div>
                </div>
            </div>
        </div>
    );
}
