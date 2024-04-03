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
                    <div className="expenditureRecommend__content-slider-carousel-item-info-benefits-benefit">
                        <div className="expenditureRecommend__content-slider-carousel-item-info-benefits-benefit-category">
                            {card.cardTopCategory}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ExpenditureRecommendCard;
