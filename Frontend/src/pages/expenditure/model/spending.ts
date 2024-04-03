export interface Spending {
    transactionUniqueId: number;
    transactionType: number;
    transactionDate: string;
    transactionSummary: string;
    transactionBalance: string;
}

export interface SpendingItemProps {
    spending: Spending;
}

export interface ExpenditureWeeklyDailyProps {
    date: Date;
    spendings: Spending[];
}
