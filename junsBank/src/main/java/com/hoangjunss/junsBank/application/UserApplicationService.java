package com.hoangjunss.junsBank.application;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoangjunss.junsBank.config.JwtTokenUtil;
import com.hoangjunss.junsBank.contants.RedisContants;
import com.hoangjunss.junsBank.dto.user.AccountDTO;
import com.hoangjunss.junsBank.dto.user.AuthenticationDTO;
import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.dto.user.UserDTO;
import com.hoangjunss.junsBank.entity.user.Account;
import com.hoangjunss.junsBank.entity.user.User;
import com.hoangjunss.junsBank.mapper.AccountMapper;
import com.hoangjunss.junsBank.mapper.UserMapper;
import com.hoangjunss.junsBank.producer.UserProducer;
import com.hoangjunss.junsBank.service.Redis.RedisService;
import com.hoangjunss.junsBank.service.User.AccountService;
import com.hoangjunss.junsBank.service.User.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserApplicationService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @Autowired
    private UserProducer userProducer;
    public void createUser(UserCreateDTO userCreateDTO) {
        // Chuyển đổi từ DTO sang Entity
        User user = userMapper.createToEntity(userCreateDTO);

        // Lưu vào cơ sở dữ liệu qua UserService
      userService.createUser(user);



    }
    public  void verificationChangePassword( String oldPassword,String newPassword){

    }
    @Transactional
    public UserDTO verification(String email,String verifi){
           User user=userService.verifiUser(email,verifi);
        userProducer.sendCreateUserEvent(user.getIdentificationNumber());

        return userMapper.toDTO(user);
    }
    @Transactional
    public AuthenticationDTO login (AccountDTO accountDTO){
        Account account=accountMapper.createToEntity(accountDTO);
        Account accountLogin=accountService.login(account);
        UserDetails userDetails=accountLogin.getUser();

        String jwtToken=jwtTokenUtil.generateToken(userDetails);
        Date expirationDate = jwtTokenUtil.getExpirationDate(jwtToken);
        String expirationDateString = expirationDate.toString(); // Chuyển thành chuỗi

        String refreshToken=jwtTokenUtil.generateRefreshToken(userDetails);
        String tokenKey = RedisContants.WHITE_LIST_PASSWORD + jwtToken;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Chuyển `User` thành JSON
            String userJson = objectMapper.writeValueAsString(userDetails);
            long ttl = (expirationDate.getTime() - System.currentTimeMillis()) / 1000; // Tính TTL theo giây
            // Lưu JSON `User` vào Redis với TTL
            redisService.set(tokenKey, userJson);
            redisService.setTimeToLive(tokenKey, ttl); // Đặt TTL phù hợp với thời gian sống của token
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
            throw new RuntimeException("Failed to save user with token in Redis");
        }
        return AuthenticationDTO.builder().token(jwtToken).refreshToken(refreshToken).build();
    }
    @Transactional
    public void changePassword(String oldPassword, String newPassword){
        accountService.changePassword(oldPassword, newPassword);
    }
    @Transactional
    public void verificationChangePassword(String verificationCode){
        accountService.verifyPasswordChange(verificationCode);
    }

}