package de.share_your_idea.user_management.service;

import de.share_your_idea.user_management.entity.UserEntity;
import de.share_your_idea.user_management.entity.UserRole;
import de.share_your_idea.user_management.exception.CustomEmptyInputException;
import de.share_your_idea.user_management.exception.CustomNotFoundException;
import de.share_your_idea.user_management.repository.UserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        log.info("User Service: SaveUser Method is called");
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setUserRole(UserRole.ROLE_USER);
        return userEntityRepository.save(userEntity);
    }

    public UserEntity findByUsername(String username) {
        log.info("User Service: FindByUsername Method is called");
        if (username != null) {
            Optional<UserEntity> userEntityOptional = userEntityRepository.findUserEntityByUsername(username);
            return userEntityOptional.orElseThrow(()-> new CustomNotFoundException(String.format("UserEntity with Username like %s not found.", username)));
        }
        throw new CustomEmptyInputException("The Username is empty.");
    }

    public UserEntity findByUsernameAndPassword(String username, String password) {
        log.info("User Service: FindByUsernameAndPassword Method is called");
        UserEntity userEntity = findByUsername(username);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        throw new CustomNotFoundException(String.format("UserEntity with Username like %s and Password %s not found.", username, password));
    }

    public List<UserEntity> findAllUsers() {
        log.info("User Service: FindAllUsers Method is called");
        return userEntityRepository.findAll();
    }
}
