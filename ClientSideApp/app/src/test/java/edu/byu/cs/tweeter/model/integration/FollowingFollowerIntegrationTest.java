package edu.byu.cs.tweeter.model.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.client.model.service.FollowingFollowerProxyService;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowingFollowersResponse;

public class FollowingFollowerIntegrationTest {
    private FollowingFollowersRequest validRequest;
    private FollowingFollowersRequest invalidRequest;

    private FollowingFollowersResponse successResponse;
    private FollowingFollowersResponse failureResponse;

    private FollowingFollowerProxyService followingFollowerService;

    /**
     * Create a FollowingService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    public void setup() {
        User currentUser = new User("FirstName", "LastName", "@testFollowerFolloweeIntegration", null);

        User user1 = new User("Allen", "Anderson", "@testFF1", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        User user2 = new User("Amy", "Ames", "@testFF2", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
        User user3 = new User("Bob", "Bobson", "@testFF3", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        // Setup request objects to use in the tests
        validRequest = new FollowingFollowersRequest(currentUser.getAlias(), 3, null, true);
        invalidRequest = new FollowingFollowersRequest("@asdlfkjsdlfkjasdlfs", 0, null, true);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new FollowingFollowersResponse(Arrays.asList(user1, user2, user3), true);


        failureResponse = new FollowingFollowersResponse();
        failureResponse.setFollowees(new ArrayList<>());
        failureResponse.setSuccess(true);

        followingFollowerService = new FollowingFollowerProxyService();
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
        FollowingFollowersResponse response = followingFollowerService.getFollowingFollowers(validRequest);
        Assertions.assertEquals(successResponse.getFollowees().size(), response.getFollowees().size());
    }

    /**
     * Verify that the {@link FollowingFollowerProxyService#getFollowingFollowers(FollowingFollowersRequest)} (FollowingFollowersRequest)} method loads the
     * profile image of each user included in the result.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    public void testGetFollowees_validRequest_loadsProfileImages() throws IOException, TweeterRemoteException {
        FollowingFollowersResponse response = followingFollowerService.getFollowingFollowers(validRequest);

        for (User user : response.getFollowees()) {
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
        FollowingFollowersResponse response = followingFollowerService.getFollowingFollowers(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }
}
