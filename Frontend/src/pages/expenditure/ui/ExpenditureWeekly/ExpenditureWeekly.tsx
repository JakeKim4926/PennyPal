import React, { useState, useEffect } from 'react';
import { ExpenditureWeeklyDaily } from './item/ExpenditureWeeklyDaily';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faCircleChevronLeft,
    faCircleChevronRight,
    faCircleExclamation,
    faArrowsRotate,
    faXmark,
} from '@fortawesome/free-solid-svg-icons';

const getCurrentDate = (): string => {
    const now = new Date();
    const month = now.getMonth() + 1; // getMonth()는 0부터 시작하므로 +1
    const date = now.getDate();
    const day = now.toLocaleDateString('ko-KR', { weekday: 'short' });

    return `${month < 10 ? `0${month}` : month}.${date < 10 ? `0${date}` : date} ${day.toUpperCase()}`;
};

const getWeekDates = (currentWeekStart: Date): { rangeText: string; dates: Date[] } => {
    const dates = Array.from({ length: 7 }).map((_, i) => {
        return new Date(new Date(currentWeekStart).setDate(currentWeekStart.getDate() + i));
    });

    const format = (date: Date): string => `${date.getFullYear()}년 ${date.getMonth() + 1}월 ${date.getDate()}일`;
    const rangeText = `${format(dates[0])} - ${format(dates[6])}`;

    return { rangeText, dates };
};

export function ExpenditureWeekly() {
    const [coverVisible, setCoverVisible] = useState(true); // 가림막 div의 표시 상태

    const removeCover = () => {
        setCoverVisible(false); // 가림막 div를 제거
    };

    const [currentWeekStart, setCurrentWeekStart] = useState(
        new Date(new Date().setDate(new Date().getDate() - new Date().getDay() + 1)),
    );

    useEffect(() => {
        // 주가 변경될 때 추가 로직이 필요한 경우 여기에 작성
    }, [currentWeekStart]);

    const { rangeText, dates } = getWeekDates(currentWeekStart);

    const handlePreviousWeek = () => {
        setCurrentWeekStart(new Date(new Date(currentWeekStart).setDate(currentWeekStart.getDate() - 7)));
    };

    const handleNextWeek = () => {
        setCurrentWeekStart(new Date(new Date(currentWeekStart).setDate(currentWeekStart.getDate() + 7)));
    };

    const [isModalVisible, setIsModalVisible] = useState(false);
    const [selectedDate, setSelectedDate] = useState<Date | null>(null);

    const handleDailyClick = (date: Date) => {
        setSelectedDate(date);
        setIsModalVisible(true);
    };

    const formatDate = (date: Date): string => {
        const year = date.getFullYear();
        const month = date.getMonth() + 1; // getMonth()는 0부터 시작하므로 1을 더해줍니다.
        const day = date.getDate();
        return `${year}년 ${month < 10 ? `0${month}` : month}월 ${day < 10 ? `0${day}` : day}일`;
    };

    const Modal = ({ onClose, date }: { onClose: () => void; date: Date | null }) => (
        <div
            className="modal"
            style={{
                position: 'fixed',
                top: '50%',
                left: '50%',
                transform: 'translate(-50%, -50%)',

                display: 'flex',
                flexDirection: 'column',
                gap: '20px',

                width: '500px',
                height: '700px',
                border: '1px solid lightgray',
                borderRadius: '20px',
                backgroundColor: 'white',
                zIndex: 1000,
            }}
        >
            <button
                onClick={onClose}
                style={{
                    position: 'fixed',
                    top: '50px',
                    left: '450px',
                    transform: 'translate(-50%, -50%)',
                }}
            >
                <FontAwesomeIcon icon={faXmark} className="clickable-icon" size="lg" />
            </button>

            <div className="modal__title">{date ? formatDate(date) : '날짜 정보 없음'}</div>
            <div className="modal__content">
                <div className="modal__content-list">소비내역</div>
                <div className="modal__content-sum">합계</div>
            </div>
        </div>
    );

    return (
        <div className={`expenditureWeekly contentCard`} style={{ position: 'relative' }}>
            {coverVisible && (
                <div className="coverDiv">
                    <button onClick={removeCover} className="showContentButton">
                        출석하고 오늘의 지출내역 불러오기!
                    </button>
                </div>
            )}

            {isModalVisible && <Modal onClose={() => setIsModalVisible(false)} date={selectedDate} />}

            <div className="contentCard__title">
                <div className="contentCard__title-text">WEEKLY SPENDINGS</div>
                <div className="contentCard__title-week">
                    <FontAwesomeIcon
                        icon={faCircleChevronLeft}
                        onClick={handlePreviousWeek}
                        className="clickable-icon"
                    />
                    <span className="contentCard__title-week-text">{rangeText}</span>
                    <FontAwesomeIcon icon={faCircleChevronRight} onClick={handleNextWeek} className="clickable-icon" />
                </div>
                <div className="contentCard__title-today">
                    <span className="contentCard__title-today-text">TODAY</span>
                    <span className="contentCard__title-today-date">{getCurrentDate()}</span>
                </div>
            </div>

            <div className="expenditureWeekly__content">
                {dates.map((date, index) => (
                    <ExpenditureWeeklyDaily key={index} date={date} onClick={() => handleDailyClick(date)} />
                ))}
            </div>

            <div className="expenditureWeekly__footer">
                <div className="expenditureWeekly__footer-alert">
                    <FontAwesomeIcon icon={faCircleExclamation} size="lg" />
                    <span>카테고리가 미설정된 소비내역을 편집해주세요!</span>
                </div>
                <div className="expenditureWeekly__footer-reload">
                    <span className="expenditureWeekly__footer-reload-text">이 주의 지출내역 불러오기</span>
                    <div className="expenditureWeekly__footer-reload-button">
                        <FontAwesomeIcon icon={faArrowsRotate} size="2xl" />
                    </div>
                </div>
            </div>
        </div>
    );
}
