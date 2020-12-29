package de.share_your_idea.user_management.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.share_your_idea.user_management.dao.UserDao;
import de.share_your_idea.user_management.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService {

    private final UserDao userDao;
    private final RestTemplate restTemplate;

    @Autowired
    public UserService(UserDao userDao, RestTemplate restTemplate) {
        this.userDao = userDao;
        this.restTemplate = restTemplate;
    }

    public List<UserEntity> getAllUsers() throws JsonProcessingException {
        log.info("User Service: GetAllUsers Method is called");
        Optional<List<UserEntity>> optionalUserEntityList = Optional.ofNullable(userDao.selectAllUsers());
        List<UserEntity> userEntityList = optionalUserEntityList.orElseThrow(() -> new NotFoundException("List of Users with UserUid not found."));
        log.info("User Service: GetAllUsers Method fetched List of UserEntities : {}", new ObjectMapper().writeValueAsString(userEntityList));
        return userEntityList;
    }

    public UserEntity getUserByUserUid(UUID userUid) throws JsonProcessingException {
        log.info("User Service: GetUserByUserUid Method is called");
        Optional<UserEntity> optionalUserEntity = Optional.ofNullable(userDao.selectUserByUserUid(userUid));
        UserEntity userEntity = optionalUserEntity.orElseThrow(() -> new NotFoundException("User with UserUid " + userUid + " not found."));
        log.info("User Service: GetUserByUserUid Method fetched UserEntity : {}", new ObjectMapper().writeValueAsString(userEntity));
        return userEntity;
    }

    public UserEntity getUserByUsername(String username) throws JsonProcessingException {
        log.info("User Service: GetUserByUsername Method is called");
        ResponseEntity<UserEntity> response =
                restTemplate.getForEntity("http://USER-REGISTRATION-SERVICE/user-registration/fetch-user-by-username/" + username, UserEntity.class, new ParameterizedTypeReference<UserEntity>(){});
        if(response != null && response.hasBody()){
            UserEntity userEntity = response.getBody();
            Integer result = userDao.insertUser(UserEntity.newUser(UUID.randomUUID(), userEntity));
            log.info("User Service: GetUserByUsername Method fetched UserEntity : {}", new ObjectMapper().writeValueAsString(userEntity));
            log.info("User Service: GetUserByUsername Method saved UserEntity : {}", new ObjectMapper().writeValueAsString(result));
            return userEntity;
        }
        throw new NotFoundException("User with Username " + username + " not found.");
    }

    public int insertUser(UserEntity userEntity) throws JsonProcessingException {
        log.info("User Service: InsertUser Method is called");
        Integer result = userDao.insertUser(UserEntity.newUser(UUID.randomUUID(), userEntity));
        log.info("User Service: InsertUser Method inserted UserEntity : {}", new ObjectMapper().writeValueAsString(result));
        return result;
    }

    public int updateUserByUserUid(UUID userUid, UserEntity userEntity) throws JsonProcessingException {
        log.info("User Service: UpdateUserByUserUid Methode is called");
        UserEntity existingUser = getUserByUserUid(userUid);
        UUID existingUserUid = existingUser.getUserUid();
        if (existingUserUid != null) {
            Integer result = userDao.updateUser(existingUserUid, userEntity);
            log.info("User Service: UpdateUserByUserUid Method updated UserEntity : {}", new ObjectMapper().writeValueAsString(result));
            return result;
        }
        throw new NotFoundException("User with UserUid " + userUid + " not found.");
    }

    public int removeUser(UUID userUid) throws JsonProcessingException {
        log.info("User Service: RemoveUser Methode is called");
        UserEntity existingUser = getUserByUserUid(userUid);
        UUID existingUserUid = existingUser.getUserUid();
        if (existingUserUid != null) {
            Integer result = userDao.deleteUserByUserUid(existingUserUid);
            log.info("User Service: RemoveUser Method removed UserEntity : {}", new ObjectMapper().writeValueAsString(result));
            return result;
        }
        throw new NotFoundException("User with UserUid " + userUid + " not found.");
    }
}