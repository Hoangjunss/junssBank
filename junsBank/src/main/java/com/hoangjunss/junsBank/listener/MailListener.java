package com.hoangjunss.junsBank.listener;

import com.hoangjunss.junsBank.dto.EmailVerificationEvent;
import com.hoangjunss.junsBank.service.User.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailListener {

    @Autowired
    private MailService mailService;

    @KafkaListener(topics = "email-verification-topic", groupId = "email-group")
    public void sendVerificationEmail(EmailVerificationEvent event) {
        // Gửi email xác thực qua MailService
        mailService.sendVerificationCode(event.getEmail(), event.getVerificationCode());
        log.info("Verification email sent to: " + event.getEmail());
    }
}
