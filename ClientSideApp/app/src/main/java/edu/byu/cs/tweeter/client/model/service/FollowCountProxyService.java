/*
package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
*/
/*
import edu.byu.cs.tweeter.shared.model.service.FollowCountService;
*//*

import edu.byu.cs.tweeter.shared.model.service.request.FollowCountRequest;
*/
/*
import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;
*//*


*/
/**
 * Contains the business logic for getting the number of followees
 * and followers for the given userAlias.
 *//*

public class FollowCountProxyService extends ProxyService implements FollowCountService {

    */
/**
     * Returns the number of users who follow a user given a user alias and the
     * number users who follow the given user.
     *
     * @param followCountRequest contains the data required to fulfill the request.
     * @return the number of followees and followers.
     *//*

    public FollowCountResponse getFollowCount(FollowCountRequest followCountRequest) throws IOException, TweeterRemoteException {
        FollowCountResponse followCountResponse;

        followCountResponse = getServerFacade().getFollowCount(followCountRequest, "/getfollowcount");

        if(!followCountResponse.isSuccess()) {
            throw new AssertionError();
        }

        return followCountResponse;
    }

}
*/
