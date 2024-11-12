package com.hoangjunss.junsBank.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "identification_number", nullable = false, unique = true)
    private String identificationNumber;
    @Column(name = "status_user", nullable = false)
    private boolean status;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }
}
