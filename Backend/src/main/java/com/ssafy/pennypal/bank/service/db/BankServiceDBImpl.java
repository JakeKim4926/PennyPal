package com.ssafy.pennypal.bank.service.db;

import com.ssafy.pennypal.bank.dto.service.request.UserApiKeyRequestDTO;
import com.ssafy.pennypal.bank.repository.IBankRepository;
import com.ssafy.pennypal.domain.member.entity.Member;
import com.ssafy.pennypal.domain.member.repository.IMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BankServiceDBImpl implements IBankServiceDB {

    private final IBankRepository bankRepository;
    private final IMemberRepository memberRepository;

    @Override
    @Transactional
    public void InsertUserKey(UserApiKeyRequestDTO userApiKeyRequestDTO) {
        log.info("userApiKeyRequestDTO = {}", userApiKeyRequestDTO);
        Member member = memberRepository.findByMemberEmail(userApiKeyRequestDTO.getUserId());
        member.setMemberBankApi(userApiKeyRequestDTO.getUserKey());
    }
}
