import React, { useState, useEffect } from 'react';
import { customAxios } from '@/shared/lib/customAxios';
import { getCookie } from '@/shared/lib/cookieHelper';

import ExpenditureWeeklyDaily from './item/ExpenditureWeeklyDaily';
import { Spending } from '../../model/spending';

import {
    fetchTodayAccountTransactions,
    fetchMemberAttendance,
    fetchAccountTransactions,
} from '../../model/fetchFunctions';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faCircleChevronLeft,
    faCircleChevronRight,
    faCircleExclamation,
    faArrowsRotate,
    faXmark,
} from '@fortawesome/free-solid-svg-icons';

const spendingsData: Spending[] = [
    { id: 1, date: '2024-04-01', label: '교통비', amount: '10,000원' },
    { id: 1, date: '2024-04-01', label: '교통비', amount: '10,000원' },
    { id: 2, date: '2024-04-02', label: '식료품', amount: '25,000원' },
    { id: 2, date: '2024-04-02', label: '식료품', amount: '25,000원' },
    // 추가 지출 데이터...
];

const getCurrentDate = (): string => {
    const now = new Date();
    const month = now.getMonth() + 1; // getMonth()는 0부터 시작하므로 +1
    const date = now.getDate();
    const day = now.toLocaleDateString('ko-KR', { weekday: 'short' });

    return `${month < 10 ? `0${month}` : month}.${date < 10 ? `0${date}` : date} ${day.toUpperCase()}`;
};

const getWeekDates = (currentWeekStart: Date): { rangeText: string; dates: Date[] } => {
    const dates = Array.from({ length: 7 }).map((_, i) => {
        const utcDate = new Date(
            Date.UTC(
                currentWeekStart.getUTCFullYear(),
                currentWeekStart.getUTCMonth(),
                currentWeekStart.getUTCDate() + i,
            ),
        );
        return utcDate;
    });

    const format = (date: Date): string =>
        `${date.getUTCFullYear()}년 ${date.getUTCMonth() + 1}월 ${date.getUTCDate()}일`;
    const rangeText = `${format(dates[0])} - ${format(dates[6])}`;

    return { rangeText, dates };
};

export function ExpenditureWeekly() {
    const [coverVisible, setCoverVisible] = useState(true); // 가림막 div의 표시 상태

    const checkAttendanceState = async () => {
        const memberIndex = getCookie('memberId'); // 쿠키에서 memberId를 가져옵니다.
        if (!memberIndex) {
            console.error('로그인이 필요합니다.');
            alert('로그인이 필요합니다');
            return;
        }

        try {
            const response = await customAxios.get(`/member/attend/state`, {
                params: { memberIndex },
            });

            if (response.data && response.data.data === false) {
                // 금일 출석을 진행하지 않았을 경우
                setCoverVisible(true);
            } else {
                // 이미 출석을 한 경우
                setCoverVisible(false);
            }
        } catch (error) {
            console.error('출석 여부 확인 중 오류가 발생했습니다:', error);
        }
    };

    const handleShowContentButtonClick = async () => {
        try {
            await fetchMemberAttendance();
            removeCover();
            await fetchTodayAccountTransactions();
        } catch (error) {
            // 오류 처리
            console.error('오류가 발생했습니다:', error);
            alert('오류가 발생했습니다. 다시 시도해주세요.');
        }
    };

    const removeCover = () => {
        setCoverVisible(false);
    };

    const [currentWeekStart, setCurrentWeekStart] = useState(
        new Date(new Date().setDate(new Date().getDate() - new Date().getDay() + 1)),
    );

    useEffect(() => {
        checkAttendanceState();
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
                    <button onClick={handleShowContentButtonClick} className="showContentButton">
                        출석하고 오늘의 지출내역 불러오기!
                        <div className="showContentButton__reload clickable-btn">
                            <div className="showContentButton__reload-img">
                                <FontAwesomeIcon icon={faArrowsRotate} size="xl" />
                            </div>
                        </div>
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
                {dates.map((date, index) => {
                    const dateStr = date.toISOString().slice(0, 10); // 'YYYY-MM-DD' 형식으로 변환
                    const dailySpendings = spendingsData.filter((spending) => spending.date === dateStr);

                    return <ExpenditureWeeklyDaily key={index} date={date} spendings={dailySpendings} />;
                })}
            </div>

            <div className="expenditureWeekly__footer">
                <div className="expenditureWeekly__footer-alert">
                    <FontAwesomeIcon icon={faCircleExclamation} size="lg" />
                    <span>열심히 활동해봅시다!</span>
                </div>
                <div className="expenditureWeekly__footer-reload clickable" onClick={fetchAccountTransactions}>
                    <span className="expenditureWeekly__footer-reload-text">지출내역 불러오기!</span>
                    <div className="expenditureWeekly__footer-reload-button">
                        <div className="expenditureWeekly__footer-reload-button-img">
                            <FontAwesomeIcon icon={faArrowsRotate} size="2xl" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
