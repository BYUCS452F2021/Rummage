package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.client.util.ByteArrayUtils;
import edu.byu.cs.tweeter.shared.model.domain.Status;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.FollowingFollowerService;
import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowingFollowersResponse;
import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;

/**
 * Contains the business logic for getting the users a user is following.
 */
public class FollowingFollowerProxyService extends ProxyService implements FollowingFollowerService {

    @Override
    public FollowingFollowersResponse getFollowingFollowers(FollowingFollowersRequest followsRequest) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();

        FollowingFollowersResponse followingFollowers = serverFacade.getFollowingFollowers(followsRequest, "/getfollowingfollowers");

        if(followingFollowers.isSuccess()) {
            loadImages(followingFollowers);
        }

        return followingFollowers;
    }

    /**
     * Loads the profile image data for each status included in the response.
     *
     * @param response the response from the followee request.
     */
    private void loadImages(FollowingFollowersResponse response) throws IOException {
        for(User user : response.getFollowees()) {
            byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
            user.setImageBytes(bytes);
        }
    }
}
