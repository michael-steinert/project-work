package com.example.user_registration.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
