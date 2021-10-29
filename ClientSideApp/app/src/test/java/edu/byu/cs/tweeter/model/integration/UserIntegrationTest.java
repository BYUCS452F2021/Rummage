package edu.byu.cs.tweeter.model.integration;
/*

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.client.model.service.UserProxyService;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.UserRequest;
import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;

public class UserIntegrationTest {
    UserRequest validRequest;
    UserRequest invalidRequest;

    UserResponse successResponse;
    UserResponse failureResponse;

    UserProxyService userServiceTask;

    */
/**
     * Create a UserService spy that uses a mock ServerFacade to return known responses to
     * requests.
     *//*

    @BeforeEach
    public void setup() {
        String loggedInUser = "DoesThisNameMatter?";

        String userAlias = "FirstNameLastName";

        User resultUser = new User("dummy1", "dummy2", "FirstNameLastName", "https://faculty.cs.byu.edu" +
                "/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        // Setup request objects to use in the tests
        validRequest = new UserRequest(userAlias, loggedInUser);
        invalidRequest = new UserRequest("@alsdkfjasdlfkjasldfj", loggedInUser);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new UserResponse(resultUser);
        failureResponse = new UserResponse("An exception has occurred");

        // Create a UserService instance and wrap it with a spy that will use the mock service
        userServiceTask = new UserProxyService();
    }

    */
/**
     * Verify that for successful requests the {@link UserProxyService#getUser(UserRequest)}
     * method returns the same result as the {@link ServerFacade}.
     * .
     *
     * @throws IOException if an IO error occurs.
     *//*

    @Test
    public void testGetUser_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        UserResponse response = userServiceTask.getUser(validRequest);
        Assertions.assertEquals(successResponse.getCurrUser(), response.getCurrUser());
        Assertions.assertEquals(successResponse.getUser(), response.getUser());
        Assertions.assertEquals(successResponse.getIsFollowing(), response.getIsFollowing());
    }

    */
/**
     * Verify that the {@link UserProxyService#getUser(UserRequest)} method loads the
     * profile image of each user included in the result.
     *
     * @throws IOException if an IO error occurs.
     *//*

    @Test
    public void testGetUser_validRequest_loadsProfileImages() throws IOException, TweeterRemoteException {
        UserResponse response = userServiceTask.getUser(validRequest);
        Assertions.assertNotNull(response.getUser().getImageBytes());
    }

    */
/**
     * Verify that for failed requests the {@link UserProxyService#getUser(UserRequest)}
     * method returns the expected response.
     *//*

    @Test
    public void testGetUser_invalidRequest_returnsNoUser() {
        Assertions.assertThrows(Exception.class, () -> userServiceTask.getUser(invalidRequest));
    }

}
*/
