package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.server.dao.FollowDAO;
import edu.byu.cs.tweeter.shared.model.service.RelationshipChangeService;
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;

/**
 * Contains the business logic for updating the relationship between two users.
 */
public class RelationshipChangeServiceImpl implements RelationshipChangeService {

    @Override
    public RelationshipChangeResponse changeRelationship(RelationshipChangeRequest relationshipChangeRequest) {
        return getFollowDAO().changeRelationship(relationshipChangeRequest);
    }

    /**
     * Returns an instance, helps with mocking.
     *
     * @return the DAO instance
     */
    FollowDAO getFollowDAO() {
        return new FollowDAO();
    }

}
