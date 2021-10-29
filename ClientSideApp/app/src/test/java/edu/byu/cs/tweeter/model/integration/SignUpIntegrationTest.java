package edu.byu.cs.tweeter.model.integration;
/*

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.client.model.service.SignUpProxyService;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SignUpIntegrationTest {
    private SignUpProxyService signUpService;

    private SignUpRequest validRequest;
    private SignUpRequest invalidRequest;

    private LoginResponse successResponse;
    private LoginResponse failResponse;

    */
/**
     * Create a SignUpService spy that uses a mock ServerFacade to return known responses to
     * requests.
     *//*

    @BeforeEach
    void setUp() throws IOException, TweeterRemoteException {
        signUpService = new SignUpProxyService();


        validRequest = new SignUpRequest("signUpTester", "testPass", "name", "surname", "https://faculty.cs.byu.edu" +
                "/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        invalidRequest = new SignUpRequest("@testMickey", "testPass", "bad", "bad", "https://faculty.cs.byu.edu" +
                "/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        User user1 = new User("name", "surname", "signUpTester",
                "https://faculty.cs.byu.edu" +
                        "/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        successResponse = new LoginResponse(user1, new AuthToken());
        failResponse = new LoginResponse("User already Exists");
    }

    @Test
    void testSignup_whenValidRequest_thenValidResponse() throws IOException, TweeterRemoteException {
        LoginResponse actual = signUpService.login(validRequest);
        assertEquals(successResponse.getUser(), actual.getUser());
    }

    @Test
    void testSignup_whenInvalidRequest_thenInvalidResponse() throws IOException, TweeterRemoteException {
        assertThrows(TweeterRemoteException.class, () -> {
            signUpService.login(invalidRequest);
        });
    }

    //Must run delete from server
}
*/
