package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.UserProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.request.UserRequest;
import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;

/**
 * Contains the business logic for getting the user associated with a given user alias
 */
public class UserPresenter extends Presenter {

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public UserPresenter(View view) { super(view); }

    /**
     * Returns the user associated with a given user alias
     * @param request contains the data required to fulfill the request.
     * @return the followees/followers.
     */
    public UserResponse getUser(UserRequest request) throws Exception {
        getCheckAuthorizedService().checkAuthorized(new SignOutRequest(request.getAuthToken()));
        return getUserService().getUser(request);
    }

    /**
     * Returns an instance of {@link UserProxyService}. Allows mocking of the UserService class
     * for testing purposes. All usages of UserService should get their UserService
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    public UserProxyService getUserService() {
        return new UserProxyService();
    }

}
