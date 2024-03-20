package com.ssafy.pennypal.bank.exception;

import com.ssafy.pennypal.bank.controller.BankController;
import com.ssafy.pennypal.bank.exception.model.UserApiKeyException;
import com.ssafy.pennypal.global.common.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = BankController.class)
public class BankExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserApiKeyException.class)
    public ApiResponse<Object> bindException(UserApiKeyException e) {
        return ApiResponse.of(
                HttpStatus.BAD_REQUEST,
                e.getMessage(), null
        );
    }

}
