import React, { useRef, useEffect } from 'react';
import Chart from 'chart.js/auto';

export function TeamInfoTeamExpenditure() {
    return (
        <div className="teamInfoTeamExpenditure contentCard">
            <div className="teamInfoTeamExpenditure__title contentCard__title">
                <div className="teamInfoTeamExpenditure__title-text contentCard__title-text">TEAM EXPENDITURE</div>
            </div>
            <LineChart />
        </div>
    );
}

const LineChart = () => {
    const chartContainer = useRef<HTMLCanvasElement | null>(null);

    useEffect(() => {
        if (!chartContainer.current) return;

        const ctx = chartContainer.current.getContext('2d')!;

        new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN'],
                datasets: [
                    {
                        label: '지난주',
                        data: [65, 59, 80, 81, 56, 55, 40],
                        fill: false,
                        borderColor: 'rgb(136, 186, 83)',
                        pointRadius: 5,
                        pointBackgroundColor: 'rgb(136, 186, 83)',
                        backgroundColor: 'rgb(136, 186, 83)',
                        pointStyle: 'circle',
                        tension: 0.1,
                    },
                    {
                        label: '이번주',
                        data: [23, 35, 68, 5, 34, 12, 46],
                        fill: false,
                        borderColor: 'rgb(235, 192, 105)',
                        pointRadius: 5,
                        pointBackgroundColor: 'rgb(235, 192, 105)',
                        backgroundColor: 'rgb(235, 192, 105)',
                        pointStyle: 'circle',
                        tension: 0.1,
                    },
                ],
            },
            options: {
                plugins: {
                    legend: {
                        align: 'end',
                        position: 'top',
                        onClick: () => {},
                        labels: {
                            usePointStyle: true,
                            pointStyle: 'circle',
                        },
                    },
                },
                scales: {
                    x: {
                        grid: {
                            display: false,
                        },
                        offset: true,
                    },
                    y: {
                        ticks: {
                            callback: function (value) {
                                return '';
                            },
                        },
                        grid: {
                            display: false,
                        },
                    },
                },
            },
        });
    }, []);

    return (
        <div>
            <canvas ref={chartContainer} height="100"></canvas>
        </div>
    );
};

export default LineChart;
