import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faChevronLeft, faChevronRight } from '@fortawesome/free-solid-svg-icons';

interface DataStat {
    icon: string;
    label: string;
    value: string;
}

interface CarouselItem {
    id: number;
    stats: DataStat[];
}

interface IBigDataRecommendingProps {
    favCategory1: String;
    favCategory2: String;
    items: CarouselItem[];
}

export function ExpenditureRecommend({ favCategory1, favCategory2, items }: IBigDataRecommendingProps) {
    const [carouselIndex, setCarouselIndex] = useState(0);

    const goToNext = () => {
        setCarouselIndex((prevIndex) => (prevIndex + 1) % items.length);
    };

    const goToPrevious = () => {
        setCarouselIndex((prevIndex) => (prevIndex - 1 + items.length) % items.length);
    };

    const renderStats = (stats: DataStat[]) => {
        return stats.map((stat, index) => (
            <div key={index} className="stat">
                <img src={stat.icon} alt={stat.label} />
                <span>{stat.label}</span>
                <strong>{stat.value}</strong>
            </div>
        ));
    };

    return (
        <div className="expenditureRecommend contentCard">
            <div className="expenditureRecommend__title contentCard__title">
                <div className="contentCard__title-text">BIGDATA RECOMMENDING</div>
                <div className="expenditureRecommend__title-text">
                    <span>내 소비패턴에 맞는 카드</span> 사용하고 지출을 줄여보세요!
                </div>
            </div>

            <div className="expenditureRecommend__content">
                <div className="expenditureRecommend__content-slider">
                    <div className="expenditureRecommend__content-slider-carousel">
                        <button onClick={goToPrevious} aria-label="Previous">
                            <FontAwesomeIcon icon={faChevronLeft} size="xl" />
                        </button>
                        <div className="expenditureRecommend__content-slider-carousel-item">
                            <div className="expenditureRecommend__content-slider-carousel-item-img"></div>
                            <div className="expenditureRecommend__content-slider-carousel-item-info">
                                {renderStats(items[carouselIndex].stats)}
                                <div className="expenditureRecommend__content-slider-carousel-item-info-title">
                                    <div className="expenditureRecommend__content-slider-carousel-item-info-title-name">
                                        카드명
                                    </div>
                                    <div className="expenditureRecommend__content-slider-carousel-item-info-title-brand">
                                        카드사
                                    </div>
                                </div>
                                <div className="expenditureRecommend__content-slider-carousel-item-info-benefits">
                                    <div className="expenditureRecommend__content-slider-carousel-item-info-benefits-benefit">
                                        혜택 1
                                    </div>
                                    <div className="expenditureRecommend__content-slider-carousel-item-info-benefits-benefit">
                                        혜택 2
                                    </div>
                                    <div className="expenditureRecommend__content-slider-carousel-item-info-benefits-benefit">
                                        혜택 3
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button onClick={goToNext} aria-label="Next">
                            <FontAwesomeIcon icon={faChevronRight} size="xl" />
                        </button>
                    </div>
                    <div className="expenditureRecommend__content-slider-indicator">
                        {items.map((item, index) => (
                            <span
                                key={item.id}
                                className={`dot ${index === carouselIndex ? 'active' : ''}`}
                                onClick={() => setCarouselIndex(index)}
                            ></span>
                        ))}
                    </div>
                </div>
                <div className="expenditureRecommend__content-comment">
                    <span>{favCategory1}</span>, <span>{favCategory2}</span>을 좋아하는 00님을 위한 맞춤 카드 추천
                </div>
            </div>
        </div>
    );
}
