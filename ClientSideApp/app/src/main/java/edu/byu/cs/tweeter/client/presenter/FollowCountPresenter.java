package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.CheckAuthorizedProxyService;
import edu.byu.cs.tweeter.client.model.service.FollowCountProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.CheckAuthorizedService;
import edu.byu.cs.tweeter.shared.model.service.request.FollowCountRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;

/**
 * The presenter to get the number followees and number following with a given user alias
 */
public class FollowCountPresenter extends Presenter {

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public FollowCountPresenter(View view) { super(view); }

    /**
     * Returns FollowCount (contain the num followees and followers) associated
     * with a specific user alias
     * @param request contains the data required to fulfill the request.
     * @return FollowCount.
     */
    public FollowCountResponse getCount(FollowCountRequest request) throws Exception {
        getCheckAuthorizedService().checkAuthorized(new SignOutRequest(request.getAuthToken()));
        return getCountService().getFollowCount(request);
    }

    /**
     * Returns an instance of {@link FollowCountProxyService}. Allows mocking of the FollowCountService class
     * for testing purposes. All usages of FollowCountService should get their FollowCountService
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    public FollowCountProxyService getCountService() {
        return new FollowCountProxyService();
    }
}
