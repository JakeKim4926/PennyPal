package com.ssafy.pennypal.bank.controller;

import com.ssafy.pennypal.bank.dto.controller.response.UserAccountResponseControllerDTO;
import com.ssafy.pennypal.bank.dto.service.request.UserAccountRequestServiceDTO;
import com.ssafy.pennypal.bank.service.IBankService;
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

    private final IBankService bankService;

    @PostMapping("/user/api/key/{userId}")
    public ApiResponse<UserAccountResponseControllerDTO> createUserApiKey(@PathVariable String userId) {
        UserAccountRequestServiceDTO userAccountRequestServiceDTO = UserAccountRequestServiceDTO.builder()
                .apiKey(SSAFY_BANK_API_KEY)
                .userId(userId)
                .build();

        UserAccountResponseControllerDTO userAccountResponseControllerDTO = UserAccountResponseControllerDTO.of(
                bankService.createUserAccount(userAccountRequestServiceDTO)
        );

        return ApiResponse.ok(userAccountResponseControllerDTO);
    }

    @GetMapping("/user/api/key/{userId}")
    public ApiResponse<UserAccountResponseControllerDTO> getUserApiKey(@PathVariable String userId) {
        UserAccountRequestServiceDTO userAccountRequestServiceDTO = UserAccountRequestServiceDTO.builder()
                .apiKey(SSAFY_BANK_API_KEY)
                .userId(userId)
                .build();

        UserAccountResponseControllerDTO userAccountResponseControllerDTO = UserAccountResponseControllerDTO.of(
                bankService.getUserAccount(userAccountRequestServiceDTO)
        );

        return ApiResponse.ok(userAccountResponseControllerDTO);
    }

    @GetMapping("/user/account/{userId}")
    public void getAccountList() {

    }
}
