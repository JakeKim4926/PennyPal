import React, { useRef, useEffect } from 'react';
import { ChartArea } from '@/shared';

export function ExpenditureGraph() {
    const data = [
        [100000, 10000, 170000, 80000, 100000, 2000, 90000],
        [50000, 100000, 50000, 50000, 50000, 40000, 60000],
    ];

    return (
        <div className="contentCard">
            <div className="contentCard__title">
                <div className="contentCard__title-text">GRAPH</div>
            </div>
            <ChartArea data={data} />
        </div>
    );
}
