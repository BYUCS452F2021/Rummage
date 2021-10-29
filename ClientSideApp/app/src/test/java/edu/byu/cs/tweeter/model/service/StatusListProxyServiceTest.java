package edu.byu.cs.tweeter.model.service;
/*

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

import edu.byu.cs.tweeter.client.model.service.StatusListProxyService;
import edu.byu.cs.tweeter.shared.model.domain.Status;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;

public class StatusListProxyServiceTest {

    StatusListRequest validRequest;
    StatusListRequest invalidRequest;

    StatusListResponse successResponse;
    StatusListResponse failureResponse;

    StatusListProxyService statusListServiceSpy;

    */
/**
     * Create a StatusListService spy that uses a mock ServerFacade to return known responses to
     * requests.
     *//*

    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {
        User currentUser = new User("FirstName", "LastName", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        Status resultStatus1 = new Status("@AllenAnderson https://google.com food https://www.msn.com", currentUser, ZonedDateTime.now(ZoneId.of("UTC+0")));
        Status resultStatus2 = new Status("Post message", currentUser, ZonedDateTime.now(ZoneId.of("UTC+0")));
        Status resultStatus3 = new Status("https://byu.edu Post message", currentUser, ZonedDateTime.now(ZoneId.of("UTC+0")));

        // Setup request objects to use in the tests
        validRequest = new StatusListRequest(currentUser.getAlias(), 2, null, false);
        invalidRequest = new StatusListRequest(null, 0, null, false);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new StatusListResponse(Arrays.asList(resultStatus1, resultStatus2, resultStatus3), false);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.getStatuses(validRequest, "/getstatuslist")).thenReturn(successResponse);
        
        failureResponse = new StatusListResponse("An exception has occurred");
        Mockito.when(mockServerFacade.getStatuses(invalidRequest, "/getstatuslist")).thenReturn(failureResponse);

        // Create a StatusListService instance and wrap it with a spy that will use the mock service
        statusListServiceSpy = Mockito.spy(new StatusListProxyService());
        Mockito.when(statusListServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
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
        StatusListResponse response = statusListServiceSpy.getStatuses(validRequest);
        Assertions.assertEquals(successResponse, response);
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
        StatusListResponse response = statusListServiceSpy.getStatuses(validRequest);

        for(Status status : response.getStatusList()) {
            Assertions.assertNotNull(status.getPoster().getImageBytes());
        }
    }

    */
/**
     * Verify that for failed requests the {@link StatusListProxyService#getStatuses(StatusListRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     *//*

    @Test
    public void testGetStatuses_invalidRequest_returnsNoStatuses() throws IOException, TweeterRemoteException {
        StatusListResponse response = statusListServiceSpy.getStatuses(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }
}
*/
