package com.hoangjunss.junsBank.service.User;

import com.hoangjunss.junsBank.entity.user.User;
import com.hoangjunss.junsBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        user.setId(getGenerationId());
        return userRepository.save(user);
    }

    @Override
    public User findByIdentificationNumber(String indentificationNumber) {
        return userRepository.findByIdentificationNumber(indentificationNumber).orElseThrow();
    }

    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
