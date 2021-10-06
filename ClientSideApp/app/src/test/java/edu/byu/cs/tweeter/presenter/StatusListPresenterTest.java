package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

import edu.byu.cs.tweeter.client.presenter.StatusListPresenter;
import edu.byu.cs.tweeter.shared.model.domain.Status;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.service.StatusListProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.CheckAuthorizedService;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;

public class StatusListPresenterTest {
    private StatusListPresenter spyPresenter;
    private StatusListProxyService mockStatusListService;
    private CheckAuthorizedService mockCheckAuthorizedService;

    private StatusListRequest testRequest;
    private StatusListResponse expectedResponse;

    /**
     * Create a StatusListPresenter spy that uses a mock service to return known responses to
     * requests.
     */
    @BeforeEach
    public void setup() throws Exception {
        User currentUser = new User("FirstName", "LastName", null);

        Status resultStatus1 = new Status("@AllenAnderson https://google.com food https://www.msn.com", currentUser, ZonedDateTime.now(ZoneId.of("UTC+0")));
        Status resultStatus2 = new Status("Post message", currentUser, ZonedDateTime.now(ZoneId.of("UTC+0")));
        Status resultStatus3 = new Status("https://byu.edu Post message", currentUser, ZonedDateTime.now(ZoneId.of("UTC+0")));

        // Setup request objects to use in the tests
        testRequest = new StatusListRequest(currentUser.getAlias(), 2, null, false);
        expectedResponse = new StatusListResponse(Arrays.asList(resultStatus1, resultStatus2, resultStatus3), false);

        mockStatusListService = Mockito.mock(StatusListProxyService.class);
        Mockito.when(mockStatusListService.getStatuses(testRequest)).thenReturn(expectedResponse);

        mockCheckAuthorizedService = Mockito.mock(CheckAuthorizedService.class);
        Mockito.when(mockCheckAuthorizedService.checkAuthorized(Mockito.any(SignOutRequest.class))).thenReturn(new SignOutResponse());

        // setup the presenter we're testing, and assign it to use the mock
        spyPresenter = Mockito.spy(new StatusListPresenter(new StatusListPresenter.View() {
        }));
        Mockito.when(spyPresenter.getStatusListService()).thenReturn(mockStatusListService);
        Mockito.when(spyPresenter.getCheckAuthorizedService()).thenReturn(mockCheckAuthorizedService);
    }

    /*
     * Tests if the presenter returns the same response as the service
     */
    @Test
    public void testGetStatusList_returnsServiceResult() throws Exception {
        // Assert that the presenter returns the same response as the service (it doesn't do
        // anything else, so there's nothing else to test).
        StatusListResponse actualResponse = spyPresenter.getStatusList(testRequest);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    /*
     * Tests if the presenter throws the same error as the service
     */
    @Test
    public void testGetStatusList_serviceThrowsIOException_presenterThrowsIOException() throws IOException, TweeterRemoteException {
        Mockito.when(mockStatusListService.getStatuses(testRequest)).thenThrow(new IOException());
        Assertions.assertThrows(IOException.class, () -> spyPresenter.getStatusList(testRequest));
    }
}
