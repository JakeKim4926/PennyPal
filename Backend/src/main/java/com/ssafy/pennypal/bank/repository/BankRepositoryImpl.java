package com.ssafy.pennypal.bank.repository;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.global.common.support.Querydsl5RepositorySupport;

public class BankRepositoryImpl extends Querydsl5RepositorySupport implements IBankRepositoryCustom {

    public BankRepositoryImpl() {
        super(Member.class);
    }
}
