package com.hoangjunss.junsBank.service.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoangjunss.junsBank.contants.RedisContants;
import com.hoangjunss.junsBank.dto.EmailVerificationEvent;
import com.hoangjunss.junsBank.entity.user.User;
import com.hoangjunss.junsBank.producer.MailProducer;
import com.hoangjunss.junsBank.producer.UserProducer;
import com.hoangjunss.junsBank.repository.UserRepository;
import com.hoangjunss.junsBank.service.Redis.RedisService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MailService mailService;
    @Autowired
    private MailProducer mailProducer;
    @Autowired
    private UserProducer userProducer;
    @Override
    @Transactional
    public void createUser(User user) {
        // Check for duplicate phone number in Redis
        log.warn("Duplicate phone number detected in Redis for phone: {}", user.getPhoneNumber());
        if (redisService.exists(RedisContants.PHONE_SET_KEY + ":" + user.getPhoneNumber())) {
            log.warn("Phone number already exists in Redis for phone: {}", user.getPhoneNumber());
            throw new IllegalArgumentException("Phone number already exists!");
        }

        // Check for duplicate identification number in Redis
        if (redisService.exists(RedisContants.IDENTIFICATION_NUMBER_SET_KEY + ":" + user.getIdentificationNumber())) {
            log.warn("Identification number already exists in Redis for ID: {}", user.getIdentificationNumber());
            throw new IllegalArgumentException("Identification number already exists!");
        }

        // Check for duplicate in the database as a backup
        if (userRepository.existsByPhone(user.getPhoneNumber())) {
            log.warn("Phone number already exists in the database for phone: {}", user.getPhoneNumber());
            throw new IllegalArgumentException("Phone number already exists in the database!");
        }
        if (userRepository.existsByIdentificationNumber(user.getIdentificationNumber())) {
            log.warn("Identification number already exists in the database for ID: {}", user.getIdentificationNumber());
            throw new IllegalArgumentException("Identification number already exists in the database!");
        }



        user.setId(getGenerationId());
        String verificationCode = UUID.randomUUID().toString();

        try {
            // Save phone number and identification number in Redis for future checks
            redisService.set(RedisContants.VERIFICATION_CODE_KEY + ":" + user.getEmail(), verificationCode);
            redisService.setTimeToLive(RedisContants.VERIFICATION_CODE_KEY + ":" + user.getEmail(), 10); // TTL là 10 phút

            redisService.set(RedisContants.VERIFICATION_PASSWORD+ ":" + user.getIdentificationNumber(), user.getPassword());
            redisService.setTimeToLive(RedisContants.VERIFICATION_PASSWORD + ":" +user.getIdentificationNumber(), 10); // TTL là 10 phút


            ObjectMapper objectMapper = new ObjectMapper();
            String userJson = objectMapper.writeValueAsString(user);
            redisService.set(RedisContants.USER_VERIFI_KEY + ":" + user.getIdentificationNumber(), userJson);
            redisService.setTimeToLive(RedisContants.USER_VERIFI_KEY + ":" + user.getIdentificationNumber(), 10);
            mailProducer.sendVerificationEmailEvent(user.getEmail(), verificationCode);
        } catch (Exception e) {
            // Rollback the transaction if Redis saving fails
            log.error("Error occurred while saving to Redis. Rolling back transaction for user ID: {}. Error: {}", user.getId(), e.getMessage());
            throw new RuntimeException("Transaction rolled back due to Redis error: " + e.getMessage());
        }


    }
    @Override
    @Transactional
    public User verifiUser(String email,String verificationCode ) {
        String storedCode = (String) redisService.get(RedisContants.VERIFICATION_CODE_KEY + ":" + email);
        User user;
        if (storedCode != null && storedCode.equals(verificationCode)) {
            // Lấy thông tin người dùng từ Redis
            String userJson = (String) redisService.get(RedisContants.USER_VERIFI_KEY + ":" + email);
            if (userJson == null) {
                throw new IllegalArgumentException("User information not found in Redis");
            }

            // Chuyển đổi JSON thành `User` entity và lưu vào cơ sở dữ liệu
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                user = objectMapper.readValue(userJson, User.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to convert JSON to User", e);
            }
        }  else {
        throw new IllegalArgumentException("Invalid verification code");
    }
        // Check for duplicate phone number in Redis

        if (redisService.exists(RedisContants.PHONE_SET_KEY + ":" + user.getPhoneNumber())) {
            log.warn("Phone number already exists in Redis for phone: {}", user.getPhoneNumber());
            throw new IllegalArgumentException("Phone number already exists!");
        }

        // Check for duplicate identification number in Redis
        if (redisService.exists(RedisContants.IDENTIFICATION_NUMBER_SET_KEY + ":" + user.getIdentificationNumber())) {
            log.warn("Identification number already exists in Redis for ID: {}", user.getIdentificationNumber());
            throw new IllegalArgumentException("Identification number already exists!");
        }

        // Check for duplicate in the database as a backup
        if (userRepository.existsByPhone(user.getPhoneNumber())) {
            log.warn("Phone number already exists in the database for phone: {}", user.getPhoneNumber());
            throw new IllegalArgumentException("Phone number already exists in the database!");
        }
        if (userRepository.existsByIdentificationNumber(user.getIdentificationNumber())) {
            log.warn("Identification number already exists in the database for ID: {}", user.getIdentificationNumber());
            throw new IllegalArgumentException("Identification number already exists in the database!");
        }

        // Generate ID and save the user to the database

        User savedUser = userRepository.save(user);
        log.info("User saved successfully in the database with ID: {}", savedUser.getId());

        try {
            // Save phone number and identification number in Redis for future checks
            redisService.delete(RedisContants.VERIFICATION_CODE_KEY + ":" + email);


            redisService.set(RedisContants.PHONE_SET_KEY + ":" + savedUser.getPhoneNumber(), "true");
            redisService.set(RedisContants.IDENTIFICATION_NUMBER_SET_KEY + ":" + savedUser.getIdentificationNumber(), "true");
            ObjectMapper objectMapper = new ObjectMapper();
            String userJson = objectMapper.writeValueAsString(savedUser);
            redisService.set(RedisContants.USER_KEY + ":" + savedUser.getIdentificationNumber(), userJson);
            log.info("User phone and ID saved to Redis successfully for user ID: {}", savedUser.getId());
        } catch (Exception e) {
            // Rollback the transaction if Redis saving fails
            log.error("Error occurred while saving to Redis. Rolling back transaction for user ID: {}. Error: {}", savedUser.getId(), e.getMessage());
            throw new RuntimeException("Transaction rolled back due to Redis error: " + e.getMessage());
        }

        return savedUser;
    }



    @Override
    public User findByIdentificationNumber(String indentificationNumber) {
        try {
            // Kiểm tra Redis trước
            String userJson = (String) redisService.get(RedisContants.USER_KEY + ":" + indentificationNumber);
            if (userJson != null) {
                // Chuyển JSON thành `User` object
                ObjectMapper objectMapper = new ObjectMapper();
                User user = objectMapper.readValue(userJson, User.class);
                log.info("User found in Redis with identification number: {}", indentificationNumber);
                return user;
            }

            // Nếu không có trong Redis, kiểm tra trong cơ sở dữ liệu
            Optional<User> userOptional = userRepository.findByIdentificationNumber(indentificationNumber);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                log.info("User found in the database with identification number: {}", indentificationNumber);

                // Lưu user vào Redis cho các lần truy xuất sau
                ObjectMapper objectMapper = new ObjectMapper();
                String newUserJson = objectMapper.writeValueAsString(user);
                redisService.set(RedisContants.USER_KEY + ":" + indentificationNumber, newUserJson);
                return user;
            } else {
                log.warn("User not found with identification number: {}", indentificationNumber);
                throw new NoSuchElementException("User not found with identification number: " + indentificationNumber);
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching user with identification number: {}. Error: {}", indentificationNumber, e.getMessage());
            throw new RuntimeException("Error retrieving user with identification number: " + indentificationNumber, e);
        }
    }

    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
