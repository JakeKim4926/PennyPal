package com.ssafy.pennypal.bank.service.api;

import com.ssafy.pennypal.bank.dto.service.request.UserAccountRequestServiceDTO;
import com.ssafy.pennypal.bank.dto.service.response.UserAccountResponseServiceDTO;

public interface IBankServiceAPI {
    UserAccountResponseServiceDTO createUserAccount(UserAccountRequestServiceDTO userAccountRequestServiceDTO);

    UserAccountResponseServiceDTO getUserAccount(UserAccountRequestServiceDTO userAccountRequestServiceDTO);
}
