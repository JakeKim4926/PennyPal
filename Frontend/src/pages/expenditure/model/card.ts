export interface DataStat {
    label: string;
}

export interface CardData {
    cardId: number;
    cardCompany: string;
    cardImg: string;
    cardName: string;
    cardTopCategory: string;
}

export interface RecommendedCardResponse {
    cardCompany: string;
    cardName: string;
    cardTopCategory: string;
    cardImg: string;
}
