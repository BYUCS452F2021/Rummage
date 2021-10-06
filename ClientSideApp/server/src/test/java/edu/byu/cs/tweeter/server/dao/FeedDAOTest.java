package edu.byu.cs.tweeter.server.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SQSPostRequest;
import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FeedDAOTest {

    private FeedDAO feedDAOToTest;
    private String currDateString;
    private String validUserAlias;
    private String validImageURL;
    private String validFirstName;
    private String validLastName;
    private List<String> batch;
    private long dateNum;

    @BeforeEach
    void setUp() {
        feedDAOToTest = new FeedDAO();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
        ZonedDateTime now = ZonedDateTime.now();
        currDateString = now.format(formatter);
        dateNum = now.toInstant().atZone(ZoneOffset.UTC).toEpochSecond();
        validUserAlias = "@testAuthTokenCreate";
        validImageURL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
        validFirstName = "test";
        validLastName = "Create";
        batch = new ArrayList<>();
        batch.add("guy7644");
        batch.add("guy7645");
        batch.add("guy7646");
    }

    @Test
    void create_whenNew_thenCreates_DoesntThrow() throws Exception {
        feedDAOToTest.createPostBatch(new SQSPostRequest(new PostRequest(validUserAlias,"testPostDAO"), validFirstName,validLastName,validImageURL,currDateString,dateNum), batch);

    }

    @Test
    void read_hasCorrectSize() throws Exception {
        // setup
        StatusListResponse statusListResponse = feedDAOToTest.readStatuses(new StatusListRequest("guy7644", 10, null, true));
        Assertions.assertEquals(9, statusListResponse.getStatusList().size());
    }

}
