package com.ssafy.pennypal.bank.service.db;

import com.ssafy.pennypal.bank.dto.service.request.UserApiKeyRequestDTO;

public interface IBankServiceDB {
    void InsertUserKey(UserApiKeyRequestDTO userApiKeyRequestDTO);
}
