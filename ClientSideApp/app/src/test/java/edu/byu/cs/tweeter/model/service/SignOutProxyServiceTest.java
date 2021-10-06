package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.client.model.service.SignOutProxyService;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignOutProxyServiceTest {

    private SignOutProxyService spySignOutService;
    private ServerFacade mockServerFacade;

    private SignOutRequest validRequest;
    private SignOutRequest invalidRequest;

    private SignOutResponse successResponse;
    private SignOutResponse failResponse;

    /**
     * Create a SignOutService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    void setUp() throws IOException, TweeterRemoteException {
        spySignOutService = Mockito.spy(new SignOutProxyService());
        mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(spySignOutService.getServerFacade()).thenReturn(mockServerFacade);

        validRequest = new SignOutRequest(new AuthToken());
        invalidRequest = new SignOutRequest(new AuthToken());
        successResponse = new SignOutResponse(true);
        failResponse = new SignOutResponse(false);
        Mockito.when(mockServerFacade.signOut(validRequest, "/signout")).thenReturn(successResponse);
        Mockito.when(mockServerFacade.signOut(invalidRequest, "/signout")).thenReturn(failResponse);
    }

    /**
     * Verify that for successful requests the {@link SignOutProxyService#signOut(SignOutRequest)} (SignOutRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    void testSignOut_whenValidRequest_thenValidResponse() throws IOException, TweeterRemoteException {
        SignOutResponse actual = spySignOutService.signOut(validRequest);
        assertEquals(successResponse, actual);
        Mockito.verify(mockServerFacade).signOut(validRequest, "/signout");
    }

    /**
     * Verify that for failed requests the {@link SignOutProxyService#signOut(SignOutRequest)} (SignOutRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    void testSignOut_whenInvalidRequest_thenInvalidResponse() throws IOException, TweeterRemoteException {
        SignOutResponse actual = spySignOutService.signOut(invalidRequest);
        assertEquals(failResponse, actual);
        Mockito.verify(mockServerFacade).signOut(invalidRequest, "/signout");
    }

}