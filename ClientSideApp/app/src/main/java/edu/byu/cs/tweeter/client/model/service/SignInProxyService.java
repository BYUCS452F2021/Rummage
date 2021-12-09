package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.LoginService;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.shared.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

/*
 * Contains the business login for signing in a user
 */
public class SignInProxyService extends LoginProxyService implements LoginService {
    public static final String LOG_TAG = "SIGN_IN_SERVICE";

    @Override
    public LoginResponse doLoginOperation(LoginRequest request) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        return serverFacade.signIn(request, "/signin");
    }
}
