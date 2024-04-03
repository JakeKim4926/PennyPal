import React, { useState, useEffect } from 'react';
import { customAxios } from '@/shared/lib/customAxios';
import { getCookie } from '@/shared/lib/cookieHelper';

import ExpenditureWeeklyDaily from './item/ExpenditureWeeklyDaily';
import { Spending } from '../../model/spending';

import { Expense, fetchMemberAttendance, fetchAccountTransactions } from '../../model/fetchFunctions';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faCircleChevronLeft,
    faCircleChevronRight,
    faCircleExclamation,
    faArrowsRotate,
} from '@fortawesome/free-solid-svg-icons';

const spendingsData: Spending[] = [
    {
        transactionUniqueId: 408,
        transactionType: 1,
        transactionDate: '20240401',
        transactionSummary: '신라마라탕',
        transactionBalance: '12000',
    },
    {
        transactionUniqueId: 409,
        transactionType: 2,
        transactionDate: '20240401',
        transactionSummary: '백제마라탕',
        transactionBalance: '12000',
    },
    {
        transactionUniqueId: 410,
        transactionType: 2,
        transactionDate: '20240401',
        transactionSummary: '고구려마라탕',
        transactionBalance: '14000',
    },
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
    const [transactions, setTransactions] = useState<Expense[]>([]);

    useEffect(() => {
        const loadExpense = async () => {
            const fetchedExpense = await fetchAccountTransactions();
            console.log(fetchedExpense); // 로그로 출력하여 확인
            if (Array.isArray(fetchedExpense)) {
                setTransactions(fetchedExpense);
            } else {
                console.error('fetchAccountTransactions did not return an array');
                setTransactions([]); // 호출 실패 시 빈 배열 설정
            }
        };

        loadExpense();
    }, []);

    const [coverVisible, setCoverVisible] = useState(true); // 가림막 div의 표시 상태

    const checkAttendanceState = async () => {
        const memberId = getCookie('memberId'); // 쿠키에서 memberId를 가져옵니다.
        if (!memberId) {
            console.error('로그인이 필요합니다.');
            alert('로그인이 필요합니다');
            return;
        }

        try {
            const response = await customAxios.get(`/member/attend/state`, {
                params: { memberId },
            });

            if (response.data && response.data.data === false) {
                // 금일 출석을 진행하지 않았을 경우
                setCoverVisible(true);
                console.log('출석여부 : false');
            } else {
                // 이미 출석을 한 경우
                setCoverVisible(false);
                console.log('출석여부 : true');
            }
        } catch (error) {
            console.error('출석 여부 확인 중 오류가 발생했습니다:', error);
            alert('출석 여부 확인 오류');
        }
    };

    const handleShowContentButtonClick = async () => {
        try {
            await fetchMemberAttendance();
            removeCover();
            await fetchAccountTransactions();
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
                    // 날짜를 'YYYYMMDD' 형식의 문자열로 변환하는 로직
                    const dateStr = `${date.getFullYear()}${
                        date.getMonth() + 1 < 10 ? `0${date.getMonth() + 1}` : date.getMonth() + 1
                    }${date.getDate() < 10 ? `0${date.getDate()}` : date.getDate()}`;

                    // 이 로직을 사용하여 spendingsData.filter(...) 호출 시 비교하는 부분에 적용합니다.
                    const dailySpendings = transactions.filter(
                        (transaction) => transaction.transactionDate === dateStr && transaction.transactionType === 2,
                    );

                    return <ExpenditureWeeklyDaily key={index} date={date} spendings={dailySpendings} />;
                })}
            </div>

            <div className="expenditureWeekly__footer">
                <div className="expenditureWeekly__footer-alert">
                    <FontAwesomeIcon icon={faCircleExclamation} size="lg" />
                    <span>지출이 늘어나지 않도록 관리하세요!</span>
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
