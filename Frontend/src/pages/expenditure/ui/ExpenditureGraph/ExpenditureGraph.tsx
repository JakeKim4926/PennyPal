import React, { useState, useEffect } from 'react';
import { ChartArea } from '@/shared';
import { DailyExpense, fetchDailyExpense } from '../../model/fetchFunctions';

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
// export function ExpenditureGraph() {
//     const [expenditures, setExpenditures] = useState<DailyExpense[]>([]);

//     useEffect(() => {
//         // API 호출 및 데이터 설정
//         const initFetchDailyExpense = async () => {
//             const dailyExpenses = await fetchDailyExpense();
//             setExpenditures(dailyExpenses);
//         };

//         initFetchDailyExpense();
//     }, []);

//     const getWeekRange = (addWeeks: number) => {
//         const now = new Date();
//         const firstDayOfWeek = new Date(now.setDate(now.getDate() - now.getDay() + 1 + addWeeks * 7));
//         const lastDayOfWeek = new Date(firstDayOfWeek);
//         lastDayOfWeek.setDate(lastDayOfWeek.getDate() + 6);

//         return { firstDayOfWeek, lastDayOfWeek };
//     };

//     const formatDate = (date: Date) => {
//         return `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date
//             .getDate()
//             .toString()
//             .padStart(2, '0')}`;
//     };

//     const lastWeek = getWeekRange(-1);
//     const thisWeek = getWeekRange(0);

//     const getExpenditureForDay = (date: Date) => {
//         const formattedDate = formatDate(date);
//         return expenditures.find((expense) => expense.expenseDate === formattedDate)?.expenseAmount || '';
//     };

//     const data = [
//         [100000, 20000, 170000, 80000, 100000, 10000, 90000],
//         [50000, 100000, 50000, 150000, 50000, 80000, 60000],
//     ];

//     return (
//         <div className="expenditureGraph contentCard">
//             <div className="example">
//                 <div className="lastWeek">
//                     {[...Array(7).keys()].map((i) => (
//                         <div key={i}>
//                             {getExpenditureForDay(
//                                 new Date(
//                                     lastWeek.firstDayOfWeek.getFullYear(),
//                                     lastWeek.firstDayOfWeek.getMonth(),
//                                     lastWeek.firstDayOfWeek.getDate() + i,
//                                 ),
//                             )}
//                         </div>
//                     ))}
//                 </div>
//                 <div className="thisWeek">
//                     {[...Array(7).keys()].map((i) => (
//                         <div key={i}>
//                             {getExpenditureForDay(
//                                 new Date(
//                                     thisWeek.firstDayOfWeek.getFullYear(),
//                                     thisWeek.firstDayOfWeek.getMonth(),
//                                     thisWeek.firstDayOfWeek.getDate() + i,
//                                 ),
//                             )}
//                         </div>
//                     ))}
//                 </div>
//             </div>

//             <div className="example">
//                 <div className="lastWeek">
//                     <div className="mon"></div>
//                     <div className="tue"></div>
//                     <div className="wed"></div>
//                     <div className="thu"></div>
//                     <div className="fri"></div>
//                     <div className="sat"></div>
//                     <div className="sun"></div>
//                 </div>
//                 <div className="thisWeek">
//                     <div className="mon"></div>
//                     <div className="tue"></div>
//                     <div className="wed"></div>
//                     <div className="thu"></div>
//                     <div className="fri"></div>
//                     <div className="sat"></div>
//                     <div className="sun"></div>
//                 </div>
//             </div>
//             <div className="contentCard__title">
//                 <div className="contentCard__title-text">GRAPH</div>
//             </div>

//             <ChartArea data={data} />
//         </div>
//     );
// }
