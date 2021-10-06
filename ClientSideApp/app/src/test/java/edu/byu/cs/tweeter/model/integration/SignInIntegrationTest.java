package edu.byu.cs.tweeter.model.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.client.model.service.SignInProxyService;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignInIntegrationTest {
    private SignInProxyService signInService;

    private SignInRequest validRequest;
    private SignInRequest invalidRequest;

    private LoginResponse successResponse;
    private LoginResponse failResponse;

    /**
     * Create a SignInService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    void setUp() throws IOException, TweeterRemoteException {
        signInService = Mockito.spy(new SignInProxyService());

        validRequest = new SignInRequest("@testMickey", "password");
        invalidRequest = new SignInRequest("testAlias", "testPass");

        User user1 = new User("Mickey", "Mouse", "@testMickey",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        successResponse = new LoginResponse(user1, new AuthToken());
        failResponse = new LoginResponse("something went wrong");
    }

    @Test
    void testSignIn_whenValidRequest_thenValidResponse() throws IOException, TweeterRemoteException {
        LoginResponse actual = signInService.login(validRequest);
        assertEquals(successResponse.getUser(), actual.getUser());
    }

    //@Test
    //void testSignIn_whenInvalidRequest_thenInvalidResponse() throws IOException, TweeterRemoteException {
    //    LoginResponse actual = signInService.login(invalidRequest);
    //    assertEquals(failResponse.getUser(), actual.getUser());
    //}
}
