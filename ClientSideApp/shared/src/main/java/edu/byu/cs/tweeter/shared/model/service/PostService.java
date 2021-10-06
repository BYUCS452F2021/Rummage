package edu.byu.cs.tweeter.shared.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.response.PostResponse;

/*
 * Contains the business logic for posting a new status
 */
public interface PostService {

    /*
     * Creates a new status post
     *
     * @param request contains the data required to fulfill the request.
     * @return the post message and a boolean indicating success or failure
     */
    PostResponse post(PostRequest request) throws Exception;
}
