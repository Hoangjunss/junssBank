package com.hoangjunss.junsBank.produce;

import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    private static final String TOPIC_CREATE_USER = "create-user";
    private static final String TOPIC_CREATE_ACCOUNT_AND_TRANSACTION = "create-account-and-transaction";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendCreateUserEvent(UserCreateDTO userCreateDTO) {
        kafkaTemplate.send(TOPIC_CREATE_USER, userCreateDTO);
    }

    public void sendCreateAccountAndTransactionEvent(String accountTransactionJson) {
        kafkaTemplate.send(TOPIC_CREATE_ACCOUNT_AND_TRANSACTION, accountTransactionJson);
    }
}
