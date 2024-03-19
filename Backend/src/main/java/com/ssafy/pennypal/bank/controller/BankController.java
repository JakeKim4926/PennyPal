package com.ssafy.pennypal.bank.controller;

import com.ssafy.pennypal.bank.dto.controller.response.UserAccountResponseControllerDTO;
import com.ssafy.pennypal.bank.dto.service.request.UserAccountRequestServiceDTO;
import com.ssafy.pennypal.bank.dto.service.request.UserApiKeyRequestDTO;
import com.ssafy.pennypal.bank.service.api.IBankServiceAPI;
import com.ssafy.pennypal.bank.service.db.IBankServiceDB;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("bank/api")
@RequiredArgsConstructor
public class BankController {
    private static final String SSAFY_BANK_API_KEY = System.getenv("SSAFY_BANK_API_KEY");

    private final IBankServiceAPI bankServiceAPI;
    private final IBankServiceDB bankServiceDB;

    @PostMapping("/user/api/key/{userEmail}")
    public ApiResponse<UserAccountResponseControllerDTO> createUserApiKey(@PathVariable String userEmail) {
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
        return ApiResponse.ok(userAccountResponseControllerDTO);
    }

    @GetMapping("/user/api/key/{userEmail}")
    public ApiResponse<UserAccountResponseControllerDTO> getUserApiKey(@PathVariable String userEmail) {
        UserAccountRequestServiceDTO userAccountRequestServiceDTO = UserAccountRequestServiceDTO.builder()
                .apiKey(SSAFY_BANK_API_KEY)
                .userEmail(userEmail)
                .build();

        UserAccountResponseControllerDTO userAccountResponseControllerDTO = UserAccountResponseControllerDTO.of(
                bankServiceAPI.getUserAccount(userAccountRequestServiceDTO)
        );

        return ApiResponse.ok(userAccountResponseControllerDTO);
    }

    @PostMapping("/user/account/{userId}")
    public void getAccountList() {

    }
}
