package com.hoangjunss.junsBank.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventListener {

    @KafkaListener(topics = "create-user", groupId = "my-group")
    public void consumeCreateUserEvent(String message) {
        System.out.println("Received message: " + message);
        // Thực hiện các xử lý cần thiết với message
    }
}
