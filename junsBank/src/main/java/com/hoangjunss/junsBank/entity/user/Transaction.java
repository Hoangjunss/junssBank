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
    private String transactionId; // Mã giao dịch, duy nhất cho mỗi giao dịch

    @Column(name = "from_account")
    private String fromAccount; // Tài khoản gửi (có thể null nếu là nạp tiền)

    @Column(name = "to_account")
    private String toAccount; // Tài khoản nhận (có thể null nếu là rút tiền)

    @Column(name = "amount", nullable = false)
    private double amount; // Số tiền giao dịch


    @Column(name = "transaction_type", nullable = false)
    private String transactionType; // Loại giao dịch (CHUYỂN TIỀN, NẠP TIỀN, RÚT TIỀN)

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private String status; // Trạng thái giao dịch (PENDING, COMPLETED, FAILED)

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate; // Ngày giờ giao dịch'
}
