import React from 'react';
import { Button } from '../../../shared';
import { TestComp1 } from './TestComp1/TestComp1';
import { TestComp2 } from './TestComp2/TestComp2';
import { PageHeader } from '../../../shared';

export function Test() {
    return (
        <div className="container test__container">
            <div className="test">
                <PageHeader page="test" />
                <TestComp1 />
                <TestComp2 />
            </div>
        </div>
    );
}
