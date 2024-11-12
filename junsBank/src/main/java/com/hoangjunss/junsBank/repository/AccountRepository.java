package com.hoangjunss.junsBank.repository;

import com.hoangjunss.junsBank.entity.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Integer> {
Optional<Account> findByUserCode(String usercode);
}
