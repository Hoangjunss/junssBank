package com.hoangjunss.junsBank.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_transactions")
    public class AccountTransaction {
        @Id
        private Integer id;

        @Column(name = "account_number", nullable = false, unique = true)
        private String accountNumber;

        @Column(name = "balance", nullable = false)
        private double balance;


        @Column(name = "status", nullable = false)
        private boolean status;

        @Column(name = "type", nullable = false)
        private String accountType;

        @Column(name="create_date",nullable = false)
        private LocalDate createDate;

        @OneToOne
        @JoinColumn
        private User user;
    }
