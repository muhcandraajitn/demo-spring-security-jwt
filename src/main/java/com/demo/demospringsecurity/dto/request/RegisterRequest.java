package com.demo.demospringsecurity.dto.request;

import com.demo.demospringsecurity.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String pin;
    @Enumerated(EnumType.STRING)
    private Role role;
}
