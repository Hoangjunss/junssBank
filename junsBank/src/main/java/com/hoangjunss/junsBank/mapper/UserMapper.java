package com.hoangjunss.junsBank.mapper;

import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.entity.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper {
    @Autowired
    private ModelMapper mapper;
    public User createToEntity(UserCreateDTO userCreateDTO){
        User user=mapper.map(userCreateDTO,User.class);
        return user;
    }
}
