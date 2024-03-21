package com.ssafy.pennypal.bank.service.db;

import com.ssafy.pennypal.bank.dto.service.request.UserApiKeyRequestDTO;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
class BankServiceDBImplTest {

    @Autowired
    private IMemberRepository memberRepository;

    @Autowired
    private IBankServiceDB bankServiceDB;

    @DisplayName("Member Entity 에 KEY 값이 잘 들어 가는가")
    @Test
    void Member_Entity_KEY() {
        // given
        UserApiKeyRequestDTO userApiKeyRequestDTO = UserApiKeyRequestDTO.builder()
                .userKey("1234567890")
                .userId("mine702@naver.com")
                .build();
        // when
        bankServiceDB.InsertUserKey(userApiKeyRequestDTO);
        // then
        Member member = memberRepository.findByMemberEmail(userApiKeyRequestDTO.getUserId());
        Assertions.assertThat(member.getMemberBankApi()).isEqualTo(userApiKeyRequestDTO.getUserKey());
    }

}