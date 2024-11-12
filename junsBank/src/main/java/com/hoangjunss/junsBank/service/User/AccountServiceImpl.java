package com.hoangjunss.junsBank.service.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoangjunss.junsBank.contants.RedisContants;
import com.hoangjunss.junsBank.entity.user.Account;
import com.hoangjunss.junsBank.entity.user.User;
import com.hoangjunss.junsBank.repository.AccountRepository;
import com.hoangjunss.junsBank.service.Redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
