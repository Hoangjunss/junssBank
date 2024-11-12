package com.hoangjunss.junsBank.producer;

import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    private static final String TOPIC_CREATE_USER = "create-user";


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendCreateUserEvent(String email) {
        kafkaTemplate.send(TOPIC_CREATE_USER, email);
    }


}
