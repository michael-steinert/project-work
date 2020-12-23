package de.share_your_idea.user_registration.service;

import de.share_your_idea.user_registration.entity.UserEntity;
import de.share_your_idea.user_registration.repository.UserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity saveUser(UserEntity userEntity) {
        log.info("UserService: SaveUser Method is called");
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setUserRole("ROLE_USER");
        return userEntityRepository.save(userEntity);
    }

    public UserEntity findByUsername(String username) {
        log.info("UserService: FindByLogin Method is called");
        return userEntityRepository.findByUsername(username);
    }

    public UserEntity findByUsernameAndPassword(String username, String password) {
        log.info("UserService: FindByUsernameAndPassword Method is called");
        UserEntity userEntity = findByUsername(username);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
