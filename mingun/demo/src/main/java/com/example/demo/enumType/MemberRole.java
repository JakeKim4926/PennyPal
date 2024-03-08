package com.example.demo.enumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    TEAM_LEADER("팀장"),
    TEAM_MEMBER("팀원");

    private final String text;
}
