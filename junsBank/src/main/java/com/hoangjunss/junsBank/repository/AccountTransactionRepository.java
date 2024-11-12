package com.hoangjunss.junsBank.repository;

import com.hoangjunss.junsBank.entity.user.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Integer> {
}
