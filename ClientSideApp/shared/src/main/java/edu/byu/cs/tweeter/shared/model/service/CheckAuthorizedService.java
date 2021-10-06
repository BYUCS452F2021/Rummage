package edu.byu.cs.tweeter.shared.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

public interface CheckAuthorizedService {
    /**
     *
     * @param signOutRequest contains the same thing needed for here
     * @return the sign out response contains the same things needed for here
     * @throws IOException
     * @throws TweeterRemoteException
     */
    SignOutResponse checkAuthorized(SignOutRequest signOutRequest) throws Exception;
}
