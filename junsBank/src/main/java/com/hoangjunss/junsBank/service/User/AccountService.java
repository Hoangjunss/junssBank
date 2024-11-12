package com.hoangjunss.junsBank.service.User;

import com.hoangjunss.junsBank.entity.user.Account;

public interface AccountService {
    void createAccount(String identificationNumber);
    Account login(Account account);
    void changePassword(String oldPassword, String newPassword);
    void verifyPasswordChange( String verificationCode);
}
