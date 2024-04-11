import { useState, useEffect } from 'react';

import { ChartArea } from '@/shared';
import { DailyExpense, fetchDailyExpense } from '@/pages/expenditure/index';

export function ExpenditureGraph() {
    const [expenditures, setExpenditures] = useState<DailyExpense[]>([]);

    useEffect(() => {
        // API 호출 및 데이터 설정
        const initFetchDailyExpense = async () => {
            const dailyExpenses = await fetchDailyExpense();
            setExpenditures(dailyExpenses);
        };

        initFetchDailyExpense();
    }, []);

    const getWeekRange = (addWeeks: number): Date[] => {
        const now = new Date();
        const week = [];
        for (let i = 0; i < 7; i++) {
            const day = new Date(now);
            day.setDate(now.getDate() - now.getDay() + i + 1 + addWeeks * 7);
            week.push(day);
        }
        return week;
    };

    const formatDate = (date: Date): string => {
        return `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date
            .getDate()
            .toString()
            .padStart(2, '0')}`;
    };

    const lastWeekDates = getWeekRange(-1);
    const thisWeekDates = getWeekRange(0);

    const getExpendituresForWeek = (weekDates: Date[]): number[] => {
        return weekDates.map((date) => {
            const formattedDate = formatDate(date);
            const expenditure = expenditures.find((expense) => expense.expenseDate === formattedDate);
            return expenditure ? expenditure.expenseAmount : 0;
        });
    };

    const data = [getExpendituresForWeek(lastWeekDates), getExpendituresForWeek(thisWeekDates)];

    return (
        <div className="expenditureGraph contentCard">
            <div className="contentCard__title">
                <div className="contentCard__title-text">GRAPH</div>
            </div>

            <ChartArea data={data} />
        </div>
    );
}
