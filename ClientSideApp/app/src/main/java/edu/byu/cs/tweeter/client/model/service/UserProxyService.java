package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;
import java.net.MalformedURLException;

import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.UserService;
import edu.byu.cs.tweeter.shared.model.service.request.UserRequest;
import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;
import edu.byu.cs.tweeter.client.util.ByteArrayUtils;

/**
 * Contains the business logic for getting the user associated with a given user alias.
 */
public class UserProxyService extends ProxyService implements UserService {

    /*
     * Returns the user associated with a given user alias.
     *
     * @param followCountRequest contains the data required to fulfill the request.
     * @return the number of followees and followers.
     */
    public UserResponse getUser(UserRequest userRequest) throws IOException, TweeterRemoteException {
        UserResponse userResponse;


        userResponse = getServerFacade().getUser(userRequest, "/getuserview");

        if(!userResponse.isSuccess()) {
            throw new AssertionError();
        }
        else {
            try {
                loadImage(userResponse.getUser());
            } catch (MalformedURLException e) {
                throw new IOException("Image could not be loaded.");
            }
        }

        return userResponse;
    }

    /**
     * Loads the profile image data for the user.
     *
     * @param user the user whose profile image data is to be loaded.
     */
    private void loadImage(User user) throws IOException {
        byte[] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
        user.setImageBytes(bytes);
    }
}
