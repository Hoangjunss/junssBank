package com.hoangjunss.junsBank.repository;

import com.hoangjunss.junsBank.entity.user.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
