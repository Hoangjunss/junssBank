package com.hoangjunss.junsBank.service.impl;


import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.dto.user.UserDTO;
import com.hoangjunss.junsBank.entity.user.User;
import com.hoangjunss.junsBank.mapper.UserMapper;
import com.hoangjunss.junsBank.repository.UserRepository;
import com.hoangjunss.junsBank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;



    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        User user = userMapper.toEntity(userCreateDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

   /* @Override
    public UserDTO updateUser(UserUpdateDTO userUpdateDTO) {
        User user = userMapper.convertUpdateToEntity(userUpdateDTO);
        User updatedUser = userRepository.save(user);
        return userMapper.toUserDTO(updatedUser);
    }*/

    @Override
    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}