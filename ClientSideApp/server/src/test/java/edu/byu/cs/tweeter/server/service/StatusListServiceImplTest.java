package edu.byu.cs.tweeter.server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

import edu.byu.cs.tweeter.server.dao.FeedDAO;
import edu.byu.cs.tweeter.server.dao.StoryDAO;
import edu.byu.cs.tweeter.shared.model.domain.Status;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;

public class StatusListServiceImplTest {

    StatusListRequest validRequest;
    StatusListRequest invalidRequest;

    StatusListResponse successResponse;
    StatusListResponse failureResponse;

    StatusListServiceImpl statusListServiceImplSpy;

    /**
     * Create a StatusListServiceImpl spy that uses a mock DAO to return known responses to
     * requests.
     */
    @BeforeEach
    public void setup() {
        User currentUser = new User("FirstName", "LastName", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        Status resultStatus1 = new Status("@AllenAnderson https://google.com food https://www.msn.com", currentUser, ZonedDateTime.now(ZoneId.of("UTC+0")));
        Status resultStatus2 = new Status("Post message", currentUser, ZonedDateTime.now(ZoneId.of("UTC+0")));
        Status resultStatus3 = new Status("https://byu.edu Post message", currentUser, ZonedDateTime.now(ZoneId.of("UTC+0")));

        // Setup request objects to use in the tests
        validRequest = new StatusListRequest(currentUser.getAlias(), 2, null, false);
        invalidRequest = new StatusListRequest(null, 0, null, false);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new StatusListResponse(Arrays.asList(resultStatus1, resultStatus2, resultStatus3), false);
        StoryDAO storyDAO = Mockito.mock(StoryDAO.class);
        FeedDAO feeedDAO = Mockito.mock(FeedDAO.class);
        Mockito.when(storyDAO.readStatuses(validRequest)).thenReturn(successResponse);

        failureResponse = new StatusListResponse("An exception has occurred");
        Mockito.when(storyDAO.readStatuses(invalidRequest)).thenReturn(failureResponse);

        // Create a StatusListService instance and wrap it with a spy that will use the mock service
        statusListServiceImplSpy = Mockito.spy(new StatusListServiceImpl());
        Mockito.when(statusListServiceImplSpy.getStoryDAO()).thenReturn(storyDAO);
    }

    /**
     * Verify that for successful requests the {@link StatusListServiceImpl#getStatuses(StatusListRequest)}
     * method returns the same result as the
     */
    @Test
    public void testGetStatuses_validRequest_correctResponse() {
        StatusListResponse response = statusListServiceImplSpy.getStatuses(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    /**
     * Verify that for failed requests the {@link StatusListServiceImpl#getStatuses(StatusListRequest)}
     * method returns the same result as the
     */
    @Test
    public void testGetStatuses_invalidRequest_returnsNoStatuses() {
        StatusListResponse response = statusListServiceImplSpy.getStatuses(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }
}
