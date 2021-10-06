package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.CheckAuthorizedService;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

public class CheckAuthorizedProxyService extends ProxyService implements CheckAuthorizedService {
    @Override
    public SignOutResponse checkAuthorized(SignOutRequest signOutRequest) throws IOException, TweeterRemoteException {
        SignOutResponse signOutResponse;

        signOutResponse = getServerFacade().checkAuthorized(signOutRequest, "/checkauthorized");

        if(!signOutResponse.isSuccess()) {
            throw new AssertionError("Unauthorized");
        }

        return signOutResponse;
    }
}
