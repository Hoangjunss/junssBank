package com.hoangjunss.junsBank.controller;

import com.hoangjunss.junsBank.application.UserApplicationService;
import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.dto.user.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserApplicationService userApplicationService;
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registration(@RequestBody UserCreateDTO createUserRequest,
                                                HttpServletRequest request) {
        log.info("User registration request: {}", createUserRequest.toString());

        UserDTO registeredUser = userApplicationService.createUser(createUserRequest);


        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}
