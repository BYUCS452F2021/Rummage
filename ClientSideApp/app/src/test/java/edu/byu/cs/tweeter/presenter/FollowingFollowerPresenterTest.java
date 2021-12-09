package edu.byu.cs.tweeter.presenter;
/*

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import edu.byu.cs.tweeter.client.presenter.FollowingFollowerPresenter;
import edu.byu.cs.tweeter.client.presenter.Presenter;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.service.FollowingFollowerProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.CheckAuthorizedService;
import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowingFollowersResponse;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

class FollowingFollowerPresenterTest {

    private FollowingFollowerPresenter spyPresenter;
    private FollowingFollowerProxyService mockFollowingFollowerService;
    private CheckAuthorizedService mockCheckAuthorizedService;

    private FollowingFollowersRequest testRequest;
    private FollowingFollowersResponse expectedResponse;

    */
/**
     * Create a FollowingFollowerPresenter spy that uses a mock service to return known responses to
     * requests.
     *//*

    @BeforeEach
    public void setup() throws Exception {
        User currentUser = new User("FirstName", "LastName", null);

        User resultUser1 = new User("FirstName1", "LastName1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        User resultUser2 = new User("FirstName2", "LastName2",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
        User resultUser3 = new User("FirstName3", "LastName3",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");

        testRequest = new FollowingFollowersRequest(currentUser.getAlias(), 3, null, true);
        expectedResponse = new FollowingFollowersResponse(Arrays.asList(resultUser1, resultUser2, resultUser3), false);

        // setup the mock service
        mockFollowingFollowerService = Mockito.mock(FollowingFollowerProxyService.class);
        Mockito.doReturn(expectedResponse).when((FollowingFollowerProxyService) mockFollowingFollowerService).getFollowingFollowers(testRequest);
        //Mockito.when(mockFollowingFollowerService.getFollowList(testRequest)).thenReturn(expectedResponse);

        mockCheckAuthorizedService = Mockito.mock(CheckAuthorizedService.class);
        Mockito.when(mockCheckAuthorizedService.checkAuthorized(Mockito.any(SignOutRequest.class))).thenReturn(new SignOutResponse());

        // setup the presenter we're testing, and assign it to use the mock
        spyPresenter = Mockito.spy(new FollowingFollowerPresenter(new Presenter.View() {
        }));
        Mockito.when(spyPresenter.getFollowingService()).thenReturn(mockFollowingFollowerService);
        Mockito.when(spyPresenter.getCheckAuthorizedService()).thenReturn(mockCheckAuthorizedService);
    }

    */
/*
     * Tests if the presenter returns the same response as the service
     *//*

    @Test
    public void testGetFollowing_returnsServiceResult() throws Exception {
        // Assert that the presenter returns the same response as the service (it doesn't do
        // anything else, so there's nothing else to test).
        FollowingFollowersResponse actualResponse = spyPresenter.getFollowList(testRequest);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    */
/*
     * Tests if the presenter throws the same error as the service
     *//*

    @Test
    public void testGetFollowing_serviceThrowsIOException_presenterThrowsIOException() throws IOException, TweeterRemoteException {
        Mockito.when(mockFollowingFollowerService.getFollowingFollowers(testRequest)).thenThrow(new IOException());
        Assertions.assertThrows(IOException.class, () -> spyPresenter.getFollowList(testRequest));
    }
}
*/
