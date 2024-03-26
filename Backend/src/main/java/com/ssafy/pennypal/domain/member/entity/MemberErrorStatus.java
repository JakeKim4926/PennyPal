package com.ssafy.pennypal.domain.member.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorStatus {
    EMAIL_EXIST(420),
    NICKNAME_EXIST(421);

    private final int value;
}
