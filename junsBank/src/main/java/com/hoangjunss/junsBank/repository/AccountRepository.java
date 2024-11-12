package com.hoangjunss.junsBank.repository;

import com.hoangjunss.junsBank.entity.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {

}
