package com.ssafy.pennypal.domain.member.controller;

import com.ssafy.pennypal.common.RestDocsSupport;
import com.ssafy.pennypal.domain.member.dto.request.MemberSignupRequest;
import com.ssafy.pennypal.domain.member.service.MemberService;
import com.ssafy.pennypal.domain.team.controller.TeamControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class MemberControllerTest extends RestDocsSupport {

    private final MemberService memberService = mock(MemberService.class);

    @Override
    protected Object initController() {
        return new MemberController(memberService);
    }

    @DisplayName("회원가입을 진행한다.")
    @Test
    void test() {
        // given
        MemberSignupRequest.builder()

                .build();

        // when

        // then
    }


}