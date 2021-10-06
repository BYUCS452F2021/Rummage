package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.client.model.service.SignUpProxyService;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignUpProxyServiceTest {

    private SignUpProxyService spySignUpService;
    private ServerFacade mockServerFacade;

    private SignUpRequest validRequest;
    private SignUpRequest invalidRequest;

    private LoginResponse successResponse;
    private LoginResponse failResponse;

    /**
     * Create a SignUpService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    void setUp() throws IOException, TweeterRemoteException {
        spySignUpService = Mockito.spy(new SignUpProxyService());
        mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(spySignUpService.getServerFacade()).thenReturn(mockServerFacade);

        validRequest = new SignUpRequest("testAlias", "testPass", "name", "surname", null);
        invalidRequest = new SignUpRequest("testAlias", "testPass", "bad", "bad", null);

        User user1 = new User("FirstName1", "LastName1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        successResponse = new LoginResponse(user1, new AuthToken());
        failResponse = new LoginResponse("something went wrong");
        Mockito.when(mockServerFacade.signUp(validRequest, "/signup")).thenReturn(successResponse);
        Mockito.when(mockServerFacade.signUp(invalidRequest, "/signup")).thenReturn(failResponse);
    }

    /**
     * Verify that for successful requests the {@link SignUpProxyService#doLoginOperation(SignInRequest)} (SignInRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    void testSignup_whenValidRequest_thenValidResponse() throws IOException, TweeterRemoteException {
        LoginResponse actual = spySignUpService.login(validRequest);
        assertEquals(successResponse, actual);
        Mockito.verify(mockServerFacade).signUp(validRequest, "/signup");
    }

    /**
     * Verify that for failed requests the {@link SignUpProxyService#doLoginOperation(SignInRequest)} (SignInRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    void testSignup_whenInvalidRequest_thenInvalidResponse() throws IOException, TweeterRemoteException {
        LoginResponse actual = spySignUpService.login(invalidRequest);
        assertEquals(failResponse, actual);
        Mockito.verify(mockServerFacade).signUp(invalidRequest, "/signup");
    }

}
