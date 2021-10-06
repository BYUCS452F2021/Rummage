package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.presenter.RelationshipChangePresenter;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.client.model.service.RelationshipChangeProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.CheckAuthorizedService;
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

public class RelationshipChangePresenterTest {
    private RelationshipChangePresenter spyPresenter;
    private RelationshipChangeProxyService mockRelationshipChangeService;
    private CheckAuthorizedService mockCheckAuthorizedService;

    private RelationshipChangeRequest testRequest;
    private RelationshipChangeResponse expectedResponse;

    /**
     * Create a RelationshipChangePresenter spy that uses a mock service to return known responses to
     * requests.
     */
    @BeforeEach
    public void setup() throws Exception {
        String userAlias = "FirstNameLastName";
        String otherUserAlias = "NoNameUser";

        AuthToken authToken = new AuthToken();

        // Setup request objects to use in the tests
        testRequest = new RelationshipChangeRequest(userAlias, otherUserAlias, true);
        expectedResponse = new RelationshipChangeResponse(authToken, false);

        mockRelationshipChangeService = Mockito.mock(RelationshipChangeProxyService.class);
        Mockito.when(mockRelationshipChangeService.changeRelationship(testRequest)).thenReturn(expectedResponse);

        mockCheckAuthorizedService = Mockito.mock(CheckAuthorizedService.class);
        Mockito.when(mockCheckAuthorizedService.checkAuthorized(Mockito.any(SignOutRequest.class))).thenReturn(new SignOutResponse());

        // setup the presenter we're testing, and assign it to use the mock
        spyPresenter = Mockito.spy(new RelationshipChangePresenter(new RelationshipChangePresenter.View() {
        }));
        Mockito.when(spyPresenter.getRelationshipChangeService()).thenReturn(mockRelationshipChangeService);
        Mockito.when(spyPresenter.getCheckAuthorizedService()).thenReturn(mockCheckAuthorizedService);
    }

    /*
     * Tests if the presenter returns the same response as the service
     */
    @Test
    public void testGetUser_returnsServiceResult() throws Exception {
        // Assert that the presenter returns the same response as the service (it doesn't do
        // anything else, so there's nothing else to test).
        RelationshipChangeResponse actualResponse = spyPresenter.changeRelationship(testRequest);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }
}
