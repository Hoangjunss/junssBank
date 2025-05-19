package com.hoangjunss.junsBank.service;


import com.hoangjunss.junsBank.dto.historyChangeStatusTransaction.HistoryChangeStatusTransactionCreateDTO;
import com.hoangjunss.junsBank.dto.historyChangeStatusTransaction.HistoryChangeStatusTransactionDTO;
import com.hoangjunss.junsBank.dto.historyChangeStatusTransaction.HistoryChangeStatusTransactionUpdateDTO;

import java.util.List;

public interface HistoryChangeStatusTransactionService {
    HistoryChangeStatusTransactionDTO createHistory(HistoryChangeStatusTransactionCreateDTO dto);
    HistoryChangeStatusTransactionDTO updateHistory(HistoryChangeStatusTransactionUpdateDTO dto);
    HistoryChangeStatusTransactionDTO getHistoryById(Integer id);
    List<HistoryChangeStatusTransactionDTO> getAllHistories();
    void deleteHistory(Integer id);
}