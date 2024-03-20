package com.ssafy.pennypal.domain.team.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.team.dto.request.TeamCreateRequest;
import com.ssafy.pennypal.domain.team.service.TeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TeamController.class)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TeamService teamService;

    // todo : 강의 듣고 다시 작성
//    @DisplayName("팀을 생성한다.")
//    @Test
//    void createTeam() throws Exception {
//        // given
//
//        Member member = createMember("member1@pennypal.site", "짠1", LocalDateTime.now());
//
//        TeamCreateRequest request = TeamCreateRequest.builder()
//                .teamName("팀이름1")
//                .teamIsAutoConfirm(false)
//                .teamLeaderId(member.getMemberId())
//                .teamInfo("팀소개입니다.")
//                .build();
//
//        // when, then
//        mockMvc.perform(
//                        post("/team/api/create")
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("create-team",preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        responseFields(
//                                fieldWithPath("code").type(JsonFieldType.NUMBER)
//                                        .description("코드"),
//                                fieldWithPath("status").type(JsonFieldType.STRING)
//                                        .description("상태"),
//                                fieldWithPath("message").type(JsonFieldType.STRING)
//                                        .description("메시지"),
//                                fieldWithPath("data").type(JsonFieldType.OBJECT)
//                                        .description("응답 데이터"),
//                                fieldWithPath("data.teamName").type(JsonFieldType.STRING)
//                                        .description("팀 명"),
//                                fieldWithPath("data.teamIsAutoConfirm").type(JsonFieldType.BOOLEAN)
//                                        .description(" 자동가입 승인 여부"),
//                                fieldWithPath("data.teamLeaderId").type(JsonFieldType.NUMBER)
//                                        .description("팀장 Id"),
//                                fieldWithPath("data.teamInfo").type(JsonFieldType.STRING)
//                                        .description("팀 한줄소개")
//                        )
//                )
//        );
//
//    }

    private Member createMember(String memberEmail, String memberNickname, LocalDateTime memberBirthDate){
        return Member.builder()
                .memberEmail(memberEmail)
                .memberPassword("1234")
                .memberName("김김짠돌")
                .memberNickname(memberNickname)
                .memberBirthDate(memberBirthDate)
                .build();
    }


}