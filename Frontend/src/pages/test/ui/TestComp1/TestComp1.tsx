import React, { useState } from 'react';
import { Button } from '../../../../shared';
import { increaseCount } from '../../model/TestComp1/increaseCount';

export function TestComp1() {
    const [count, setCount] = useState(1);
    const handleClick = increaseCount(count, setCount);

    return (
        <div>
            <Button child={`${count}`} color={'color-main'} onClick={handleClick} />
        </div>
    );
}
