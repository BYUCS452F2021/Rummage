package edu.byu.cs.tweeter.client.presenter;


import edu.byu.cs.tweeter.client.model.service.FollowedSalesProxyService;
import edu.byu.cs.tweeter.shared.model.service.request.FollowedSalesRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowedSalesResponse;

/**
 * The presenter for the "yardSalelist" functionality of the application.
 */
public class FollowedSalesPresenter extends Presenter {

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public FollowedSalesPresenter(View view) {
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
    public FollowedSalesResponse getFollowedSales(FollowedSalesRequest request) throws Exception {
/*
        getCheckAuthorizedService().checkAuthorized(new SignOutRequest(request.getAuthToken()));
*/
        FollowedSalesProxyService yardSaleProxyService = getYardSaleService();
        return yardSaleProxyService.getYardSales(request);
    }

    /**
     * Returns an instance of {@link FollowedSalesProxyService}. Allows mocking of the StatusListService class
     * for testing purposes. All usages of StatusListService should get their StatusListService
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    public FollowedSalesProxyService getYardSaleService() {
        return new FollowedSalesProxyService();
    }
}
