package edu.byu.cs.tweeter.shared.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowingFollowersResponse;

/**
 * Contains the business logic for getting the next page of
 * followees or followers for a specified follower (for the Following and Followers tabs in UI, respectfully.
 */
public interface FollowingFollowerService {

    /*
     * Returns the followees or followers associated with a user alias
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees or followers.
     */
    FollowingFollowersResponse getFollowingFollowers(FollowingFollowersRequest request) throws Exception;
}
