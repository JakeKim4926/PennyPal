package com.ssafy.pennypal.bank.service.api;

import com.ssafy.pennypal.bank.dto.service.common.CommonHeaderRequestDTO;
import com.ssafy.pennypal.bank.dto.service.request.*;
import com.ssafy.pennypal.bank.dto.service.response.AccountTransactionListResponseServiceDTO;
import com.ssafy.pennypal.bank.dto.service.response.UserAccountResponseServiceDTO;
import com.ssafy.pennypal.bank.dto.service.response.UserBankAccountResponseServiceDTO;
import com.ssafy.pennypal.bank.dto.service.response.UserBankAccountsResponseServiceDTO;

public interface IBankServiceAPI {
    UserAccountResponseServiceDTO createUserAccount(UserAccountRequestServiceDTO userAccountRequestServiceDTO);

    UserAccountResponseServiceDTO getUserAccount(UserAccountRequestServiceDTO userAccountRequestServiceDTO);

    UserBankAccountResponseServiceDTO createUserBankAccount(UserBankAccountRequestServiceDTO userBankAccountRequestServiceDTO);

    UserBankAccountsResponseServiceDTO getUserAccountList(CommonHeaderRequestDTO commonHeaderRequestDTO);

    AccountTransactionListResponseServiceDTO getUserAccountTransaction(AccountTransactionRequestServiceDTO accountTransactionRequestServiceDTO);

    void accountDeposit(AccountDepositServiceDTO accountDepositServiceDTO);

    void accountWithdrawal(DrawingTransferRequestServiceDTO drawingTransferRequestServiceDTO);
}
