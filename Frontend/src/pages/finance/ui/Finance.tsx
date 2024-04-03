import React, { useEffect, useRef, useState } from 'react';
import CardComponent from '@/pages/finance/ui/CardComponent/CardComponent';
import StockComponent from '@/pages/finance/ui/StockComponent/StockComponent';
import SavingsComponent from '@/pages/finance/ui/SavingsComponent/SavingsComponent';
import { CardListUp, StockDetail, StockListUp, StockSubDetail } from '@/pages/finance/model';
import { useNavigate } from 'react-router-dom';
import { Chart, ChartConfiguration } from 'chart.js';
import { PageHeader } from '@/shared';
interface Stock {
    stockId: number;
    crno: string;
    isinCd: string;
    stckIssuCmpyNm: string;
    basDt: number[];
    stckGenrDvdnAmt: number;
}
interface SubDetail {
    basDt: string; // 기준일
    srtnCd: string; // 단축코드
    isinCd: string; // 국제증권식별번호
    itmsNm: string; // 항목명, 예: "한국앤컴퍼니"
    mrktCtg: string; // 시장 카테고리, 예: "KOSPI"
    clpr: string; // 종가
    vs: string; // 변동
    fltRt: string; // 변동률
    mkp: string; // 시가
    hipr: string; // 고가
    lopr: string; // 저가
    trqu: string; // 거래량
    trPrc: string; // 거래 가격
    lstgStCnt: string; // 상장 주식 수
    mrktTotAmt: string; // 시장 총액
}

interface StockWithDetail {
    stock: Stock;
    subDetail?: SubDetail; // 세부 정보는 선택적으로 존재할 수 있습니다.
}
interface StockTransaction {
    basDt: string;
    stckGenrDvdnAmt: number;
}
interface Card {
    cardId: number;
    cardType: string;
    cardCompany: string;
    cardName: string;
    cardBenefitType: string;
    cardImg: string;
    cardTopCategory: string;
    cardCategory: string;
    cardRequired: number;
    cardDomestic: number;
    cardAbroad: number;
}

export function Finance() {
    const [data, setData] = useState<StockWithDetail[]>([]);
    const [data1, setData1] = useState<Card[]>([]);
    const navigate = useNavigate();
    const canvasRef = useRef<HTMLCanvasElement | null>(null);
    const [chartInstance, setChartInstance] = useState<Chart | null>(null);

    // 데이터가 없을 때 기본 메시지를 그리는 함수
    const drawPlaceholder = () => {
        const canvas = canvasRef.current;
        if (canvas) {
            // canvas가 null이 아닐 때만 실행
            const ctx = canvas.getContext('2d');
            if (ctx) {
                ctx.clearRect(0, 0, canvas.width, canvas.height); // 이전 그림을 지웁니다.
                ctx.fillStyle = '#cccccc'; // 텍스트 색상 설정
                ctx.textAlign = 'center'; // 텍스트 정렬 설정
                ctx.font = '16px Arial'; // 텍스트 스타일 설정
                ctx.fillText('주식에 마우스를 올려주세요!', canvas.width / 2, canvas.height / 2); // 텍스트 그리기
            }
        }
    };

    useEffect(() => {
        // 컴포넌트가 마운트될 때 기본 메시지 그리기
        drawPlaceholder();
    }, []);

    const handleMouseEnter = async (stockId: number) => {
        try {
            const res = await StockDetail(stockId);
            if (res.data.code === 200) {
                let stockTransactions = res.data.data.stockWithTransactionListDtoList;

                // 기준이 될 마지막 데이터의 값과 날짜를 가져옵니다.
                const lastData = stockTransactions[stockTransactions.length - 1];
                const baseValue = lastData.stckGenrDvdnAmt;
                const baseDate = new Date(lastData.basDt);

                // 3일 전부터 데이터를 생성합니다.
                const generatedData = [];
                for (let i = 3; i > 0; i--) {
                    const newDate = new Date(baseDate);
                    newDate.setDate(baseDate.getDate() - i);
                    const dateString = newDate.toISOString().split('T')[0];

                    // +-20% 범위 내에서 무작위 값을 생성합니다.
                    const randomFactor = Math.random() * 0.4 + 0.8; // 0.8 ~ 1.2
                    const newValue = baseValue * randomFactor;

                    generatedData.push({
                        basDt: dateString,
                        stckGenrDvdnAmt: Math.round(newValue),
                    });
                }

                // 생성된 데이터와 기존 데이터를 합칩니다.
                stockTransactions = [...generatedData, ...stockTransactions];

                drawChart(stockTransactions); // 차트 그리는 함수 호출
            }
        } catch (err) {
            console.error(err);
        }
    };
    const adjustImageStyle = (e: React.SyntheticEvent<HTMLImageElement, Event>) => {
        const imgElement = e.currentTarget; // 타입 단언
        if (imgElement.naturalWidth > imgElement.naturalHeight) {
            // 가로가 세로보다 긴 경우
            imgElement.style.width = '150px';
            imgElement.style.height = 'auto';
        } else {
            // 세로가 가로보다 긴 경우 또는 정사각형
            imgElement.style.width = 'auto';
            imgElement.style.height = '150px';
        }
    };
    const drawChart = (stockTransactions: StockTransaction[]) => {
        const canvas = document.getElementById('myChart') as HTMLCanvasElement | null;

        if (canvas) {
            const chartConfig: ChartConfiguration = {
                type: 'line',
                data: {
                    labels: stockTransactions.map((t) => t.basDt),
                    datasets: [
                        {
                            label: '배당금',
                            data: stockTransactions.map((t) => t.stckGenrDvdnAmt),
                            fill: false,
                            borderColor: 'rgb(75, 192, 192)',
                            tension: 0.1,
                            pointBackgroundColor: '#fff', // 데이터 포인트 배경 색상
                            pointBorderColor: '#007bff', // 데이터 포인트 테두리 색상
                            pointHoverBackgroundColor: '#007bff', // 호버 시 데이터 포인트 배경 색상
                            pointHoverBorderColor: '#fff', // 호버 시 데이터 포인트 테두리 색상
                        },
                    ],
                },
                options: {
                    scales: {
                        y: {
                            display: false, // y축 단위값 숨김
                        },
                        x: {
                            grid: {
                                display: false, // x축 격자 무늬 숨김
                            },
                        },
                    },
                    plugins: {
                        tooltip: {
                            enabled: true, // 데이터 호버링 시 툴팁 활성화
                            mode: 'index',
                            intersect: false,
                        },
                        legend: {
                            display: false, // 범례 숨김
                        },
                    },
                    elements: {
                        line: {
                            borderWidth: 2, // 선 두께 조정
                        },
                        point: {
                            radius: 5, // 데이터 포인트 반지름
                            hoverRadius: 7, // 호버 시 데이터 포인트 반지름
                        },
                    },
                    interaction: {
                        intersect: false,
                        mode: 'nearest',
                    },
                    responsive: true, // 반응형 차트 설정
                    maintainAspectRatio: false, // 차트의 종횡비 유지 여부
                },
            };

            // 기존 차트 인스턴스가 있다면 제거합니다.
            if (chartInstance) {
                chartInstance.destroy();
            }

            // 새로운 차트 인스턴스를 생성합니다.
            // 여기서는 `canvas`를 직접 전달합니다.
            const newChartInstance = new Chart(canvas, chartConfig);
            setChartInstance(newChartInstance);
        }
    };
    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await StockListUp();
                if (res.data.code === 200) {
                    const stocks: StockWithDetail[] = res.data.data.content.map((stock: Stock) => ({
                        stock: stock,
                    }));
                    setData(stocks);
                }
            } catch (err) {
                console.error(err);
            }
            try {
                const res = await CardListUp(0, 4);
                if (res.data.code === 200) {
                    setData1(res.data.data.content);
                }
            } catch (err) {
                console.error(err);
            }
        };

        fetchData();
    }, []);
    useEffect(() => {
        const fetchSubDetail = async () => {
            try {
                for (const el of data) {
                    const res = await StockSubDetail(el.stock.isinCd);
                    console.log(res);
                }
            } catch (err) {
                console.log(err);
            }
        };

        fetchSubDetail();
    }, [data]);
    return (
        <div className="container bg-grey">
            <div className="finance">
                <PageHeader page="finance"></PageHeader>
                <br />
                <div className="card">
                    <div className="stock__header">
                        <p>다양한 혜택 분야!</p>
                        <h2>혜택 좋은 카드 어때요?</h2>
                    </div>
                    <div className="finance__morebutton">
                        <a onClick={() => navigate('/financedetail')}>
                            <img src="@/../assets/image/arrowHorizontal.svg" />
                        </a>
                    </div>
                    <div className="card__content">
                        {data1.map((card) => (
                            <div key={card.cardId} className="card__content--item">
                                <div className="image-container">
                                    <img src={card.cardImg} onLoad={adjustImageStyle} />
                                </div>
                                <div className="namebox">
                                    <h3>{card.cardName}</h3>
                                    <span>{card.cardCompany}</span>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
                <br />
                <div className="stock">
                    <div className="stock__header">
                        <p>주식은 단타가 아냐</p>
                        <h2>배당률 좋은 주식 어때요?</h2>
                    </div>
                    <div className="finance__morebutton">
                        <a onClick={() => navigate('/financedetail')}>
                            <img src="@/../assets/image/arrowHorizontal.svg" />
                        </a>
                    </div>
                    <div className="stock__content">
                        {data.map((stock) => (
                            <div
                                key={stock.stock.stockId}
                                className="stock__content--item"
                                onMouseEnter={() => handleMouseEnter(stock.stock.stockId)} // 마우스 호버 이벤트 핸들러
                            >
                                <div className="stock__content--companyName">{stock.stock.stckIssuCmpyNm}</div>
                                <div className="stock__content--info">
                                    <span>배당금: {stock.stock.stckGenrDvdnAmt.toLocaleString()} 원</span>
                                </div>
                                <div className="stock__content--info">
                                    <span>기준일: {stock.stock.basDt}</span>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
                <div>
                    <canvas ref={canvasRef} id="myChart" width="600" height="250"></canvas>
                </div>
            </div>
        </div>
    );
}
