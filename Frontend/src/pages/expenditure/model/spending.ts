export interface Spending {
    id: number;
    date: string; // 'YYYY-MM-DD' 형식
    label: string;
    amount: string; // 예: '10,000원'
}

export interface SpendingItemProps {
    spending: Spending;
}

export interface ExpenditureWeeklyDailyProps {
    date: Date;
    spendings: Spending[];
}
