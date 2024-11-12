package com.hoangjunss.junsBank.service.User;

import com.hoangjunss.junsBank.entity.user.User;

public interface UserService {
    User saveUser(User user);
    User findByIdentificationNumber(String indentificationNumber);
}
