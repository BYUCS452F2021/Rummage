package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.presenter.Presenter;
import edu.byu.cs.tweeter.client.presenter.SignOutPresenter;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.client.model.service.SignOutProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignOutPresenterTest {

    private SignOutPresenter spySignOutPresenter;
    private SignOutProxyService mockSignOutService;

    private SignOutRequest request;
    private SignOutResponse response;

    /**
     * Create a SignOutPresenter spy that uses a mock service to return known responses to
     * requests.
     */
    @BeforeEach
    void setUp() {
        spySignOutPresenter = Mockito.spy(new SignOutPresenter(new Presenter.View() {
        }));
        mockSignOutService = Mockito.mock(SignOutProxyService.class);
        Mockito.when(spySignOutPresenter.getSignOutService()).thenReturn(mockSignOutService);

        response = new SignOutResponse(true);
        request = new SignOutRequest(new AuthToken());
    }

    /*
     * Tests if the presenter returns the same response as the service when signing up
     */
    @Test
    void testSignOut_returnsServiceResult() throws IOException, TweeterRemoteException {
        Mockito.when(mockSignOutService.signOut(request)).thenReturn(response);
        SignOutResponse actual = spySignOutPresenter.signOut(request);
        assertEquals(response, actual);
    }

    // TODO
    // we would add a test for throwing an exception,
    // but with dummy data it doesn't make sense to throw any exceptions for post yet

}
