package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.IOException;

import edu.byu.cs.tweeter.server.service.SignInServiceImpl;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.LoginService;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

/**
 * An AWS lambda function that logs a user in and returns the user object and an auth code for
 * a successful login.
 */
public class SignInHandler implements RequestHandler<SignInRequest, LoginResponse> {

    @Override
    public LoginResponse handleRequest(SignInRequest loginRequest, Context context) {
        LoginService signInService = new SignInServiceImpl();
        try {
            return signInService.doLoginOperation(loginRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResponse(e.getMessage());
        }
    }

}
