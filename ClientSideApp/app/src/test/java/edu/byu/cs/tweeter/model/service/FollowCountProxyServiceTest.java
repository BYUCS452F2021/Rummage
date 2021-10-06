package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.client.model.service.FollowCountProxyService;
import edu.byu.cs.tweeter.shared.model.domain.FollowCount;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.FollowCountRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;

public class FollowCountProxyServiceTest {

    private FollowCountRequest validRequest;
    private FollowCountRequest invalidRequest;

    private FollowCountResponse successResponse;
    private FollowCountResponse failureResponse;

    private FollowCountProxyService followCountProxyServiceSpy;

    /**
     * Create a FollowCount spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {
        User currentUser = new User("FirstName", "LastName", null);
        FollowCount followCount = new FollowCount(currentUser.getAlias());

        // Setup request objects to use in the tests
        validRequest = new FollowCountRequest(currentUser.getAlias());
        invalidRequest = new FollowCountRequest("dummy");

        // Setup a mock ServerFacade that will return known responses
        successResponse = new FollowCountResponse(followCount);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.getFollowCount(validRequest, "/getfollowcount")).thenReturn(successResponse);

        failureResponse = new FollowCountResponse("An exception occurred");
        Mockito.when(mockServerFacade.getFollowCount(invalidRequest, "/getfollowcount")).thenReturn(failureResponse);

        // Create a FollowingService instance and wrap it with a spy that will use the mock service
        followCountProxyServiceSpy = Mockito.spy(new FollowCountProxyService());
        Mockito.when(followCountProxyServiceSpy.getServerFacade()).thenReturn(mockServerFacade);

    }

    /**
     * Verify that for successful requests the {@link FollowCountProxyService#getFollowCount(FollowCountRequest)}
     * method returns the same result as the {@link ServerFacade}.
     * .
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    public void testGetFollowCount_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        FollowCountResponse response = followCountProxyServiceSpy.getFollowCount(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    /**
     * Verify that for failed requests the {@link FollowCountProxyService#getFollowCount(FollowCountRequest)}
     * method throws an exception.
     */
    @Test
    public void testGetFollowCount_invalidRequest_throwsException() {
        Assertions.assertThrows(AssertionError.class,
                () -> followCountProxyServiceSpy.getFollowCount(invalidRequest));
    }

}
