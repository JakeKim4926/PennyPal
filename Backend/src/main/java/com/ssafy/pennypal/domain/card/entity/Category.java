package com.ssafy.pennypal.domain.card.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    Long categoryId;

    @Column(name = "category_shopping")
    Double categoryShopping;

    @Column(name = "category_culture")
    Double categoryCulture;

    @Column(name = "category_transportation")
    Double categoryTransportation;

    @Column(name = "category_car")
    Double categoryCar;

    @Column(name = "category_food")
    Double categoryFood;

    @Column(name = "category_education")
    Double categoryEducation;

    @Column(name = "category_housing_communication")
    Double categoryHousingCommunication;

    @Column(name = "category_travel")
    Double categoryTravel;

    @Column(name = "category_medical")
    Double categoryMedical;

    @Column(name = "category_financial_insurance")
    Double categoryFinancialInsurance;

    @Column(name = "category_others")
    Double categoryOthers;

    @OneToOne(mappedBy = "category")
    private Card card;
}
