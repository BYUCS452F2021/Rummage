package edu.byu.cs.tweeter.model.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.client.model.net.TweeterServerException;
import edu.byu.cs.tweeter.client.model.service.FollowCountProxyService;
import edu.byu.cs.tweeter.shared.model.domain.FollowCount;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.FollowCountRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;

public class FollowCountIntegrationTest {

    private FollowCountRequest validRequest;
    private FollowCountRequest invalidRequest;

    private FollowCountResponse successResponse;

    private FollowCountProxyService followCountProxyService;

    @BeforeEach
    public void setup() {
        followCountProxyService = new FollowCountProxyService();

        int expectedNumFollowers = 17;
        int expectedNumFollowing = 42;

        User currentUser = new User("Donald", "Duck", "@testUser", "www.example3.com");
        FollowCount followCount = new FollowCount(currentUser.getAlias());
        followCount.setNumFollowers(expectedNumFollowers);
        followCount.setNumFollowing(expectedNumFollowing);

        // Setup request objects to use in the tests
        validRequest = new FollowCountRequest(currentUser.getAlias());
        invalidRequest = new FollowCountRequest("");

        successResponse = new FollowCountResponse(followCount);
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
        FollowCountResponse response = followCountProxyService.getFollowCount(validRequest);
        Assertions.assertEquals(successResponse.getFollowCount().getNumFollowers(), response.getFollowCount().getNumFollowers());
        Assertions.assertEquals(successResponse.getFollowCount().getNumFollowing(), response.getFollowCount().getNumFollowing());
    }

    /**
     * Verify that for failed requests the {@link FollowCountProxyService#getFollowCount(FollowCountRequest)}
     * method throws an exception.
     */
    @Test
    public void testGetFollowCount_invalidRequest_throwsException() {
       Assertions.assertThrows(TweeterServerException.class, () -> followCountProxyService.getFollowCount(invalidRequest));
    }

}
