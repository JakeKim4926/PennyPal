package com.ssafy.pennypal.bank.service.api;

import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderRequestDTO;
import com.ssafy.pennypal.bank.dto.service.request.*;
import com.ssafy.pennypal.bank.dto.service.response.*;
import com.ssafy.pennypal.bank.exception.model.UserApiKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankServiceAPIImpl implements IBankServiceAPI {

    private static final String SSAFY_BANK_API_SERVER = System.getenv("SSAFY_BANK_API_SERVER");

    private final RestTemplate restTemplate;

//    private void extracted() {
//        // Add an interceptor to the RestTemplate to log the request
//        ClientHttpRequestInterceptor loggingInterceptor = (request, body, execution) -> {
//            log.info("Request Method: {}", request.getMethod());
//            log.info("Request URI: {}", request.getURI());
//            String requestBody = new String(body, StandardCharsets.UTF_8);
//            log.info("Request Body: {}", requestBody);
//
//            ClientHttpResponse response = execution.execute(request, body);
//
//            // Optionally log response
//            String responseBody = new BufferedReader(
//                    new InputStreamReader(response.getBody(), StandardCharsets.UTF_8)).lines()
//                    .collect(Collectors.joining("\n"));
//            log.info("Response Body: {}", responseBody);
//
//            return response;
//
//        };
//
//        // It's better to add this interceptor during RestTemplate bean creation to avoid adding it multiple times
//        restTemplate.getInterceptors().add(loggingInterceptor);
//    }

    @Override
    public UserAccountResponseServiceDTO createUserAccount(UserAccountRequestServiceDTO userAccountRequestServiceDTO) {
        String url = SSAFY_BANK_API_SERVER + "member";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserAccountRequestServiceDTO> request = new HttpEntity<>(userAccountRequestServiceDTO, headers);
        try {
            return restTemplate.postForObject(url, request, UserAccountResponseServiceDTO.class);
        } catch (RestClientException e) {
            throw new UserApiKeyException("중복된 이메일 입니다", e);
        }
    }

    @Override
    public UserAccountResponseServiceDTO getUserAccount(UserAccountRequestServiceDTO userAccountRequestServiceDTO) {
        String url = SSAFY_BANK_API_SERVER + "member/search";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserAccountRequestServiceDTO> request = new HttpEntity<>(userAccountRequestServiceDTO, headers);
        try {
            return restTemplate.postForObject(url, request, UserAccountResponseServiceDTO.class);
        } catch (RestClientException e) {
            throw new UserApiKeyException("존재하지 않는 아이디 입니다", e);
        }
    }

    @Override
    public UserBankAccountResponseServiceDTO createUserBankAccount(UserBankAccountRequestServiceDTO userBankAccountRequestServiceDTO) {
        String url = SSAFY_BANK_API_SERVER + "edu/account/openAccount";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserBankAccountRequestServiceDTO> request = new HttpEntity<>(userBankAccountRequestServiceDTO, headers);
        try {
            return restTemplate.postForObject(url, request, UserBankAccountResponseServiceDTO.class);
        } catch (RestClientException e) {
            throw new UserApiKeyException("존재하지 않는 아이디 입니다", e);
        }
    }


    @Override
    public UserBankAccountsResponseServiceDTO getUserAccountList(CommonHeaderRequestDTO commonHeaderRequestDTO) {
        String url = SSAFY_BANK_API_SERVER + "edu/account/inquireAccountList";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CommonHeaderRequestDTO> request = new HttpEntity<>(commonHeaderRequestDTO, headers);
        try {
            return restTemplate.postForObject(url, request, UserBankAccountsResponseServiceDTO.class);
        } catch (RestClientException e) {
            throw new UserApiKeyException("보유 하고 있는 계좌가 없습니다", e);
        }
    }

    @Override
    public AccountTransactionListResponseServiceDTO getUserAccountTransaction(AccountTransactionRequestServiceDTO accountTransactionRequestServiceDTO) {
        String url = SSAFY_BANK_API_SERVER + "edu/account/inquireAccountTransaction/History";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AccountTransactionRequestServiceDTO> request = new HttpEntity<>(accountTransactionRequestServiceDTO, headers);
        try {
            return restTemplate.postForObject(url, request, AccountTransactionListResponseServiceDTO.class);
        } catch (RestClientException e) {
            throw new UserApiKeyException("계좌 거래 내역이 존재 하지 않습니다", e);
        }
    }

    @Override
    public void accountDeposit(AccountDepositServiceDTO accountDepositServiceDTO) {
        String url = SSAFY_BANK_API_SERVER + "edu/account/receivedTransferAccountNumber";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AccountDepositServiceDTO> request = new HttpEntity<>(accountDepositServiceDTO, headers);
        try {
            restTemplate.postForObject(url, request, AccountDepositWithdrawalResponseServiceDTO.class);
        } catch (RestClientException e) {
            throw new UserApiKeyException("계좌가 존재 하지 않습니다", e);
        }
    }

    @Override
    public void accountWithdrawal(DrawingTransferRequestServiceDTO drawingTransferRequestServiceDTO) {
        String url = SSAFY_BANK_API_SERVER + "edu/account/drawingTransfer";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DrawingTransferRequestServiceDTO> request = new HttpEntity<>(drawingTransferRequestServiceDTO, headers);
        try {
            restTemplate.postForObject(url, request, AccountDepositWithdrawalResponseServiceDTO.class);
        } catch (RestClientException e) {
            throw new UserApiKeyException("계좌가 존재 하지 않습니다", e);
        }

    }
}
