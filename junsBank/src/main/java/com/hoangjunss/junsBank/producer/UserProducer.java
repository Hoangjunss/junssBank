package com.hoangjunss.junsBank.producer;

import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    private static final String TOPIC_CREATE_USER = "create-user";
    private static final String TOPIC_USER_CHANGE = "change-user";


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendCreateUserEvent(String email) {
        kafkaTemplate.send(TOPIC_CREATE_USER, email);
    }
    // Gửi sự kiện thay đổi mật khẩu chung cho cả cập nhật tài khoản và lịch sử
    public void sendPasswordChangeEvent(String identificationNumber) {
        kafkaTemplate.send(TOPIC_USER_CHANGE, identificationNumber);
    }

}
