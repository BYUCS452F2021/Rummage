package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.LoginProxyService;
import edu.byu.cs.tweeter.client.model.service.SignInProxyService;
import edu.byu.cs.tweeter.client.model.service.SignUpProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

/**
 * The presenter for the login functionality of the application.
 */
public class LoginPresenter extends Presenter {

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public LoginPresenter(View view) {
        super(view);
    }

    /**
     * Makes a login request.
     *
     * @param signInRequest the request.
     */
    public LoginResponse login(SignInRequest signInRequest) throws IOException, TweeterRemoteException {
        LoginProxyService loginService = getLoginService(signInRequest);
        return loginService.login(signInRequest);
    }

    /**
     * Returns an instance of {@link LoginProxyService}. Allows mocking of the LoginService class
     * for testing purposes. All usages of LoginService should get their LoginService
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    public LoginProxyService getLoginService(SignInRequest request) {
        if (request instanceof SignUpRequest) {
            return new SignUpProxyService();
        } else {
            return new SignInProxyService();
        }

    }

}
