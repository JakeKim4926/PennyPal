import { useState, useEffect } from 'react';

import { ExpenditureWeeklyDaily } from './item/ExpenditureDailyItem';
import {
    fetchMemberAttendance,
    fetchCheckMemberAttendance,
    fetchDailyAccountTransactions,
    fetchAccountTransactions,
    Spending,
    getCurrentDate,
    getWeekDates,
} from '@/pages/expenditure/index';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faCircleChevronLeft,
    faCircleChevronRight,
    faCircleExclamation,
    faArrowsRotate,
} from '@fortawesome/free-solid-svg-icons';

export function ExpenditureWeekly() {
    // * 거래내역 자동 로드
    const [transactions, setTransactions] = useState<Spending[]>([]);
    const [currentWeekStart, setCurrentWeekStart] = useState(
        new Date(new Date().setDate(new Date().getDate() - new Date().getDay() + 1)),
    );
    useEffect(() => {
        const loadExpense = async () => {
            const fetchedExpense = await fetchDailyAccountTransactions();
            console.log(fetchedExpense);

            if (Array.isArray(fetchedExpense)) {
                setTransactions(fetchedExpense);
            } else {
                console.error('fetchAccountTransactions did not return an array');
                setTransactions([]); // 호출 실패 시 빈 배열 설정
            }
        };

        loadExpense();
        checkAttendanceState();
    }, [currentWeekStart]);

    // * 출석여부 조회
    const [coverVisible, setCoverVisible] = useState(true); // 가림막 div의 표시 상태

    const checkAttendanceState = async () => {
        const attendanceState = await fetchCheckMemberAttendance();
        setCoverVisible(!attendanceState);
    };

    const handleShowContentButtonClick = async () => {
        try {
            await fetchMemberAttendance();
            removeCover();
            await fetchDailyAccountTransactions();
        } catch (error) {
            console.error('오류가 발생했습니다:', error);
        }
    };

    const removeCover = () => {
        setCoverVisible(false);
    };

    // * 캘린더 날짜 이동

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
