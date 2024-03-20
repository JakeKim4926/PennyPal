import React from 'react';
import { TestComp1 } from './TestComp1/TestComp1';
import { TestComp2 } from './TestComp2/TestComp2';
import { PageHeader } from '../../../shared';
import { useLocation } from 'react-router-dom';

export function Test() {
    const location = useLocation();

    return (
        <div className="container test__container">
            <div className="test">
                <PageHeader page={location.pathname.substring(1)} />
                <TestComp1 />
                <TestComp2 />
            </div>
        </div>
    );
}
