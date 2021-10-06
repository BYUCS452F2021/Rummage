package edu.byu.cs.tweeter.model.integration;

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

public class SignOutIntegrationTest {
    private SignOutProxyService signOutService;

    private SignOutRequest validRequest;

    private SignOutResponse successResponse;

    /**
     * Create a SignOutService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    void setUp() throws IOException, TweeterRemoteException {
        signOutService = new SignOutProxyService();

        validRequest = new SignOutRequest(new AuthToken());
        successResponse = new SignOutResponse(true);
    }

    @Test
    void testSignOut_whenValidRequest_thenValidResponse() throws IOException, TweeterRemoteException {
        SignOutResponse actual = signOutService.signOut(validRequest);
        assertEquals(successResponse.isSuccess(), actual.isSuccess());
    }
}
