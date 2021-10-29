package edu.byu.cs.tweeter.client.presenter;
/*

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.CheckAuthorizedProxyService;
import edu.byu.cs.tweeter.client.model.service.FollowingFollowerProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.CheckAuthorizedService;
import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowingFollowersResponse;

*/
/**
 * The presenter for the "following" or "follower" functionality of the application.
 */

public class FollowingFollowerPresenter extends Presenter {


/*
    */
/**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */

    public FollowingFollowerPresenter(View view) { super(view); }


/**
     * Returns the users that the user specified in the request is following (or the users that are following
     * the specified user). Uses information in the request object to limit the number of followees/followers
     * returned and to return the next set of followees/followers after any that were returned in a previous request.
     * @param request contains the data required to fulfill the request.
     * @return the followees/followers.
     *//*

    public FollowingFollowersResponse getFollowList(FollowingFollowersRequest request) throws Exception {
        getCheckAuthorizedService().checkAuthorized(new SignOutRequest(request.getAuthToken()));
        FollowingFollowerProxyService followingFollowerService = getFollowingService();
        return followingFollowerService.getFollowingFollowers(request);
    }

    */
/**
     * Returns an instance of {@link FollowingFollowerProxyService}. Allows mocking of the FollowingService class
     * for testing purposes. All usages of FollowingService should get their FollowingService
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     *//*

    public FollowingFollowerProxyService getFollowingService() {
        return new FollowingFollowerProxyService();
    }*/
}

