package edu.byu.cs.tweeter.shared.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

/*
 * Contains the business logic for signing out  an authenticated user.
 */
public interface SignOutService {

    /*
     * Signs our a user given a user alias and returns boolean indicating success or failure
     *
     * @param followCountRequest contains the data required to fulfill the request.
     * @return a boolean indicating success/failure
     */
    SignOutResponse signOut(LogoutRequest request) throws IOException, TweeterRemoteException;
}
