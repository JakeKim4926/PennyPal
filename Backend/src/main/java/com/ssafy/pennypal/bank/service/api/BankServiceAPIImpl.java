package com.ssafy.pennypal.bank.service.api;

import com.ssafy.pennypal.bank.dto.service.request.UserAccountRequestServiceDTO;
import com.ssafy.pennypal.bank.dto.service.response.UserAccountResponseServiceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankServiceAPIImpl implements IBankServiceAPI {

    private static final String SSAFY_BANK_API_SERVER = System.getenv("SSAFY_BANK_API_SERVER");

    private final RestTemplate restTemplate;

    @Override
    public UserAccountResponseServiceDTO createUserAccount(UserAccountRequestServiceDTO userAccountRequestServiceDTO) {
        String url = SSAFY_BANK_API_SERVER + "member";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserAccountRequestServiceDTO> request = new HttpEntity<>(userAccountRequestServiceDTO, headers);
        return restTemplate.postForObject(url, request, UserAccountResponseServiceDTO.class);
    }

    @Override
    public UserAccountResponseServiceDTO getUserAccount(UserAccountRequestServiceDTO userAccountRequestServiceDTO) {
        String url = SSAFY_BANK_API_SERVER + "member/search";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserAccountRequestServiceDTO> request = new HttpEntity<>(userAccountRequestServiceDTO, headers);
        return restTemplate.postForObject(url, request, UserAccountResponseServiceDTO.class);
    }

}
