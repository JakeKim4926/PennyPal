import { Nav } from '../shared';
import { Header } from '../shared';
import { AppRoutes } from './AppRoutes';

import '@/style/main.scss';

export function App() {
    return (
        <>
            <Header />
            <AppRoutes />
            <Nav />
        </>
    );
}
