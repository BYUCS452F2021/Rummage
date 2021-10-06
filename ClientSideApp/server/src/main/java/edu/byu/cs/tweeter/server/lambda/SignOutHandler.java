package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.IOException;

import edu.byu.cs.tweeter.server.service.SignOutServiceImpl;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.SignOutService;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

/**
 * An AWS lambda function to sign out a user
 */
public class SignOutHandler implements RequestHandler<SignOutRequest, SignOutResponse> {

    @Override
    public SignOutResponse handleRequest(SignOutRequest signOutRequest, Context context) {
        SignOutService signOutService = new SignOutServiceImpl();
        try {
            return signOutService.signOut(signOutRequest);
        } catch (IOException | TweeterRemoteException e) {
            e.printStackTrace();
            return new SignOutResponse(e.getMessage());
        }
    }

}
