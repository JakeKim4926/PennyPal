export interface CardData {
    cardId: number;
    cardCompany: string;
    cardImg: string;
    cardName: string;
    cardTopCategory: string;
}

export interface ExpenditureRecommendCardProps {
    card: CardData;
}
