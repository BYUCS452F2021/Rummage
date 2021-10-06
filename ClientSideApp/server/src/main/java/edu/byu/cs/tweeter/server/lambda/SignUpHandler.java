package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.server.service.SignUpServiceImpl;
import edu.byu.cs.tweeter.shared.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

/**
 * An AWS lambda function to sign up a user
 */
public class SignUpHandler implements RequestHandler<SignUpRequest, LoginResponse> {

    @Override
    public LoginResponse handleRequest(SignUpRequest signUpRequest, Context context) {
        SignUpServiceImpl signUpService = new SignUpServiceImpl();
        try {
            return signUpService.doLoginOperation(signUpRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResponse(e.getMessage());
        }

    }

}
