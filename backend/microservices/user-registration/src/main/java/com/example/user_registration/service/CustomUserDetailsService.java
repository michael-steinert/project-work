package com.example.user_registration.service;

import com.example.user_registration.config.CustomUserDetails;
import com.example.user_registration.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("CustomUserDetailsService: LoadUserByUsername Method is called");
        UserEntity userEntity = userService.findByUsername(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }
}
