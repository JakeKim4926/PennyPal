import { ModalSpace, Nav } from '../shared';
import { Header } from '../shared';
import { RootState } from './appProvider';
import { AppRoutes } from './appRoutes';
import { useSelector } from 'react-redux';

import '@/style/main.scss';

export function App() {
    const isInitPage = useSelector((state: RootState) => state.setIsInitPageReducer.data);

    return (
        <>
            <Header />
            <AppRoutes />
            <ModalSpace />
            {!isInitPage && <Nav />}
        </>
    );
}
