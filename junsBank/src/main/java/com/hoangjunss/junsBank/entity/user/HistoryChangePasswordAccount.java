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
@Table(name = "history_change_password_account")
public class HistoryChangePasswordAccount {
    @Id
    private Integer id;
    @Column(name = "old_paswword")
    private String oldPassword;
    @Column(name = "new_password", nullable = false)
    private String newPassword;
    @Column(name = "change_date", nullable = false)
    private LocalDate changeDate;
    @ManyToOne
    @JoinColumn
    private User user;

}
