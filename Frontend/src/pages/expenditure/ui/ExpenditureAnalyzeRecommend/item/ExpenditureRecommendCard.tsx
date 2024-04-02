import React from 'react';

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

interface ExpenditureRecommendCardProps {
    card: CardData;
}

function ExpenditureRecommendCard({ card }: ExpenditureRecommendCardProps) {
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
                    {card.cardTopCategory.map((stat, index) => (
                        <div
                            key={index}
                            className="expenditureRecommend__content-slider-carousel-item-info-benefits-benefit"
                        >
                            <div className="expenditureRecommend__content-slider-carousel-item-info-benefits-benefit-category">
                                {stat.label} 카테고리 혜택!
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default ExpenditureRecommendCard;
