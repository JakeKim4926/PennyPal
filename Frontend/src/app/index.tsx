import { ModalSpace, Nav } from '../shared';
import { Header } from '../shared';
import { AppRoutes } from './appRoutes';

import '@/style/main.scss';

export function App() {
    return (
        <>
            <Header />
            <AppRoutes />
            <ModalSpace />
            <Nav />
        </>
    );
}
