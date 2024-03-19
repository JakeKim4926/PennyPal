package com.ssafy.pennypal.bank.service.db;

import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class BankServiceDBImplTest {
    private final IMemberRepository memberRepository;

    public BankServiceDBImplTest(IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @DisplayName("사용자 은행 API 키 를 발급 받았을때 Member Entity 에 값이 잘 들어 가는가")
    @Test
    void 사용자_은행_API() {
        // given

        // when

        // then
    }

}