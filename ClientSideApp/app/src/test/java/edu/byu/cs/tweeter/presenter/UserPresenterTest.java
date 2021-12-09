package edu.byu.cs.tweeter.presenter;
/*

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.presenter.UserPresenter;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.service.UserProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.CheckAuthorizedService;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.request.UserRequest;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;

public class UserPresenterTest {
    private UserPresenter spyPresenter;
    private UserProxyService mockUserService;
    private CheckAuthorizedService mockCheckAuthorizedService;

    private UserRequest testRequest;
    private UserResponse expectedResponse;

    */
/**
     * Create a UserPresenter spy that uses a mock service to return known responses to
     * requests.
     *//*

    @BeforeEach
    public void setup() throws Exception {
        String currUserAlias = "@someCurrUser";
        String userAlias = "FirstNameLastName";

        User resultUser = new User("FirstName", "LastName", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        // Setup request objects to use in the tests
        testRequest = new UserRequest(userAlias, currUserAlias);

        expectedResponse = new UserResponse(resultUser);

        mockUserService = Mockito.mock(UserProxyService.class);
        Mockito.when(mockUserService.getUser(testRequest)).thenReturn(expectedResponse);

        mockCheckAuthorizedService = Mockito.mock(CheckAuthorizedService.class);
        Mockito.when(mockCheckAuthorizedService.checkAuthorized(Mockito.any(SignOutRequest.class))).thenReturn(new SignOutResponse());

        // setup the presenter we're testing, and assign it to use the mock
        spyPresenter = Mockito.spy(new UserPresenter(new UserPresenter.View() {
        }));
        Mockito.when(spyPresenter.getUserService()).thenReturn(mockUserService);
        Mockito.when(spyPresenter.getCheckAuthorizedService()).thenReturn(mockCheckAuthorizedService);
    }

    */
/*
     * Tests if the presenter returns the same response as the service
     *//*

    @Test
    public void testGetUser_returnsServiceResult() throws Exception {
        // Assert that the presenter returns the same response as the service (it doesn't do
        // anything else, so there's nothing else to test).
        UserResponse actualResponse = spyPresenter.getUser(testRequest);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    */
/*
     * Tests if the presenter throws the same error as the service
     *//*

    @Test
    public void testGetUser_serviceThrowsIOException_presenterThrowsIOException() throws IOException, TweeterRemoteException {
        Mockito.when(mockUserService.getUser(testRequest)).thenThrow(new IOException());
        Assertions.assertThrows(IOException.class, () -> spyPresenter.getUser(testRequest));
    }
}
*/
