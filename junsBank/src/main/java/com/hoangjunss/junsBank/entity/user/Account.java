package com.hoangjunss.junsBank.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    private Integer id;

    @Column(name = "user_code", nullable = false, unique = true)
    private String userCode; // Mã người dùng, duy nhất cho mỗi tài khoản

    @Column(name = "password", nullable = false)
    private String password; // Mật khẩu của tài khoản
    @OneToOne
    @JoinColumn
    private User user;
}
