package com.ssafy.pennypal.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Long expenseId;                                     // 지출 내역 id

    @Column(name = "expense_date")
    private LocalDate expenseDate;                              // 지출 일자

    @Column(name = "expense_amount")
    private Integer expenseAmount;                              // 지출 금액

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Expense(LocalDate expenseDate, Integer expenseAmount, Member member) {
        this.expenseDate = expenseDate;
        this.expenseAmount = expenseAmount;
        this.member = member;
    }
}
