package de.share_your_idea.user_management.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.share_your_idea.user_management.model.UserEntity;
import de.share_your_idea.user_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Start Authentication");
        log.info("Custom User Details Service: LoadUserByUsername Method is called");
        UserEntity userEntity = null;
        try {
            userEntity = userService.getUserByUsername(username);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        CustomUserDetails customUserDetails = CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
        try {
            log.info("CustomUserDetailsService: FromUserEntityToCustomUserDetails Method created CustomUserDetails : {}", new ObjectMapper().writeValueAsString(customUserDetails));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("End Authentication");
        return customUserDetails;
    }
}
