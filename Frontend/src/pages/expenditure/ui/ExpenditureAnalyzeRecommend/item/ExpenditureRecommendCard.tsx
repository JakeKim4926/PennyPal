import React from 'react';

interface CardData {
    cardId: number;
    cardCompany: string;
    cardImg: string;
    cardName: string;
    cardTopCategory: string;
}

interface ExpenditureRecommendCardProps {
    card: CardData;
}

function ExpenditureRecommendCard({ card }: ExpenditureRecommendCardProps) {
    if (!card) return null;

    const benefits = card.cardTopCategory.split(',').filter((benefit) => benefit.trim() !== '');

    return (
        <div className="expenditureRecommend__content-slider-carousel-item">
            <div className="expenditureRecommend__content-slider-carousel-item-imgBg">
                <div className="expenditureRecommend__content-slider-carousel-item-imgBg-imgSpace">
                    <div
                        className="expenditureRecommend__content-slider-carousel-item-imgBg-imgSpace-img"
                        style={{ backgroundImage: `url(${card.cardImg})` }}
                    ></div>
                </div>
            </div>
            <div className="expenditureRecommend__content-slider-carousel-item-info">
                <div className="expenditureRecommend__content-slider-carousel-item-info-title">
                    <div className="expenditureRecommend__content-slider-carousel-item-info-title-name">
                        {card.cardName}
                    </div>
                    <div className="expenditureRecommend__content-slider-carousel-item-info-title-brand">
                        {card.cardCompany}
                    </div>
                </div>
                <div className="expenditureRecommend__content-slider-carousel-item-info-benefits">
                    {/* 혜택명을 순회하면서 span 태그로 표시 */}
                    {benefits.map((benefit, index) => (
                        <div
                            key={index}
                            className="expenditureRecommend__content-slider-carousel-item-info-benefits-benefit"
                        >
                            <span>{benefit} 혜택!</span>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default ExpenditureRecommendCard;
