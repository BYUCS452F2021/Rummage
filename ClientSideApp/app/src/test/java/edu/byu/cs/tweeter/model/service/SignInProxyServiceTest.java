package edu.byu.cs.tweeter.model.service;

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

class SignInProxyServiceTest {

    private SignInProxyService spySignInService;
    private ServerFacade mockServerFacade;

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
        spySignInService = Mockito.spy(new SignInProxyService());
        mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(spySignInService.getServerFacade()).thenReturn(mockServerFacade);

        validRequest = new SignInRequest("testAlias", "testPass");
        invalidRequest = new SignInRequest("testAlias", "testPass");

        User user1 = new User("FirstName1", "LastName1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        successResponse = new LoginResponse(user1, new AuthToken());
        failResponse = new LoginResponse("something went wrong");
        Mockito.when(mockServerFacade.signIn(validRequest, "/signin")).thenReturn(successResponse);
        Mockito.when(mockServerFacade.signIn(invalidRequest, "/signin")).thenReturn(failResponse);
    }

    /**
     * Verify that for successful requests the {@link SignInProxyService#doLoginOperation(SignInRequest)} (SignInRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    void testSignIn_whenValidRequest_thenValidResponse() throws IOException, TweeterRemoteException {
        LoginResponse actual = spySignInService.login(validRequest);
        assertEquals(successResponse, actual);
        Mockito.verify(mockServerFacade).signIn(validRequest, "/signin");
    }

    /**
     * Verify that for failed requests the {@link SignInProxyService#doLoginOperation(SignInRequest)} (SignInRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    void testSignIn_whenInvalidRequest_thenInvalidResponse() throws IOException, TweeterRemoteException {
        LoginResponse actual = spySignInService.login(invalidRequest);
        assertEquals(failResponse, actual);
        Mockito.verify(mockServerFacade).signIn(invalidRequest, "/signin");
    }

}
