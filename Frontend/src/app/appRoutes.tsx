import { Routes, Route } from 'react-router-dom';
import { Expenditure } from '@/pages/expenditure';
import { Finance } from '@/pages/finance';
import { Landing } from '@/pages/landing';
import { Main } from '@/pages/main';
import { Market } from '@/pages/market';
import { Mission } from '@/pages/mission';
import { MyPage } from '@/pages/myPage';
import { Ranking } from '@/pages/ranking';
import { SignIn } from '@/pages/signin';
import { SignUp } from '@/pages/signup';
import { Test } from '@/pages/test/ui/Test';
import { Navigate } from 'react-router-dom';
import { TeamRouting } from '@/pages/teamRouting';
import { MarketReceipt } from '@/pages/marketReceipt';

export function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/main" />} />
            <Route path="/main" element={<Main />} />
            <Route path="/expenditure" element={<Expenditure />} />
            <Route path="/finance" element={<Finance />} />
            <Route path="/landing" element={<Landing />} />
            <Route path="/market" element={<Market />} />
            <Route path="/receipt" element={<MarketReceipt />} />
            <Route path="/mission" element={<Mission />} />
            <Route path="/my-page" element={<MyPage />} />
            <Route path="/ranking" element={<Ranking />} />
            <Route path="/signin" element={<SignIn />} />
            <Route path="/signup" element={<SignUp />} />
            <Route path="/team" element={<TeamRouting />} />
            <Route path="/test" element={<Test />} />
        </Routes>
    );
}
