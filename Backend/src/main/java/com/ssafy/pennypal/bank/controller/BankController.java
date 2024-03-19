package com.ssafy.pennypal.bank.controller;

import com.ssafy.pennypal.bank.dto.controller.request.AccountTransactionRequestDTO;
import com.ssafy.pennypal.bank.dto.controller.response.AccountTransactionResponseDTO;
import com.ssafy.pennypal.bank.dto.controller.response.UserAccountResponseControllerDTO;
import com.ssafy.pennypal.bank.dto.controller.response.UserAccountsResponseControllerDTO;
import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderRequestDTO;
import com.ssafy.pennypal.bank.dto.service.request.AccountTransactionRequestServiceDTO;
import com.ssafy.pennypal.bank.dto.service.request.UserAccountRequestServiceDTO;
import com.ssafy.pennypal.bank.dto.service.request.UserApiKeyRequestDTO;
import com.ssafy.pennypal.bank.dto.service.request.UserBankAccountRequestServiceDTO;
import com.ssafy.pennypal.bank.service.api.IBankServiceAPI;
import com.ssafy.pennypal.bank.service.db.IBankServiceDB;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("bank/api")
@RequiredArgsConstructor
public class BankController {
    private static final String SSAFY_BANK_API_KEY = System.getenv("SSAFY_BANK_API_KEY");

    private final IBankServiceAPI bankServiceAPI;
    private final IBankServiceDB bankServiceDB;

    // 유효성 검사 오류 메시지를 처리해서 ApiResponse 객체를 반환하는 메서드
    private ApiResponse<Object> getValidationErrorResponse(BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                if (error instanceof FieldError fieldError) {
                    errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("; ");
                } else {
                    errorMessage.append(error.getDefaultMessage()).append("; ");
                }
            }
            log.info("Validation errors: {}", errorMessage);
            return ApiResponse.of(HttpStatus.BAD_REQUEST, errorMessage.toString(), null);
        }
        return null;
    }

    @PostMapping("/user/api/key/{userEmail}")
    public ApiResponse<Object> createUserApiKey(@PathVariable String userEmail) {
        UserAccountRequestServiceDTO userAccountRequestServiceDTO = UserAccountRequestServiceDTO.builder()
                .apiKey(SSAFY_BANK_API_KEY)
                .userEmail(userEmail)
                .build();

        UserAccountResponseControllerDTO userAccountResponseControllerDTO = UserAccountResponseControllerDTO.of(
                bankServiceAPI.createUserAccount(userAccountRequestServiceDTO)
        );

        UserApiKeyRequestDTO userApiKeyRequestDTO = UserApiKeyRequestDTO.builder()
                .userEmail(userAccountResponseControllerDTO.getUserEmail())
                .userKey(userAccountResponseControllerDTO.getUserKey())
                .build();

        bankServiceDB.InsertUserKey(userApiKeyRequestDTO);

        CommonHeaderRequestDTO commonHeaderRequestDTO = CommonHeaderRequestDTO.builder()
                .apiName("openAccount")
                .apiKey(SSAFY_BANK_API_KEY)
                .userKey(userAccountResponseControllerDTO.getUserKey())
                .build();

        UserBankAccountRequestServiceDTO userBankAccountRequestServiceDTO = UserBankAccountRequestServiceDTO.builder()
                .header(commonHeaderRequestDTO)
                .accountTypeUniqueNo("001-1-81fe2deafd1943")
                .build();

        bankServiceAPI.createUserBankAccount(userBankAccountRequestServiceDTO);

        return ApiResponse.ok(userAccountResponseControllerDTO);
    }

    @GetMapping("/user/api/key/{userEmail}")
    public ApiResponse<Object> getUserApiKey(@PathVariable String userEmail) {
        UserAccountRequestServiceDTO userAccountRequestServiceDTO = UserAccountRequestServiceDTO.builder()
                .apiKey(SSAFY_BANK_API_KEY)
                .userEmail(userEmail)
                .build();

        UserAccountResponseControllerDTO userAccountResponseControllerDTO = UserAccountResponseControllerDTO.of(
                bankServiceAPI.getUserAccount(userAccountRequestServiceDTO)
        );

        return ApiResponse.ok(userAccountResponseControllerDTO);
    }

    @GetMapping("/user/account/{userEmail}")
    public ApiResponse<Object> getAccountList(@PathVariable String userEmail) {

        UserAccountRequestServiceDTO userAccountRequestServiceDTO = UserAccountRequestServiceDTO.builder()
                .apiKey(SSAFY_BANK_API_KEY)
                .userEmail(userEmail)
                .build();

        UserAccountResponseControllerDTO userAccountResponseControllerDTO = UserAccountResponseControllerDTO.of(
                bankServiceAPI.getUserAccount(userAccountRequestServiceDTO)
        );

        CommonHeaderRequestDTO commonHeaderRequestDTO = CommonHeaderRequestDTO.builder()
                .apiName("inquireAccountList")
                .apiKey(SSAFY_BANK_API_KEY)
                .userKey(userAccountResponseControllerDTO.getUserKey())
                .build();

        UserAccountsResponseControllerDTO userAccountsResponseControllerDTO = UserAccountsResponseControllerDTO.of(
                bankServiceAPI.getUserAccountList(commonHeaderRequestDTO)
        );

        return ApiResponse.ok(userAccountsResponseControllerDTO);
    }

    @GetMapping("/user/account")
    public ApiResponse<Object> getAccountTransaction(@RequestBody @Validated AccountTransactionRequestDTO requestDTO, BindingResult result) {
        ApiResponse<Object> validationResponse = getValidationErrorResponse(result);
        if (validationResponse != null)
            return validationResponse;

        UserAccountRequestServiceDTO userAccountRequestServiceDTO = UserAccountRequestServiceDTO.builder()
                .apiKey(SSAFY_BANK_API_KEY)
                .userEmail(requestDTO.getUserEmail())
                .build();

        UserAccountResponseControllerDTO userAccountResponseControllerDTO = UserAccountResponseControllerDTO.of(
                bankServiceAPI.getUserAccount(userAccountRequestServiceDTO)
        );

        CommonHeaderRequestDTO commonHeaderRequestDTO = CommonHeaderRequestDTO.builder()
                .apiName("inquireAccountTransactionHistory")
                .apiKey(SSAFY_BANK_API_KEY)
                .userKey(userAccountResponseControllerDTO.getUserKey())
                .build();

        AccountTransactionRequestServiceDTO accountTransactionRequestServiceDTO = AccountTransactionRequestServiceDTO.builder()
                .header(commonHeaderRequestDTO)
                .bankCode(requestDTO.getBankCode())
                .accountNo(requestDTO.getAccountNo())
                .startDate(requestDTO.getStartDate())
                .endDate(requestDTO.getEndDate())
                .transactionType(requestDTO.getTransactionType())
                .orderByType("DESC")
                .build();

        AccountTransactionResponseDTO accountTransactionResponseDTO = AccountTransactionResponseDTO.of(
                bankServiceAPI.getUserAccountTransaction(accountTransactionRequestServiceDTO)
        );

        return ApiResponse.ok(accountTransactionResponseDTO);
    }
}
