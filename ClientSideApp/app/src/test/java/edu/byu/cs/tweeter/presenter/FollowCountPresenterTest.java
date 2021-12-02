package edu.byu.cs.tweeter.presenter;
/*

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.presenter.FollowCountPresenter;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.FollowCount;
import edu.byu.cs.tweeter.client.model.service.FollowCountProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.CheckAuthorizedService;
import edu.byu.cs.tweeter.shared.model.service.request.FollowCountRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

public class FollowCountPresenterTest {
    private FollowCountPresenter spyPresenter;
    private FollowCountProxyService mockFollowCountService;
    private CheckAuthorizedService mockCheckAuthorizedService;

    private FollowCountRequest testRequest;
    private FollowCountResponse expectedResponse;

    */
/**
     * Create a FollowCountPresenter spy that uses a mock service to return known responses to
     * requests.
     *//*

    @BeforeEach
    public void setup() throws Exception {
        String userAlias = "FirstNameLastName";

        FollowCount followCount = new FollowCount(userAlias);
        followCount.setNumFollowing(5);
        followCount.setNumFollowers(6);

        // Setup request objects to use in the tests
        testRequest = new FollowCountRequest(userAlias);
        AuthToken authToken = new AuthToken();
        testRequest.setAuthToken(authToken);
        expectedResponse = new FollowCountResponse(followCount);

        mockFollowCountService = Mockito.mock(FollowCountProxyService.class);
        Mockito.when(mockFollowCountService.getFollowCount(testRequest)).thenReturn(expectedResponse);

        mockCheckAuthorizedService = Mockito.mock(CheckAuthorizedService.class);
        Mockito.when(mockCheckAuthorizedService.checkAuthorized(Mockito.any(SignOutRequest.class))).thenReturn(new SignOutResponse());

        // setup the presenter we're testing, and assign it to use the mock
        spyPresenter = Mockito.spy(new FollowCountPresenter(new FollowCountPresenter.View() {
        }));
        Mockito.when(spyPresenter.getCountService()).thenReturn(mockFollowCountService);
        Mockito.when(spyPresenter.getCheckAuthorizedService()).thenReturn(mockCheckAuthorizedService);

    }

    */
/*
     * Tests if the presenter returns the same response as the service
     *//*

    @Test
    public void testGetCount_returnsServiceResult() throws Exception {
        // Assert that the presenter returns the same response as the service (it doesn't do
        // anything else, so there's nothing else to test).
        FollowCountResponse actualResponse = spyPresenter.getCount(testRequest);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    */
/*
     * Tests if the presenters throws the same error as the service
     *//*

    @Test
    public void testGetFollowCount_serviceThrowsIOException_presenterThrowsIOException() throws AssertionError, IOException, TweeterRemoteException {
        Mockito.when(mockFollowCountService.getFollowCount(testRequest)).thenThrow(new AssertionError());
        Assertions.assertThrows(AssertionError.class, () -> spyPresenter.getCount(testRequest));
    }
}
*/
