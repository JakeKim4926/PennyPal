import { ChartArea } from '@/shared';

export function ExpenditureGraph() {
    const data = [
        [100000, 20000, 170000, 80000, 100000, 10000, 90000],
        [50000, 100000, 50000, 150000, 50000, 80000, 60000],
    ];

    return (
        <div className="expenditureGraph contentCard">
            <div className="contentCard__title">
                <div className="contentCard__title-text">GRAPH</div>
            </div>

            <ChartArea data={data} />
        </div>
    );
}
