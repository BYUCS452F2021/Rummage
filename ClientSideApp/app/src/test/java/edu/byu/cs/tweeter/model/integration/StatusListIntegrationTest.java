package edu.byu.cs.tweeter.model.integration;
/*

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.client.model.service.StatusListProxyService;
import edu.byu.cs.tweeter.shared.model.domain.Status;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;

public class StatusListIntegrationTest {
    StatusListRequest validRequest;
    StatusListRequest invalidRequest;

    StatusListResponse successResponse;
    StatusListResponse failureResponse;

    StatusListProxyService statusListService;

    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    private static final String FEMALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png";

    */
/**
     * Create a StatusListService spy that uses a mock ServerFacade to return known responses to
     * requests.
     *//*

    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {
        User user1 = new User("Allen", "Anderson", MALE_IMAGE_URL);
        User user2 = new User("Amy", "Ames", FEMALE_IMAGE_URL);
        User user3 = new User("Bob", "Bobson", MALE_IMAGE_URL);

        Status resultStatus1 = new Status("@AllenAnderson https://google.com food https://www.msn.com", user1, ZonedDateTime.of(0,1,1,1,1,1,1, ZoneId.of("UTC+0")));
        Status resultStatus2 = new Status("hi there https://www.google.com/search?q=food&rlz=1C1GCEA_enUS819US819&oq=food&aqs=chrome..69i57.1576j0j15&sourceid=chrome&ie=UTF-8 @GaryGilbert!", user2, ZonedDateTime.of(1,1,1,1,1,1,1, ZoneId.of("UTC+0")));
        Status resultStatus3 = new Status("https://byu.edu Post message", user3, ZonedDateTime.of(3,1,1,1,1,1,1, ZoneId.of("UTC+0")));

        // Setup request objects to use in the tests
        validRequest = new StatusListRequest(user1.getAlias(), 3, null, false);
        invalidRequest = new StatusListRequest(null, 0, null, false);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new StatusListResponse(Arrays.asList(resultStatus1, resultStatus2, resultStatus3), true);

        failureResponse = new StatusListResponse("An exception has occurred");
        failureResponse.setStatusList(new ArrayList<>());

        // Create a StatusListService instance and wrap it with a spy that will use the mock service
        statusListService = new StatusListProxyService();
    }

    */
/**
     * Verify that for successful requests the {@link StatusListProxyService#getStatuses(StatusListRequest)}
     * method returns the same result as the {@link ServerFacade}.
     * .
     *
     * @throws IOException if an IO error occurs.
     *//*

    @Test
    public void testGetStatuses_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        StatusListResponse response = statusListService.getStatuses(validRequest);
        Assertions.assertEquals(successResponse.getStatusList(), response.getStatusList());
        Assertions.assertEquals(successResponse.getHasMorePages(), response.getHasMorePages());
        Assertions.assertEquals(successResponse.getMessage(), response.getMessage());
    }

    */
/**
     * Verify that the {@link StatusListProxyService#getStatuses(StatusListRequest)} method loads the
     * profile image of each user included in the result.
     *
     * @throws IOException if an IO error occurs.
     *//*

    @Test
    public void testGetStatuses_validRequest_loadsProfileImages() throws IOException, TweeterRemoteException {
        StatusListResponse response = statusListService.getStatuses(validRequest);

        for(Status status : response.getStatusList()) {
            Assertions.assertNotNull(status.getPoster().getImageBytes());
        }
    }

    */
/* *
     * Verify that for failed requests the {@link StatusListProxyService#getStatuses(StatusListRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     *//*

    //@Test
    //public void testGetStatuses_invalidRequest_returnsNoStatuses() throws IOException, TweeterRemoteException {
    //    StatusListResponse response = statusListService.getStatuses(invalidRequest);
    //    Assertions.assertEquals(failureResponse.getStatusList(), response.getStatusList());
    //    Assertions.assertEquals(failureResponse.getHasMorePages(), response.getHasMorePages());
    //}
}
*/
