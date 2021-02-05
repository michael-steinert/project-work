package de.share_your_idea.usermeetingsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserMeetingSearchService {

    @Autowired
    public UserMeetingSearchService() {

    }

    /*
    TODO:
    Add Method to retrieve UserEntities from User-Management-Service to display the Search-Result.
    The retrieved Data should be stored in a temporary Data-Structure (for Example a Map with Date and corresponding Data),
    if the User-Management-Service is not available for the next Search-Query, the last retrieved Result can be displayed instead.
    */

}
