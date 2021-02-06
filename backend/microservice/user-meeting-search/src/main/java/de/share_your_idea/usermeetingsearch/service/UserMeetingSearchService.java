package de.share_your_idea.usermeetingsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.share_your_idea.usermeetingsearch.entity.SearchQueryEntity;
import de.share_your_idea.usermeetingsearch.entity.UserEntity;
import de.share_your_idea.usermeetingsearch.entity.UserMeetingEntity;
import de.share_your_idea.usermeetingsearch.repository.SearchQueryEntityRepository;
import de.share_your_idea.usermeetingsearch.repository.UserEntityRepository;
import de.share_your_idea.usermeetingsearch.repository.UserMeetingEntityRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserMeetingSearchService {

    private final SearchQueryEntityRepository searchQueryEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final UserMeetingEntityRepository userMeetingEntityRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public UserMeetingSearchService(SearchQueryEntityRepository searchQueryEntityRepository,
                                    UserEntityRepository userEntityRepository,
                                    UserMeetingEntityRepository userMeetingEntityRepository,
                                    RestTemplate restTemplate) {
        this.searchQueryEntityRepository = searchQueryEntityRepository;
        this.userEntityRepository = userEntityRepository;
        this.userMeetingEntityRepository = userMeetingEntityRepository;
        this.restTemplate = restTemplate;
    }

    /*
    TODO:
    Add Method to retrieve UserEntities from User-Management-Service to display the Search-Result.
    The retrieved Data should be stored in a temporary Data-Structure (for Example a Map with Date and corresponding Data),
    if the User-Management-Service is not available for the next Search-Query, the last retrieved Result can be displayed instead.
    */

    public SearchQueryEntity searchUserEntityBySearchQuery(String searchQuery) throws JsonProcessingException, NotFoundException {
        log.info("UserMeetingSearch-Service: SearchUserEntityBySearchQuery-Method is called");

        //Eventually is Wrapper-Class necessary to carry the ResponseEntity
        ResponseEntity<UserEntity[]> responseEntity = restTemplate
                .getForEntity("http://USER-MANAGEMENT-SERVICE/user-management/fetch-all-users",
                        UserEntity[].class, new ParameterizedTypeReference<UserEntity>() {});

        if (responseEntity != null && responseEntity.hasBody()) {

            UserEntity[] userEntityArray = responseEntity.getBody();

            List<UserEntity> userEntityList = Arrays.stream(userEntityArray)
                    .map(userEntity -> new UserEntity(userEntity.getUserId(), userEntity.getUsername(), userEntity.getUserRole(), userEntity.getAuthorizationToken()))
                    .collect(Collectors.toList());

            userEntityRepository.saveAll(userEntityList);
            List<UserEntity> searchQueryResult = userEntityRepository.findUserEntityByUsernameContaining(searchQuery);
            SearchQueryEntity searchQueryEntity = new SearchQueryEntity();
            searchQueryEntity.setSearchQuery(searchQuery);
            searchQueryEntity.setUserEntityResult(searchQueryResult);
            searchQueryEntityRepository.save(searchQueryEntity);
            log.info("UserMeetingSearch-Service: SearchUserEntityBySearchQuery-Method fetched UserEntityList : {}", new ObjectMapper().writeValueAsString(userEntityList));
            log.info("UserMeetingSearch-Service: SearchUserEntityBySearchQuery-Method founded SearchQueryResult : {}", new ObjectMapper().writeValueAsString(searchQueryResult));
            log.info("UserMeetingSearch-Service: SearchUserEntityBySearchQuery-Method saved SearchQueryEntity : {}", new ObjectMapper().writeValueAsString(searchQueryEntity));
            return searchQueryEntity;
        }
        throw new NotFoundException("UserEntity with Username like " + searchQuery + " not found.");
    }

    public SearchQueryEntity searchUserMeetingEntityBySearchQuery(String searchQuery) throws JsonProcessingException, NotFoundException {
        log.info("UserMeetingSearch-Service: SearchUserMeetingEntityBySearchQuery-Method is called");

        //Eventually is Wrapper-Class necessary to carry the ResponseEntity
        ResponseEntity<UserMeetingEntity[]> responseEntity = restTemplate
                .getForEntity("http://USER-MEETING-SERVICE/user-meeting/fetch-all-user-meetings",
                        UserMeetingEntity[].class, new ParameterizedTypeReference<UserEntity>() {});

        if (responseEntity != null && responseEntity.hasBody()) {
            UserMeetingEntity[] userMeetingEntityArray = responseEntity.getBody();

            List<UserMeetingEntity> userMeetingEntityList = Arrays.stream(userMeetingEntityArray)
                    .map(userMeetingEntity -> new UserMeetingEntity(userMeetingEntity.getMeetingId(), userMeetingEntity.getMeetingName()))
                    .collect(Collectors.toList());

            userMeetingEntityRepository.saveAll(userMeetingEntityList);
            List<UserMeetingEntity> searchQueryResult = userMeetingEntityRepository.findUserMeetingEntityByMeetingNameContaining(searchQuery);
            SearchQueryEntity searchQueryEntity = new SearchQueryEntity();
            searchQueryEntity.setSearchQuery(searchQuery);
            searchQueryEntity.setUserMeetingEntityResult(searchQueryResult);
            searchQueryEntityRepository.save(searchQueryEntity);
            log.info("UserMeetingSearch-Service: SearchUserMeetingEntityBySearchQuery-Method fetched UserMeetingEntityList : {}", new ObjectMapper().writeValueAsString(userMeetingEntityList));
            log.info("UserMeetingSearch-Service: SearchUserMeetingEntityBySearchQuery-Method founded SearchQueryResult : {}", new ObjectMapper().writeValueAsString(searchQueryResult));
            log.info("UserMeetingSearch-Service: SearchUserMeetingEntityBySearchQuery-Method saved SearchQueryEntity : {}", new ObjectMapper().writeValueAsString(searchQueryEntity));
            return searchQueryEntity;
        }
        throw new NotFoundException("UserMeetingEntity with MeetingName like " + searchQuery + " not found.");
    }

    public List<SearchQueryEntity> findAllSearchQueries() {
        log.info("UserMeetingSearch-Service: FindAllSearchQueries-Method is called");
        return searchQueryEntityRepository.findAll();
    }

    public List<UserEntity> findAllUsers() {
        log.info("UserMeetingSearch-Service: FindAllUsers-Method is called");
        return userEntityRepository.findAll();
    }

    public List<UserMeetingEntity> findAllMeetings() {
        log.info("UserMeetingSearch-Service: FindAllMeetings-Method is called");
        return userMeetingEntityRepository.findAll();
    }
}
