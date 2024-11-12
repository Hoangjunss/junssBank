package com.hoangjunss.junsBank.producer;

import com.hoangjunss.junsBank.dto.EmailVerificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MailProducer {

    private static final String TOPIC_SEND_VERIFICATION_EMAIL = "send-verification-email";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendVerificationEmailEvent(String email, String verificationCode) {
        // Tạo một đối tượng chứa thông tin cần thiết để gửi email xác thực
        EmailVerificationEvent emailEvent = new EmailVerificationEvent(email, verificationCode);

        // Gửi sự kiện vào Kafka
        kafkaTemplate.send(TOPIC_SEND_VERIFICATION_EMAIL, emailEvent);
    }
}