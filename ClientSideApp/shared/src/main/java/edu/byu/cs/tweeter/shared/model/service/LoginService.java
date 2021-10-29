package edu.byu.cs.tweeter.shared.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

/*
 * Contains the business logic for logging in a user with a given user alias
 */
public interface LoginService {

    /*
     * Signs in a user given their user alias and password
     *
     * @param request contains the data required to fulfill the request.
     * @return the logged in user and their auth token
     */
    LoginResponse doLoginOperation(LoginRequest request) throws Exception;
}
