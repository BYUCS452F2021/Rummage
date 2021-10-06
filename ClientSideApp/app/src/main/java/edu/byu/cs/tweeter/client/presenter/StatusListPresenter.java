package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;


import edu.byu.cs.tweeter.client.model.service.StatusListProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;

/**
 * The presenter for the "statuslist" functionality of the application.
 */
public class StatusListPresenter extends Presenter {

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public StatusListPresenter(View view) {
        //this.view = view;
        super(view);
    }


    /**
     * Returns the statuses for all users that a user specified in the request is following.
     * Uses information in the request object to limit the number of statuses returned
     * and to return the next set of statuses after any that were returned in a previous request.
     *
     * @param request contains the data required to fulfill the request.
     * @return the statuses.
     */
    public StatusListResponse getStatusList(StatusListRequest request) throws Exception {
        getCheckAuthorizedService().checkAuthorized(new SignOutRequest(request.getAuthToken()));
        StatusListProxyService statusListService = getStatusListService();
        return statusListService.getStatuses(request);
    }

    /**
     * Returns an instance of {@link StatusListProxyService}. Allows mocking of the StatusListService class
     * for testing purposes. All usages of StatusListService should get their StatusListService
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    public StatusListProxyService getStatusListService() {
        return new StatusListProxyService();
    }
}
