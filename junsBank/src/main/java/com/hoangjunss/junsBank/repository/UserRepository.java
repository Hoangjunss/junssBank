package com.hoangjunss.junsBank.repository;

import com.hoangjunss.junsBank.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByIdentificationNumber(String identificationNumber);
    Optional<User> findByPhoneNumber(String phone);
    Boolean existsByPhone(String phone);
    Boolean existsByIdentificationNumber(String identificationNumber);
}
