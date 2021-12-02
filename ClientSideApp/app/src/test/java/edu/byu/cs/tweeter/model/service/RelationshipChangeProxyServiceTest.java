package edu.byu.cs.tweeter.model.service;
/*

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.RelationshipChangeProxyService;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;


public class RelationshipChangeProxyServiceTest {
    RelationshipChangeRequest validRequest;
    RelationshipChangeRequest invalidRequest;

    RelationshipChangeResponse successResponse;
    RelationshipChangeResponse failureResponse;

    RelationshipChangeProxyService relationshipChangeService;

    */
/**
     * Create a RelationshipChangeService spy that uses a mock ServerFacade to return known responses to
     * requests.
     *//*

    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {
        String userAlias = "FirstNameLastName";
        String otherUserAlias = "NoNameUser";

        AuthToken authToken = new AuthToken();

        // Setup request objects to use in the tests
        validRequest = new RelationshipChangeRequest(userAlias, otherUserAlias, true);
        invalidRequest = new RelationshipChangeRequest("", "", false);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new RelationshipChangeResponse(authToken,  false);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.changeRelationship(validRequest, "/changerelationship")).thenReturn(successResponse);

        failureResponse = new RelationshipChangeResponse("An exception has occurred");
        Mockito.when(mockServerFacade.changeRelationship(invalidRequest, "/changerelationship")).thenReturn(failureResponse);

        // Create a UserService instance and wrap it with a spy that will use the mock service
        relationshipChangeService = Mockito.spy(new RelationshipChangeProxyService());
        Mockito.when(relationshipChangeService.getServerFacade()).thenReturn(mockServerFacade);
    }

    */
/**
     * Verify that for successful requests the {@link RelationshipChangeProxyService#changeRelationship(RelationshipChangeRequest)}
     * method returns the same result as the {@link ServerFacade}.
     * .
     *
     * @throws IOException if an IO error occurs.
     *//*

    @Test
    public void testGetUser_validRequest_correctResponse() throws IOException, TweeterRemoteException {
        RelationshipChangeResponse response = relationshipChangeService.changeRelationship(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    */
/**
     * Verify that for failed requests the {@link RelationshipChangeProxyService#changeRelationship(RelationshipChangeRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     *//*

    @Test
    public void testGetUser_invalidRequest_returnsNoUser() throws IOException {
        Assertions.assertThrows(AssertionError.class, () -> {
            RelationshipChangeResponse response = relationshipChangeService.changeRelationship(invalidRequest);
        });
    }
}
*/
