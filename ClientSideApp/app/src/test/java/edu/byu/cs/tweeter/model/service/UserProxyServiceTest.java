package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.UserProxyService;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.UserRequest;
import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;

public class UserProxyServiceTest {
    UserRequest validRequest;
    UserRequest invalidRequest;

    UserResponse successResponse;
    UserResponse failureResponse;

    UserProxyService userServiceTask;

    /**
     * Create a UserService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {
        String loggedInUser = "doesThisMatter?";

        String userAlias = "FirstNameLastName";

        User resultUser = new User("FirstName", "LastName", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        // Setup request objects to use in the tests
        validRequest = new UserRequest(userAlias, loggedInUser);
        invalidRequest = new UserRequest("", loggedInUser);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new UserResponse(resultUser);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.getUser(validRequest, "/getuserview")).thenReturn(successResponse);

        failureResponse = new UserResponse("An exception has occurred");
        Mockito.when(mockServerFacade.getUser(invalidRequest, "/getuserview")).thenReturn(failureResponse);

        // Create a UserService instance and wrap it with a spy that will use the mock service
        userServiceTask = Mockito.spy(new UserProxyService());
        Mockito.when(userServiceTask.getServerFacade()).thenReturn(mockServerFacade);
    }

    /**
     * Verify that for successful requests the {@link UserProxyService#getUser(UserRequest)}
     * method returns the same result as the {@link ServerFacade}.
     * .
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    public void testGetUser_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        UserResponse response = userServiceTask.getUser(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    /**
     * Verify that the {@link UserProxyService#getUser(UserRequest)} method loads the
     * profile image of each user included in the result.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    public void testGetUser_validRequest_loadsProfileImages() throws IOException, TweeterRemoteException {
        UserResponse response = userServiceTask.getUser(validRequest);

        Assertions.assertNotNull(response.getUser().getImageBytes());

    }

    /**
     * Verify that for failed requests the {@link UserProxyService#getUser(UserRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    public void testGetUser_invalidRequest_returnsNoUser() throws IOException {
        Assertions.assertThrows(AssertionError.class, () -> {
            UserResponse response = userServiceTask.getUser(invalidRequest);
        });
    }
}
