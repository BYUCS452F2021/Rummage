package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.PostService;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.response.PostResponse;

/*
 * Contains the business logic to create a new status post
 */
public class PostProxyService extends ProxyService implements PostService {

    public PostResponse post(PostRequest request) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        return serverFacade.post(request, "/poststatus");
    }

}
