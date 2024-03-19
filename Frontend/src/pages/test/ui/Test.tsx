import React from 'react';
import { Button } from '../../../shared';
import { TestComp1 } from './TestComp1/TestComp1';
import { TestComp2 } from './TestComp2/TestComp2';

export function Test() {
    return (
        <div>
            <TestComp1 />
            <TestComp2 />
        </div>
    );
}
