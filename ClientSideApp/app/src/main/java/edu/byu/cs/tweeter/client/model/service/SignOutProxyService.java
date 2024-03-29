package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.SignOutService;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.shared.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LogoutResponse;

/*
 * Contains the business logic for signing out a user
 */
public class SignOutProxyService extends ProxyService implements SignOutService {

    /*
     * Signs out a user given a SignOutRequest
     * @param request SignOutRequest
     * @ret SignOutResponse response given
     */
    public LogoutResponse signOut(LogoutRequest request) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        return serverFacade.signOut(request, "/signout");
    }

}
