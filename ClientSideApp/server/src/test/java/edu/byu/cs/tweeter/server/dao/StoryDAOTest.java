package edu.byu.cs.tweeter.server.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class StoryDAOTest {

    private StoryDAO storyDAO;
    private String currDateString;
    private String validUserAlias;
    private String validImageURL;
    private String validFirstName;
    private String validLastName;
    private long dateNum;

    @BeforeEach
    void setUp() {
        storyDAO = new StoryDAO();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
        ZonedDateTime now = ZonedDateTime.now();
        currDateString = now.format(formatter);
        dateNum = now.toInstant().atZone(ZoneOffset.UTC).toEpochSecond();
        validUserAlias = "@testAuthTokenCreate";
        validImageURL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
        validFirstName = "test";
        validLastName = "Create";
    }

    @Test
    void create_whenNew_thenCreates_DoesntThrow() throws Exception {
        storyDAO.createPost(new PostRequest(validUserAlias,"testPostDAO"), validFirstName,validLastName,validImageURL,currDateString,dateNum);
    }

    @Test
    void read_hasCorrectSize() throws Exception {
        // setup
        StatusListResponse statusListResponse = storyDAO.readStatuses(new StatusListRequest(validUserAlias, 10, null, false));
        Assertions.assertEquals(9, statusListResponse.getYardSaleList().size());
    }

}
