package com.hoangjunss.junsBank.service.User;

import com.hoangjunss.junsBank.entity.user.User;

public interface UserService {
    User verifiUser(String email,String verificationCode);
    void createUser(User user);
    User findByIdentificationNumber(String indentificationNumber);
}
