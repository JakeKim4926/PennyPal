import React, { useRef, useEffect } from 'react';
import Chart from 'chart.js/auto';

const PieChart: React.FC = () => {
    const chartRef = useRef<HTMLCanvasElement>(null);

    useEffect(() => {
        if (chartRef && chartRef.current) {
            const chartContext = chartRef.current.getContext('2d');
            if (chartContext) {
                new Chart(chartContext, {
                    type: 'pie',
                    data: {
                        labels: ['Red', 'Blue', 'Yellow'],
                        datasets: [
                            {
                                data: [12, 19, 3],
                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.6)',
                                    'rgba(54, 162, 235, 0.6)',
                                    'rgba(255, 206, 86, 0.6)',
                                ],
                                borderColor: ['rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)'],
                                borderWidth: 1,
                            },
                        ],
                    },
                    options: {
                        responsive: true,
                    },
                });
            }
        }
    }, []);

    return <canvas ref={chartRef} />;
};

export default PieChart;
