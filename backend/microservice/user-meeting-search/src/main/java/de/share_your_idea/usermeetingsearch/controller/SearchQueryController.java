package de.share_your_idea.usermeetingsearch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.share_your_idea.usermeetingsearch.entity.SearchQueryEntity;
import de.share_your_idea.usermeetingsearch.service.UserMeetingSearchService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("user-meeting-search")
@RestController
public class SearchQueryController {

    private final UserMeetingSearchService userMeetingSearchService;

    @Autowired
    public SearchQueryController(UserMeetingSearchService userMeetingSearchService) {
        this.userMeetingSearchService = userMeetingSearchService;
    }

    @GetMapping("/search-user/{searchQuery}")
    public ResponseEntity<SearchQueryEntity> searchUser(@PathVariable("searchQuery") String searchQuery) throws JsonProcessingException, NotFoundException {
        log.info("SearchQueryController-Controller: SearchUser-Method is called");
        SearchQueryEntity searchQueryEntity = userMeetingSearchService.searchUserEntityBySearchQuery(searchQuery);
        log.info("SearchQueryController-Controller: SearchUser-Method founded SearchQueryEntity : {}", new ObjectMapper().writeValueAsString(searchQueryEntity));
        return new ResponseEntity<>(searchQueryEntity, HttpStatus.OK);
    }

    @GetMapping("/search-meeting/{searchQuery}")
    public ResponseEntity<SearchQueryEntity> searchMeeting(@PathVariable("searchQuery") String searchQuery) throws JsonProcessingException, NotFoundException {
        log.info("SearchQueryController-Controller: SearchMeeting-Method is called");
        SearchQueryEntity searchQueryEntity = userMeetingSearchService.searchUserMeetingEntityBySearchQuery(searchQuery);
        log.info("SearchQueryController-Controller: searchMeeting-Method founded SearchQueryEntity : {}", new ObjectMapper().writeValueAsString(searchQueryEntity));
        return new ResponseEntity<>(searchQueryEntity, HttpStatus.OK);
    }
}