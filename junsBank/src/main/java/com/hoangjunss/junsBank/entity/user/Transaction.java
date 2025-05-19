package com.hoangjunss.junsBank.entity.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    private Integer id;

    @Column(name = "transaction_id", nullable = false, unique = true)
    private String transactionId;

    @Column(name = "from_account")
    private String fromAccount;
    @Column(name = "to_account")
    private String toAccount;

    @Column(name = "amount", nullable = false)
    private double amount;


    @Column(name = "transaction_type", nullable = false)
    private String transactionType;


    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

}
