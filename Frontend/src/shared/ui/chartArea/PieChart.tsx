import React, { useRef, useEffect } from 'react';
import Chart from 'chart.js/auto';

type PieChartProps = {
    datas: number[];
    labels: string[];
    backgroundColors: string[];
};

const PieChart = ({ datas, labels, backgroundColors }: PieChartProps) => {
    const chartContainer = useRef<HTMLCanvasElement | null>(null);

    useEffect(() => {
        if (!chartContainer.current) return;

        const ctx = chartContainer.current.getContext('2d')!;

        new Chart(ctx, {
            type: 'pie',
            data: {
                labels: labels,
                datasets: [
                    {
                        data: datas,
                        backgroundColor: backgroundColors,
                        borderColor: '#FCFCFC',
                        borderWidth: 1,
                    },
                ],
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'right',
                        labels: {
                            font: {
                                family: 'Wanted-Medium',
                                size: 14,
                            },
                        },
                    },
                },
            },
        });
    }, [datas, labels, backgroundColors]);

    return (
        <div className="expenditureAnalyze__pieChart">
            <canvas className="expenditureAnalyze__pieChart-chart" ref={chartContainer}></canvas>
        </div>
    );
};

export default PieChart;
