package de.share_your_idea.user_registration.entity;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
