package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.LoginService;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

/*
 * Contains the business logic for signing up a user
 */
public class SignUpProxyService extends LoginProxyService implements LoginService {

    public static final String LOG_TAG = "SIGN_UP_SERVICE";

    @Override
    public LoginResponse doLoginOperation(SignInRequest request) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        return serverFacade.signUp((SignUpRequest) request, "/signup");
    }

}
