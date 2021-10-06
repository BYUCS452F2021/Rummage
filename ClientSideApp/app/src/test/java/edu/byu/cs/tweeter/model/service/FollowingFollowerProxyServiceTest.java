package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import edu.byu.cs.tweeter.client.model.service.FollowingFollowerProxyService;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowingFollowersResponse;


public class FollowingFollowerProxyServiceTest {

    private FollowingFollowersRequest validRequest;
    private FollowingFollowersRequest invalidRequest;

    private FollowingFollowersResponse successResponse;
    private FollowingFollowersResponse failureResponse;

    private FollowingFollowerProxyService followingFollowerServiceSpy;

    /**
     * Create a FollowingService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {
        User currentUser = new User("FirstName", "LastName", null);

        User resultUser1 = new User("FirstName1", "LastName1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        User resultUser2 = new User("FirstName2", "LastName2",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
        User resultUser3 = new User("FirstName3", "LastName3",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");

        // Setup request objects to use in the tests
        validRequest = new FollowingFollowersRequest(currentUser.getAlias(), 3, null, true);
        invalidRequest = new FollowingFollowersRequest(null, 0, null, true);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new FollowingFollowersResponse(Arrays.asList(resultUser1, resultUser2, resultUser3), false);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.getFollowingFollowers(validRequest,"/getfollowingfollowers")).thenReturn(successResponse);

        failureResponse = new FollowingFollowersResponse("An exception occurred");
        Mockito.when(mockServerFacade.getFollowingFollowers(invalidRequest, "/getfollowingfollowers")).thenReturn(failureResponse);

        // Create a FollowingService instance and wrap it with a spy that will use the mock service
        followingFollowerServiceSpy = Mockito.spy(new FollowingFollowerProxyService());
        Mockito.when(followingFollowerServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    /**
     * Verify that for successful requests the {@link FollowingFollowerProxyService#getFollowingFollowers(FollowingFollowersRequest)} (FollowingFollowersRequest)}
     * method returns the same result as the {@link ServerFacade}.
     * .
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    public void testGetFollowees_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        FollowingFollowersResponse response = followingFollowerServiceSpy.getFollowingFollowers(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    /**
     * Verify that the {@link FollowingFollowerProxyService#getFollowingFollowers(FollowingFollowersRequest)} (FollowingFollowersRequest)} method loads the
     * profile image of each user included in the result.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    public void testGetFollowees_validRequest_loadsProfileImages() throws IOException, TweeterRemoteException {
        FollowingFollowersResponse response = followingFollowerServiceSpy.getFollowingFollowers(validRequest);

        for(User user : response.getFollowees()) {
            Assertions.assertNotNull(user.getImageBytes());
        }
    }

    /**
     * Verify that for failed requests the {@link FollowingFollowerProxyService#getFollowingFollowers(FollowingFollowersRequest)} (FollowingFollowersRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    public void testGetFollowees_invalidRequest_returnsNoFollowees() throws IOException, TweeterRemoteException {
        FollowingFollowersResponse response = followingFollowerServiceSpy.getFollowingFollowers(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }
}
