import { PageHeader } from '../../../shared';
import { useLocation } from 'react-router-dom';
import { MarketList } from './MarketList/MarketList';

export function Market() {
    const page = useLocation();

    return (
        <div className="container market__container">
            <div className="market">
                <PageHeader page={page.pathname.substring(1)} />
                <MarketList />
            </div>
        </div>
    );
}
