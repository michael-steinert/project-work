package com.example.user_registration.entity;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
