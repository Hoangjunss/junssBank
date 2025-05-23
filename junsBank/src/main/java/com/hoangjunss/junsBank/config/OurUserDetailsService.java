package com.hoangjunss.junsBank.config;


import com.hoangjunss.junsBank.repository.AccountRepository;
import com.hoangjunss.junsBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OurUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUserCode(username)
                             .orElseThrow(()-> new UsernameNotFoundException("User not found with email: " + username));
    }
}
