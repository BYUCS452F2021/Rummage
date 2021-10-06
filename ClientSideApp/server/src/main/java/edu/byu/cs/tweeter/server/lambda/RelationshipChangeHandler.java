package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.IOException;

import edu.byu.cs.tweeter.server.service.RelationshipChangeServiceImpl;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.RelationshipChangeService;
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;

/**
 * An AWS lambda function to update the relationship between two users
 */
public class RelationshipChangeHandler implements RequestHandler<RelationshipChangeRequest, RelationshipChangeResponse> {

    @Override
    public RelationshipChangeResponse handleRequest(RelationshipChangeRequest relationshipChangeRequest, Context context) {
        RelationshipChangeService relationshipChangeService = new RelationshipChangeServiceImpl();
        try {
            return relationshipChangeService.changeRelationship(relationshipChangeRequest);
        } catch (IOException | TweeterRemoteException e) {
            e.printStackTrace();
            return new RelationshipChangeResponse(e.getMessage());
        }
    }

}
