package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.RelationshipChangeProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;

/**
 * The presenter to get change the relationship between the current user
 * and another user
 */
public class RelationshipChangePresenter extends Presenter {

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public RelationshipChangePresenter(View view) { super(view); }

    /**
     * Returns RelationshipChangeResponse (contain the AuthToken, currUserAias,
     * and changedRelationshipStatus)
     * @param request contains the data required to fulfill the request.
     * @return RelationshipChangeResponse.
     */
    public RelationshipChangeResponse changeRelationship(RelationshipChangeRequest request) throws Exception {
        getCheckAuthorizedService().checkAuthorized(new SignOutRequest(request.getAuthToken()));
        return getRelationshipChangeService().changeRelationship(request);
    }

    /**
     * Returns an instance of {@link RelationshipChangeProxyService}. Allows mocking of the RelationshipChangeService class
     * for testing purposes. All usages of RelationshipChangeService should get their RelationshipChangeService
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    public RelationshipChangeProxyService getRelationshipChangeService() {
        return new RelationshipChangeProxyService();
    }
}
