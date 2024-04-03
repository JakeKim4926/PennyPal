import React, { useState, useEffect } from 'react';
import ExpenditureRecommendCard from './item/ExpenditureRecommendCard';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faChevronLeft, faChevronRight } from '@fortawesome/free-solid-svg-icons';
import { Card, fetchRecommendedCards } from '../../model/fetchFunctions';

interface IBigDataRecommendingProps {
    favCategory: String;
    items: CardData[];
}

interface CardData {
    id: number;
    cardCompany: string;
    cardImg: string;
    cardName: string;
    cardTopCategory: DataStat[];
}

interface DataStat {
    label: string;
}

export function ExpenditureRecommend({ favCategory, items }: IBigDataRecommendingProps) {
    const [cards, setCards] = useState<Card[]>([]);

    useEffect(() => {
        const loadCards = async () => {
            const fetchedCards = await fetchRecommendedCards();
            setCards(fetchedCards);
        };

        loadCards();
    }, []);

    const [carouselIndex, setCarouselIndex] = useState(0);

    const goToNext = () => {
        setCarouselIndex((prevIndex) => (prevIndex + 1) % items.length);
    };
    const goToPrevious = () => {
        setCarouselIndex((prevIndex) => (prevIndex - 1 + items.length) % items.length);
    };

    return (
        <div className="expenditureRecommend contentCard">
            <div className="example">
                {cards.map((card, index) => (
                    <div key={index} style={{ margin: '10px', padding: '10px', border: '1px solid #ccc' }}>
                        <img src={card.cardImg} alt={card.cardName} style={{ width: '100px', height: 'auto' }} />
                        <div>
                            <strong>회사:</strong> {card.cardCompany}
                        </div>
                        <div>
                            <strong>카드 이름:</strong> {card.cardName}
                        </div>
                        <div>
                            <strong>카테고리:</strong> {card.cardTopCategory}
                        </div>
                    </div>
                ))}
            </div>
            <div className="expenditureRecommend__title contentCard__title">
                <div className="contentCard__title-text">BIGDATA RECOMMENDING</div>
                <div className="expenditureRecommend__title-text">
                    <span>내 소비패턴에 맞는 카드</span> 사용하고 지출을 줄여보세요!
                </div>
            </div>

            <div className="expenditureRecommend__content">
                <div className="expenditureRecommend__content-slider">
                    <div className="expenditureRecommend__content-slider-carousel">
                        <button onClick={goToPrevious} aria-label="Previous" className="clickable-icon">
                            <FontAwesomeIcon icon={faChevronLeft} size="xl" />
                        </button>
                        <ExpenditureRecommendCard card={items[carouselIndex]} />
                        <button onClick={goToNext} aria-label="Next" className="clickable-icon">
                            <FontAwesomeIcon icon={faChevronRight} size="xl" />
                        </button>
                    </div>
                    <div className="expenditureRecommend__content-slider-indicator">
                        {items.map((item, index) => (
                            <div
                                key={item.id}
                                className={`dot ${index === carouselIndex ? 'active' : ''}`}
                                onClick={() => setCarouselIndex(index)}
                            ></div>
                        ))}
                    </div>
                </div>
            </div>
            <div className="expenditureRecommend__comment">
                <span>{favCategory}</span> 좋아하는 00님을 위한 맞춤 카드 추천
            </div>
        </div>
    );
}
