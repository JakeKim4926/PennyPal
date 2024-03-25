import { PageHeader } from '../../../shared';
import { useLocation } from 'react-router-dom';
import { MarketTop } from '../ui/MarketTop/MarketTop';
import { MarketCategory } from '../ui/MarketCategory/MarketCategory';

export function Market() {
    const page = useLocation();

    return (
        <div className="container market__container">
            <div className="market">
                <PageHeader page={page.pathname.substring(1)} />
                <MarketTop point={1230} />
                <MarketCategory />
            </div>
        </div>
    );
}
