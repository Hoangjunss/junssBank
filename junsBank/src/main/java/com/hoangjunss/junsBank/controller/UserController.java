package com.hoangjunss.junsBank.controller;

import com.hoangjunss.junsBank.application.UserApplicationService;
import com.hoangjunss.junsBank.dto.user.AccountDTO;
import com.hoangjunss.junsBank.dto.user.AuthenticationDTO;
import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.dto.user.UserDTO;
import com.hoangjunss.junsBank.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserApplicationService userApplicationService;
    @PostMapping("/signup")
    public ResponseEntity<String> registration(@RequestBody UserCreateDTO createUserRequest,
                                                HttpServletRequest request) {
        log.info("User registration request: {}", createUserRequest.toString());

        userApplicationService.createUser(createUserRequest);


        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
    @PostMapping("/verifi")
    public ResponseEntity<UserDTO> verifi(@RequestParam String email,
                                         @RequestParam String verifiCode,
                                               HttpServletRequest request) {
        log.info("User verifi request: {}", verifiCode);

        UserDTO user=userApplicationService.verification(email,verifiCode);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationDTO> signIn(
            @RequestBody AccountDTO signInRequest, HttpServletRequest request) {
        AuthenticationDTO authenticationResponse = userApplicationService.login(signInRequest);

        return ResponseEntity.ok(authenticationResponse);
    }

}
