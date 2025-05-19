package com.hoangjunss.junsBank.service;



import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.dto.user.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    UserDTO createUser(UserCreateDTO userCreateDTO);

   // UserDTO updateUser(UserUpdateDTO userUpdateDTO);

    UserDTO getUserById(Integer id);

    List<UserDTO> getAllUsers();

    void deleteUser(Integer id);
}
