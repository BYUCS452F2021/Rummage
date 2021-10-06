package edu.byu.cs.tweeter.model.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.RelationshipChangeProxyService;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;

public class RelationshipChangeIntegrationTest {
    RelationshipChangeRequest validFollowRequest;
    RelationshipChangeRequest validUnfollowRequest;

    RelationshipChangeResponse expectedFollowResponse;
    RelationshipChangeResponse expectedUnfollowResponse;

    RelationshipChangeProxyService relationshipChangeService;

    /**
     * Create a RelationshipChangeService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    public void setup() {
        String userAlias = "@testIntegrationRelChange";
        String otherUserAlias = "@otherUser";

        AuthToken authToken = new AuthToken();

        // Setup request objects to use in the tests
        validFollowRequest = new RelationshipChangeRequest(userAlias, otherUserAlias, false);
        validUnfollowRequest = new RelationshipChangeRequest(userAlias, otherUserAlias, true);

        // Setup a mock ServerFacade that will return known responses
        expectedFollowResponse = new RelationshipChangeResponse(authToken,  true);
        expectedUnfollowResponse = new RelationshipChangeResponse(authToken, false);

        relationshipChangeService = new RelationshipChangeProxyService();
    }

    /**
     * Verify that for successful requests the {@link RelationshipChangeProxyService#changeRelationship(RelationshipChangeRequest)}
     * method returns the expected result.
     *
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    public void testGetUser_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        RelationshipChangeResponse response = relationshipChangeService.changeRelationship(validFollowRequest);
        Assertions.assertEquals(expectedFollowResponse.isFollowRelationship(), response.isFollowRelationship());
    }

    /**
     * Verify that for unfollow requests the {@link RelationshipChangeProxyService#changeRelationship(RelationshipChangeRequest)}
     * method returns the expected result.
     *
     * @throws IOException if an IO error occurs.
     */
    @Test
    public void testGetUser_invalidRequest_returnsNoUser() throws IOException, TweeterRemoteException {
        RelationshipChangeResponse response = relationshipChangeService.changeRelationship(validUnfollowRequest);
        Assertions.assertEquals(expectedUnfollowResponse.isFollowRelationship(), response.isFollowRelationship());
    }

}
