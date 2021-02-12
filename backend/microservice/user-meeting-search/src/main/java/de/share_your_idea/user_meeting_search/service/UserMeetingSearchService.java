package de.share_your_idea.user_meeting_search.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.share_your_idea.user_meeting_search.entity.SearchQueryEntity;
import de.share_your_idea.user_meeting_search.entity.UserEntity;
import de.share_your_idea.user_meeting_search.entity.UserMeetingEntity;
import de.share_your_idea.user_meeting_search.repository.SearchQueryEntityRepository;
import de.share_your_idea.user_meeting_search.repository.UserEntityRepository;
import de.share_your_idea.user_meeting_search.repository.UserMeetingEntityRepository;
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

        ResponseEntity<UserEntity[]> responseEntity = restTemplate
                .getForEntity("http://USER-MANAGEMENT-SERVICE/user-management/fetch-all-users",
                        UserEntity[].class, new ParameterizedTypeReference<UserEntity>() {
                        });

        if (responseEntity != null && responseEntity.hasBody()) {
            /*
            Request Data from another Service.
            */
            UserEntity[] userEntityArray = responseEntity.getBody();
            /*
            Check if the Request to the other Service has delivered Data, otherwise an Exception is handled.
            */
            List<UserEntity> userEntityList = Arrays.stream(userEntityArray)
                    .map(userEntity -> new UserEntity(userEntity.getUsername(), userEntity.getUserRole(), userEntity.getAuthorizationToken()))
                    .collect(Collectors.toList());

            /*
            The retrieved Data is stored in the own Database in case a reconnection to the other Service is not possible.
            This increases the Resilience of this Service.
            */
            userEntityRepository.deleteAll();
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
        /*
        Request Data from another Service.
        */
        ResponseEntity<UserMeetingEntity[]> responseEntity = restTemplate
                .getForEntity("http://USER-MEETING-SERVICE/user-meeting/fetch-all-user-meetings",
                        UserMeetingEntity[].class, new ParameterizedTypeReference<UserEntity>() {
                        });
        /*
        Check if the Request to the other Service has delivered Data, otherwise an Exception is handled.
        */
        if (responseEntity != null && responseEntity.hasBody()) {
            UserMeetingEntity[] userMeetingEntityArray = responseEntity.getBody();

            List<UserMeetingEntity> userMeetingEntityList = Arrays.stream(userMeetingEntityArray)
                    .map(userMeetingEntity -> new UserMeetingEntity(userMeetingEntity.getMeetingName()))
                    .collect(Collectors.toList());

            /*
            The retrieved Data is stored in the own Database in case a reconnection to the other Service is not possible.
            This increases the Resilience of this Service.
            */
            userMeetingEntityRepository.deleteAll();
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
