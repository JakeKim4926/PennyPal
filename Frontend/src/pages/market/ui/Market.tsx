import { ChartArea, PageHeader } from '../../../shared';
import { useLocation } from 'react-router-dom';
import { MarketTop } from '../ui/MarketTop/MarketTop';
import { MarketCategory } from '../ui/MarketCategory/MarketCategory';
import { MarketList } from '../ui/MarketList/MarketList';

export function Market() {
    const page = useLocation();
    const data = [
        [100000, 150000, 170000, 180000, 100000, 2000, 90000],
        [50000, 100000, 50000, 50000, 50000, 40000, 60000],
    ];

    const prevSum = data[0].reduce((prev, cur) => prev + cur, 0); // 이전주 지출 총액
    const presSum = data[1].reduce((prev, cur) => prev + cur, 0); // 이번주 지출 총액
    const savingRate = ((presSum / prevSum) * 100).toFixed(1); // 절감률
    const spendDiff = Math.abs(presSum - prevSum).toLocaleString(); // 지출 차액

    return (
        <div className="container market__container">
            <div className="market">
                <PageHeader page={page.pathname.substring(1)} />
                <MarketTop point={1230} />
                <MarketCategory />
                <MarketList />
                <ChartArea data={data} />
            </div>
        </div>
    );
}
