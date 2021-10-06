package edu.byu.cs.tweeter.shared.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.UserRequest;
import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;

/**
 * Contains the business logic for getting the user associated with a given user alias.
 */
public interface UserService {

    /*
     * Returns the user associated with a given user alias.
     *
     * @param userRequest contains the data required to fulfill the request.
     * @return the number of followees and followers.
     */
    UserResponse getUser(UserRequest userRequest) throws Exception;
}
