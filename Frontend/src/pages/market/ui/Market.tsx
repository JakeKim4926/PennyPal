import { ChartArea, PageHeader, getCookie } from '@/shared';
import { useLocation } from 'react-router-dom';
import { MarketTop } from '@/pages/market/ui/MarketTop/MarketTop';
import { MarketCategory } from '@/pages/market/ui/MarketCategory/MarketCategory';
import { MarketList } from '@/pages/market/ui/MarketList/MarketList';
import { useState, useEffect } from 'react';
import { getUserPoint } from '../api/getUserPoint';
import { useSelector } from 'react-redux';
import { RootState } from '@/app/appProvider';

export function Market() {
    const page = useLocation();
    const [point, setPoint] = useState(0);
    const forceRender = useSelector((state: RootState) => state.forceRenderReducer.data);

    useEffect(() => {
        getUserPoint(Number(getCookie('memberId')))
            .then((res) => {
                if (res) {
                    setPoint(res.data);
                }
            })
            .catch((err) => console.log(err));
    }, [forceRender]);

    return (
        <div className="container market__container">
            <div className="market">
                <PageHeader page={page.pathname.substring(1)} />
                <MarketTop point={point} />
                <MarketCategory />
                <MarketList />
            </div>
        </div>
    );
}
