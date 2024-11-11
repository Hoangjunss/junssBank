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
@Table(name = "history_change_status_user")
public class HistoryChangeStatusUser {
    @Id
    private Integer id;
    @Column(name = "user_status")
    private boolean status;
    @ManyToOne
    @JoinColumn
    private User user;
    @Column(name = "change_date", nullable = false)
    private LocalDate changeDate;
}
