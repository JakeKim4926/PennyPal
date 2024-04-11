export const getCurrentDate = (): string => {
    const now = new Date();
    const month = now.getMonth() + 1; // getMonth()는 0부터 시작하므로 +1
    const date = now.getDate();
    const day = now.toLocaleDateString('ko-KR', { weekday: 'short' });

    return `${month < 10 ? `0${month}` : month}.${date < 10 ? `0${date}` : date} ${day.toUpperCase()}`;
};

export const getWeekDates = (currentWeekStart: Date): { rangeText: string; dates: Date[] } => {
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
