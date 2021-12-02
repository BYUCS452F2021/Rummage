package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;
import java.net.MalformedURLException;

import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.client.util.ByteArrayUtils;

/**
 * Contains the business logic to support the login operations.
 */
public abstract class LoginProxyService extends ProxyService {

    public LoginResponse login(LoginRequest request) throws IOException, TweeterRemoteException {
        LoginResponse loginResponse;

        loginResponse = doLoginOperation(request);

        /*if (loginResponse.isSuccess()) {
            try {
                loadImage(loginResponse.getUser());
            } catch (MalformedURLException e) {
                throw new IOException("Image could not be loaded on login.");
            }
        }*/

        return loginResponse;
    }

    /* *
     * Loads the profile image data for the user.
     *
     * @param user the user whose profile image data is to be loaded.
     */
    /*private void loadImage(User user) throws IOException {
        byte[] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
        user.setImageBytes(bytes);
    }*/

    public abstract LoginResponse doLoginOperation(LoginRequest request) throws IOException, TweeterRemoteException;

}
