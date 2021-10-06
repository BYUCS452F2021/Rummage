package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.RelationshipChangeService;
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;

/**
 * Contains the business logic for updating the relationship between two users.
 */
public class RelationshipChangeProxyService extends ProxyService implements RelationshipChangeService {

    /*
     * Returns the number of users who follow a user given a user alias and the
     * number users who follow the given user.
     *
     * @param relationshipChangeRequest contains the data required to fulfill the request.
     * @return relationshipChangeResponse (authToken, currUserAlias, changedRelationshipStatus)
     */
    public RelationshipChangeResponse changeRelationship(RelationshipChangeRequest relationshipChangeRequest) throws IOException, TweeterRemoteException {
        RelationshipChangeResponse relationshipChangeResponse;

        relationshipChangeResponse = getServerFacade().changeRelationship(relationshipChangeRequest, "/changerelationship");

        if(!relationshipChangeResponse.isSuccess()) {
            throw new AssertionError();
        }

        return relationshipChangeResponse;
    }
}
