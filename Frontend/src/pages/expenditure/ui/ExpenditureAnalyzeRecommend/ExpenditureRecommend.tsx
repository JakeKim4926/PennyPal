import React, { useState, useEffect } from 'react';
import ExpenditureRecommendCard from './item/ExpenditureRecommendCard';
import { getCookie } from '@/shared';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faChevronLeft, faChevronRight, faSpinner } from '@fortawesome/free-solid-svg-icons';
import { Card, fetchRecommendedCards } from '../../model/fetchFunctions';

interface IBigDataRecommendingProps {
    ready: boolean;
}

// export function ExpenditureRecommend({ items, ready }: IBigDataRecommendingProps) {
export function ExpenditureRecommend({ ready }: IBigDataRecommendingProps) {
    const [cards, setCards] = useState<Card[]>([]);
    const [isLoading, setIsLoading] = useState(true); // 로딩 상태 관리를 위한 상태 추가

    useEffect(() => {
        if (ready) {
            setIsLoading(true); // 로딩 시작
            const loadCards = async () => {
                const fetchedCards = await fetchRecommendedCards();
                setCards(fetchedCards);
                setIsLoading(false); // 로딩 완료
            };

            loadCards();
        }
    }, [ready]);

    const [carouselIndex, setCarouselIndex] = useState(0);

    const goToNext = () => {
        setCarouselIndex((prevIndex) => (prevIndex + 1) % cards.length);
    };
    const goToPrevious = () => {
        setCarouselIndex((prevIndex) => (prevIndex - 1 + cards.length) % cards.length);
    };

    const memberNickname = getCookie('memberNickname');

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
                    {isLoading ? (
                        <div className="loadingDiv">
                            <p>LOADING...</p>
                            <FontAwesomeIcon icon={faSpinner} spinPulse />
                        </div>
                    ) : (
                        <div className="expenditureRecommend__content-slider-carousel">
                            <button onClick={goToPrevious} aria-label="Previous" className="clickable-icon">
                                <FontAwesomeIcon icon={faChevronLeft} size="xl" />
                            </button>
                            <ExpenditureRecommendCard card={cards[carouselIndex]} />
                            <button onClick={goToNext} aria-label="Next" className="clickable-icon">
                                <FontAwesomeIcon icon={faChevronRight} size="xl" />
                            </button>
                        </div>
                    )}
                    <div className="expenditureRecommend__content-slider-indicator">
                        {cards.map((item, index) => (
                            <div
                                key={item.cardId}
                                className={`dot ${index === carouselIndex ? 'active' : ''}`}
                                onClick={() => setCarouselIndex(index)}
                            ></div>
                        ))}
                    </div>
                </div>
            </div>
            <div className="expenditureRecommend__comment">
                <span>{memberNickname}님</span>을 위한 맞춤 카드 추천!
            </div>
        </div>
    );
}
