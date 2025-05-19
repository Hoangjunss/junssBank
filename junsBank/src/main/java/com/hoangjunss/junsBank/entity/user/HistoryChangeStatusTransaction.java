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
@Table(name = "history_change_status_transaction")
public class HistoryChangeStatusTransaction {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Transaction transaction;

    @Column(name = "old_status")
    private boolean oldStatus;

    @Column(name = "new_status", nullable = false)
    private boolean newStatus;

    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;
}
