package com.hoangjunss.junsBank.service.User;

import com.hoangjunss.junsBank.entity.user.HistoryChangePasswordAccount;

public interface HistoryChangePasswordAccountService {
    void save(String indentificationNumber,String oldPassword,String newPassword);
}
