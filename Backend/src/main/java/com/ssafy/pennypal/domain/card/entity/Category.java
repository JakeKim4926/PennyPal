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
    String categoryShopping;

    @Column(name = "category_culture")
    String categoryCulture;

    @Column(name = "category_transportation")
    String categoryTransportation;

    @Column(name = "category_car")
    String categoryCar;

    @Column(name = "category_food")
    String categoryFood;

    @Column(name = "category_education")
    String categoryEducation;

    @Column(name = "category_housing_communication")
    String categoryHousingCommunication;

    @Column(name = "category_travel")
    String categoryTravel;

    @Column(name = "category_medical")
    String categoryMedical;

    @Column(name = "category_financial_insurance")
    String categoryFinancialInsurance;

    @Column(name = "category_others")
    String categoryOthers;

    @OneToOne(mappedBy = "category")
    private Card card;
}
