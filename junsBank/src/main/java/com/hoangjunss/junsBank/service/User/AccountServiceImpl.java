package com.hoangjunss.junsBank.service.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoangjunss.junsBank.contants.RedisContants;
import com.hoangjunss.junsBank.dto.user.PasswordChangeDTO;
import com.hoangjunss.junsBank.entity.user.Account;
import com.hoangjunss.junsBank.entity.user.User;
import com.hoangjunss.junsBank.producer.UserProducer;
import com.hoangjunss.junsBank.repository.AccountRepository;
import com.hoangjunss.junsBank.service.Redis.RedisService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
@Slf4j
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserProducer userProducer;
    @Override
    public void createAccount(String identificationNumber) {
        User user=userService.findByIdentificationNumber(identificationNumber);
        String password=(String)redisService.get(RedisContants.VERIFICATION_PASSWORD+ ":" + user.getIdentificationNumber());
        Account account=Account.builder()
                .id(getGenerationId())
                .userCode(user.getPhoneNumber())
                .user(user).password(passwordEncoder.encode(password))
                .build();
        accountRepository.save(account);
        try {
            // Chuyển `Account` thành JSON và lưu vào Redis với userCode làm key
            ObjectMapper objectMapper = new ObjectMapper();
            String accountJson = objectMapper.writeValueAsString(account);
            redisService.set(RedisContants.ACCOUNT_KEY + ":" + account.getUserCode(), accountJson);
            log.info("Account saved to Redis for login purposes with userCode: {}", account.getUserCode());
        } catch (Exception e) {
            log.error("Failed to save account to Redis for userCode: {}. Error: {}", account.getUserCode(), e.getMessage());
        }
    }
    public Account login(Account accountRequest) {
        try {
            // Kiểm tra Redis trước
            String accountJson = (String) redisService.get(RedisContants.ACCOUNT_KEY + ":" + accountRequest.getUserCode());
            if (accountJson != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                Account account = objectMapper.readValue(accountJson, Account.class);

                // Kiểm tra mật khẩu
                if (passwordEncoder.matches(accountRequest.getPassword(), account.getPassword())) {
                    log.info("Login successful for userCode: {} (Redis)", accountRequest.getUserCode());
                    return account;
                } else {
                    log.warn("Invalid password for userCode: {}", accountRequest.getUserCode());
                    throw new IllegalArgumentException("Invalid password");
                }
            }

            // Kiểm tra trong cơ sở dữ liệu nếu không có trong Redis
            Optional<Account> accountOptional = accountRepository.findByUserCode(accountRequest.getUserCode());
            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();

                // Kiểm tra mật khẩu
                if (passwordEncoder.matches(accountRequest.getPassword(), account.getPassword())) {
                    log.info("Login successful for userCode: {} (Database)", accountRequest.getUserCode());

                    // Lưu lại vào Redis để tăng tốc cho lần truy cập sau
                    String newAccountJson = new ObjectMapper().writeValueAsString(account);
                    redisService.set(RedisContants.ACCOUNT_KEY + ":" + accountRequest.getUserCode(), newAccountJson);
                    return account;
                } else {
                    log.warn("Invalid password for userCode: {}", accountRequest.getUserCode());
                    throw new IllegalArgumentException("Invalid password");
                }
            } else {
                log.warn("Account not found for userCode: {}", accountRequest.getUserCode());
                throw new NoSuchElementException("Account not found");
            }
        } catch (Exception e) {
            log.error("Error occurred while logging in with userCode: {}. Error: {}", accountRequest.getUserCode(), e.getMessage());
            throw new RuntimeException("Error during login", e);
        }
    }
    @Transactional
    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // Lấy thông tin người dùng từ Security Context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        // Kiểm tra mật khẩu cũ
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect.");
        }

        // Tạo mã xác thực và lưu vào Redis với TTL (ví dụ: 10 phút)
        String verificationCode = UUID.randomUUID().toString();

        // Gửi email xác thực với mã xác thực
        mailService.sendVerificationCode(user.getEmail(), verificationCode);
        ObjectMapper objectMapper = new ObjectMapper();

        // Lưu mật khẩu mới vào Redis để cập nhật sau khi xác thực qua email
        try {
            redisService.set(RedisContants.VERIFICATION_CHANGE_PASSWORD+user.getIdentificationNumber(), verificationCode);
            redisService.setTimeToLive(RedisContants.VERIFICATION_CHANGE_PASSWORD+user.getIdentificationNumber(), 10L);
            redisService.set(RedisContants.VERIFICATION_CHANGE_PASSWORD+verificationCode, newPassword);
            redisService.setTimeToLive(RedisContants.VERIFICATION_CHANGE_PASSWORD+verificationCode, 10L);// Đặt TTL phù hợp với thời gian sống của token
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
            throw new RuntimeException("Failed to save user with token in Redis");
        }
    }
    @Transactional
    @Override
    public void verifyPasswordChange( String verificationCode) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        try {
            // Lấy mã xác thực từ Redis
            String storedCode = (String) redisService.get(RedisContants.VERIFICATION_CHANGE_PASSWORD + user.getIdentificationNumber());
            if (storedCode == null || !storedCode.equals(verificationCode)) {
                throw new IllegalArgumentException("Invalid or expired verification code.");
            }

            // Lấy mật khẩu mới từ Redis
            String newPassword = (String) redisService.get(RedisContants.VERIFICATION_CHANGE_PASSWORD + verificationCode);
            if (newPassword == null) {
                throw new IllegalArgumentException("New password not found. Please request a password change again.");
            }
            Account account=accountRepository.findByUserCode(user.getPhoneNumber()).orElseThrow();
            String oldPassword= account.getPassword();
            account.setPassword(passwordEncoder.encode(newPassword));
            ObjectMapper objectMapper = new ObjectMapper();
            PasswordChangeDTO passwordChangeDTO= PasswordChangeDTO.builder()
                    .newPassword(newPassword)
                    .oldPassword(oldPassword)
                    .identificationNumber(user.getIdentificationNumber())
                    .build();
            String passwordChange=objectMapper.writeValueAsString(passwordChangeDTO);
            userProducer.sendPasswordChangeEvent(passwordChange);

            // Xóa mã xác thực và mật khẩu mới khỏi Redis sau khi hoàn tất
            redisService.delete(RedisContants.VERIFICATION_CHANGE_PASSWORD + user.getIdentificationNumber());
            redisService.delete(RedisContants.VERIFICATION_CHANGE_PASSWORD + verificationCode);

        } catch (Exception e) {
            // Xử lý lỗi
            e.printStackTrace();
            throw new RuntimeException("Failed to verify password change.");
        }
    }

    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
