package com.hoangjunss.junsBank.application;


import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.entity.user.User;
import com.hoangjunss.junsBank.mapper.UserMapper;
import com.hoangjunss.junsBank.produce.UserProducer;
import com.hoangjunss.junsBank.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

    @Autowired
    private UserMapper userMapper; // Mapper để chuyển từ DTO sang Entity

    @Autowired
    private UserService userService; // Service để lưu vào cơ sở dữ liệu

    @Autowired
    private UserProducer userProducer; // Producer để gửi sự kiện Kafka

    public void createUser(UserCreateDTO userCreateDTO) {
        // Chuyển đổi từ DTO sang Entity
        User user = userMapper.createToEntity(userCreateDTO);

        // Lưu vào cơ sở dữ liệu qua UserService
        userService.saveUser(user);

        // Gửi sự kiện đến Kafka
        userProducer.sendCreateUserEvent(userCreateDTO);
    }
}