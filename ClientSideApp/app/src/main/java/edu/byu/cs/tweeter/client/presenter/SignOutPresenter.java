package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.SignOutProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

/*
 * The presenter to sign out a user
 */
public class SignOutPresenter extends Presenter {

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public SignOutPresenter(View view) {
        super(view);
    }

    /**
     * Returns SingOutResponse (contains isSuccess boolean)
     * @param request contains the data required to fulfill the request.
     * @return SignOutResponse.
     */
    public SignOutResponse signOut(LogoutRequest request) throws IOException, TweeterRemoteException {
        return getSignOutService().signOut(request);
    }

    /**
     * Returns an instance of {@link SignOutProxyService}. Allows mocking of the SignOutService class
     * for testing purposes. All usages of SignOutService should get their SignOutService
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    public SignOutProxyService getSignOutService() {
        return new SignOutProxyService();
    }

}
