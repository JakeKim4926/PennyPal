import { Routes, Route } from 'react-router-dom';
import { Expenditure } from '@/pages/expenditure';
import { Finance, FinanceDetail } from '@/pages/finance';
import { Landing } from '@/pages/landing';
import { Main } from '@/pages/main';
import { Market } from '@/pages/market';
import { Mission } from '@/pages/mission';
import { MyPage } from '@/pages/myPage';
import { Ranking } from '@/pages/ranking';
import { SignIn } from '@/pages/signin';
import { SignUp } from '@/pages/signup';
import { Navigate } from 'react-router-dom';
import { TeamRouting } from '@/pages/teamRouting';
import { FindPassword } from '@/pages/signin';
import { MarketReceipt } from '@/pages/marketReceipt';
import { LoginCheck } from '@/shared';

export function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/main" />} />
            <Route
                path="/main"
                element={
                    <LoginCheck>
                        <Main />
                    </LoginCheck>
                }
            />
            <Route
                path="/find-password"
                element={
                    <LoginCheck>
                        <FindPassword />
                    </LoginCheck>
                }
            />
            <Route
                path="/expenditure"
                element={
                    <LoginCheck>
                        <Expenditure />
                    </LoginCheck>
                }
            />
            <Route
                path="/finance"
                element={
                    <LoginCheck>
                        <Finance />
                    </LoginCheck>
                }
            />
            <Route
                path="/financedetail"
                element={
                    <LoginCheck>
                        <FinanceDetail />
                    </LoginCheck>
                }
            />
            <Route
                path="/landing"
                element={
                    <LoginCheck>
                        <Landing />
                    </LoginCheck>
                }
            />
            <Route
                path="/market"
                element={
                    <LoginCheck>
                        <Market />
                    </LoginCheck>
                }
            />
            <Route
                path="/receipt"
                element={
                    <LoginCheck>
                        <MarketReceipt />
                    </LoginCheck>
                }
            />
            <Route
                path="/mission"
                element={
                    <LoginCheck>
                        <Mission />
                    </LoginCheck>
                }
            />
            <Route
                path="/my-page"
                element={
                    <LoginCheck>
                        <MyPage />
                    </LoginCheck>
                }
            />
            <Route
                path="/ranking"
                element={
                    <LoginCheck>
                        <Ranking />
                    </LoginCheck>
                }
            />
            <Route
                path="/signin"
                element={
                    <LoginCheck>
                        <SignIn />
                    </LoginCheck>
                }
            />
            <Route
                path="/signup"
                element={
                    <LoginCheck>
                        <SignUp />
                    </LoginCheck>
                }
            />
            <Route
                path="/team"
                element={
                    <LoginCheck>
                        <TeamRouting />
                    </LoginCheck>
                }
            />
        </Routes>
    );
}
