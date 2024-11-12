package com.hoangjunss.junsBank.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoangjunss.junsBank.dto.user.PasswordChangeDTO;
import com.hoangjunss.junsBank.service.User.HistoryChangePasswordAccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class HistoryChangePasswordAccountEventListener {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private HistoryChangePasswordAccountService historyChangePasswordAccountService;
    @KafkaListener(topics = "change-user", groupId = "my-group")
    @Transactional
    public void consumeCreateUserEvent(String Json) {

        try {
            // Chuyển đổi JSON thành đối tượng PasswordChangeDTO
            PasswordChangeDTO passwordChangeDTO = objectMapper.readValue(Json, PasswordChangeDTO.class);

            // Lấy identificationNumber từ đối tượng PasswordChangeDTO
         historyChangePasswordAccountService.save(passwordChangeDTO.getIdentificationNumber(), passwordChangeDTO.getOldPassword(), passwordChangeDTO.getNewPassword());

            // Bạn có thể thêm logic khác nếu cần, ví dụ: cập nhật lịch sử, gửi thông báo, v.v.

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to process password change JSON", e);
        }
    }
}
