package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.presenter.LoginPresenter;
import edu.byu.cs.tweeter.client.presenter.Presenter;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.service.LoginProxyService;
import edu.byu.cs.tweeter.client.model.service.SignInProxyService;
import edu.byu.cs.tweeter.client.model.service.SignUpProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginPresenterTest {

    private LoginPresenter spyLoginPresenter;
    private LoginProxyService mockSignInService;
    private LoginProxyService mockSignUpService;

    private SignInRequest testSignInRequest;
    private SignInRequest testSignUpRequest;
    private LoginResponse expectedResponse;

    /**
     * Create a LoginPresenter spy that uses a mock service to return known responses to
     * requests.
     */
    @BeforeEach
    void setUp() throws IOException, TweeterRemoteException {
        testSignInRequest = new SignInRequest("alias", "pass");
        testSignUpRequest = new SignUpRequest("alias", "pass", "name", "name", null);
        expectedResponse = new LoginResponse(new User("name", "name", null), new AuthToken());


        mockSignInService = Mockito.mock(SignInProxyService.class);
        Mockito.when(mockSignInService.login(testSignInRequest)).thenReturn(expectedResponse);
        mockSignUpService = Mockito.mock(SignUpProxyService.class);
        Mockito.when(mockSignUpService.login(testSignUpRequest)).thenReturn(expectedResponse);


        spyLoginPresenter = Mockito.spy(new LoginPresenter(new Presenter.View() {
        }));
        Mockito.when(spyLoginPresenter.getLoginService(testSignInRequest)).thenReturn(mockSignInService);
        Mockito.when(spyLoginPresenter.getLoginService(testSignUpRequest)).thenReturn(mockSignUpService);

    }

    /*
     * Tests if the presenter returns the same response as the service when signing in
     */
    @Test
    void testSignIn_returnsServiceResult() throws IOException, TweeterRemoteException {
        LoginResponse actual = spyLoginPresenter.login(testSignInRequest);
        assertEquals(expectedResponse, actual);
    }

    /*
     * Tests if the presenter returns the same response as the service when signing up
     */
    @Test
    void testSignUp_returnsServiceResult() throws IOException, TweeterRemoteException {
        LoginResponse actual = spyLoginPresenter.login(testSignUpRequest);
        assertEquals(expectedResponse, actual);
    }

    /*
     * Tests if the presenters throws the same error as the service when signing in
     */
    @Test
    public void testSignInServiceThrows_thenPresenterThrows() throws IOException, TweeterRemoteException {
        Mockito.when(mockSignInService.login(testSignInRequest)).thenThrow(new IOException());
        Assertions.assertThrows(IOException.class, () -> spyLoginPresenter.login(testSignInRequest));
    }

    /*
     * Tests if the presenters throws the same error as the service when signing up
     */
    @Test
    public void testSignUpServiceThrows_thenPresenterThrows() throws IOException, TweeterRemoteException {
        Mockito.when(mockSignUpService.login(testSignUpRequest)).thenThrow(new IOException());
        Assertions.assertThrows(IOException.class, () -> spyLoginPresenter.login(testSignUpRequest));
    }
}
