package com.ssafy.pennypal.bank.repository;

import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.global.common.support.Querydsl5RepositorySupport;

public class IBankRepositoryImpl extends Querydsl5RepositorySupport implements IBankRepositoryCustom {

    public IBankRepositoryImpl() {
        super(Member.class);
    }
}
