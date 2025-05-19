package com.hoangjunss.junsBank.service;


import com.hoangjunss.junsBank.dto.historyChangeAccountPassword.HistoryChangePasswordAccountCreateDTO;
import com.hoangjunss.junsBank.dto.historyChangeAccountPassword.HistoryChangePasswordAccountDTO;
import com.hoangjunss.junsBank.dto.historyChangeAccountPassword.HistoryChangePasswordAccountUpdateDTO;

import java.util.List;

public interface HistoryChangePasswordAccountService {
    HistoryChangePasswordAccountDTO createHistory(HistoryChangePasswordAccountCreateDTO dto);
    HistoryChangePasswordAccountDTO updateHistory(HistoryChangePasswordAccountUpdateDTO dto);
    HistoryChangePasswordAccountDTO getHistoryById(Integer id);
    List<HistoryChangePasswordAccountDTO> getAllHistories();
    void deleteHistory(Integer id);
}