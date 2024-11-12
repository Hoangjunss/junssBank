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
        private String accountNumber; // Mã số tài khoản giao dịch, duy nhất cho mỗi tài khoản

        @Column(name = "balance", nullable = false)
        private double balance; // Số dư hiện tại của tài khoản


        @Column(name = "status", nullable = false)
        private boolean status; // Trạng thái tài khoản (ACTIVE, SUSPENDED, CLOSED)

        @Column(name = "type", nullable = false)
        private String accountType; // Loại tài khoản (séc, tiết kiệm, v.v.)
        @Column(name="create_date",nullable = false)
        private LocalDate createDate;
    @OneToOne
    @JoinColumn
    private User user;
    }
