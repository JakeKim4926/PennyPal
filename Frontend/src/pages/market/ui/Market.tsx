import { ChartArea, PageHeader } from '@/shared';
import { useLocation } from 'react-router-dom';
import { MarketTop } from '@/pages/market/ui/MarketTop/MarketTop';
import { MarketCategory } from '@/pages/market/ui/MarketCategory/MarketCategory';
import { MarketList } from '@/pages/market/ui/MarketList/MarketList';

export function Market() {
    const page = useLocation();

    return (
        <div className="container market__container">
            <div className="market">
                <PageHeader page={page.pathname.substring(1)} />
                <MarketTop point={1230} />
                <MarketCategory />
                <MarketList />
            </div>
        </div>
    );
}
