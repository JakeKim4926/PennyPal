import React, { useRef, useEffect } from 'react';
import Chart from 'chart.js/auto';

export function TeamInfoTeamExpenditure() {
    const data = [
        [100000, 150000, 170000, 180000, 100000, 2000, 90000],
        [50000, 100000, 50000, 50000, 50000, 40000, 60000],
    ];

    const prevSum = data[0].reduce((prev, cur) => prev + cur, 0); // 이전주 지출 총액
    const presSum = data[1].reduce((prev, cur) => prev + cur, 0); // 이번주 지출 총액
    const savingRate = ((presSum / prevSum) * 100).toFixed(1); // 절감률
    const spendDiffer = Math.abs(presSum - prevSum).toLocaleString(); // 지출 차액

    let savingState; // 지출 상태 (절감 / 유지 / 증가)
    if (prevSum > presSum) {
        savingState = '절감!';
    } else if (prevSum === presSum) {
        savingState = '유지 중!';
    } else {
        savingState = '증가!';
    }

    return (
        <div className="teamInfoTeamExpenditure contentCard">
            <div className="teamInfoTeamExpenditure__title contentCard__title">
                <div className="teamInfoTeamExpenditure__title-text contentCard__title-text">TEAM EXPENDITURE</div>
            </div>
            <LineChart data={data} />
            <div className="teaminfoTeamExpenditure__bottom">
                <div className="teaminfoTeamExpenditure__bottom-left">
                    <div className="teaminfoTeamExpenditure__bottom-left-prev">
                        <div className="teaminfoTeamExpenditure__bottom-left-prev-title">이전 주차 지출액</div>
                        <div className="teaminfoTeamExpenditure__bottom-left-prev-value">
                            <span className="value">{prevSum.toLocaleString()}</span>
                            <span className="unit">원</span>
                        </div>
                    </div>
                    <div className="teaminfoTeamExpenditure__bottom-left-pres">
                        <div className="teaminfoTeamExpenditure__bottom-left-pres-title">이번 주차 지출액</div>
                        <div className="teaminfoTeamExpenditure__bottom-left-pres-value">
                            <span className="value">{presSum.toLocaleString()}</span>
                            <span className="unit">원</span>
                        </div>
                    </div>
                </div>
                <div className="teaminfoTeamExpenditure__bottom-right">
                    <div className="teaminfoTeamExpenditure__bottom-right-prev">
                        <div className="teaminfoTeamExpenditure__bottom-right-prev-title">전 주 대비</div>
                        <div className="teaminfoTeamExpenditure__bottom-right-prev-value">
                            {savingRate !== '100.0' ? `${savingRate}%` : ''} {savingState}
                        </div>
                    </div>
                    <div className="teaminfoTeamExpenditure__bottom-right-pres">
                        <div className="teaminfoTeamExpenditure__bottom-right-pres-title">전 주 대비</div>
                        <div className="teaminfoTeamExpenditure__bottom-right-pres-value">
                            {savingRate !== '100.0' ? `${spendDiffer}` : ''} {savingState}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

type LineCharProps = {
    data: number[][];
};

const LineChart = ({ data }: LineCharProps) => {
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
                        data: data[0],
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
                        data: data[1],
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
                        offset: true,
                    },
                },
            },
        });
    }, []);

    return (
        <div className="teamInfoTeamExpenditure__chartArea">
            <canvas className="teamInfoTeamExpenditure__chartArea-chart" ref={chartContainer} height="100"></canvas>
        </div>
    );
};

export default LineChart;
