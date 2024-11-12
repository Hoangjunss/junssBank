package com.hoangjunss.junsBank.service.User;

import com.hoangjunss.junsBank.entity.user.HistoryChangePasswordAccount;
import com.hoangjunss.junsBank.entity.user.User;
import com.hoangjunss.junsBank.repository.HistoryChangePasswordAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.UUID;

public class HistoryChangePasswordAccountServiceImpl implements HistoryChangePasswordAccountService{
    @Autowired
    private HistoryChangePasswordAccountRepository historyChangePasswordAccountRepository;
    @Autowired
    private UserService userService;
    @Override
    public void save(String indentificationNumber, String oldPassword, String newPassword) {
        User user=userService.findByIdentificationNumber(indentificationNumber);
        HistoryChangePasswordAccount historyChangePasswordAccount=HistoryChangePasswordAccount.builder()
                .changeDate(LocalDate.now())
                .newPassword(newPassword)
                .oldPassword(oldPassword)
                .user(user)
                .id(getGenerationId())
                .build();
        historyChangePasswordAccountRepository.save(historyChangePasswordAccount);
    }
    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
