package edu.byu.cs.tweeter.server.service;
/*
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.byu.cs.tweeter.server.dao.FollowDAO;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;


public class RelationshipChangeServiceImplTest {

    RelationshipChangeRequest validRequest;
    RelationshipChangeRequest invalidRequest;

    RelationshipChangeResponse successResponse;
    RelationshipChangeResponse failureResponse;

    RelationshipChangeServiceImpl relationshipChangeServiceImpl;

    */
/**
     * Create a RelationshipChangeServiceImpl spy that uses a mock DAO to return known responses to
     * requests.
     *//*

    @BeforeEach
    public void setup() {
        String userAlias = "FirstNameLastName";
        String otherUserAlias = "NoNameUser";

        AuthToken authToken = new AuthToken();

        // Setup request objects to use in the tests
        validRequest = new RelationshipChangeRequest(userAlias, otherUserAlias, true);
        invalidRequest = new RelationshipChangeRequest("", "", false);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new RelationshipChangeResponse(authToken, false);
        FollowDAO mockServiceRelationshipChangeDAO = Mockito.mock(FollowDAO.class);
        Mockito.when(mockServiceRelationshipChangeDAO.changeRelationship(validRequest)).thenReturn(successResponse);

        failureResponse = new RelationshipChangeResponse("An exception has occurred");
        Mockito.when(mockServiceRelationshipChangeDAO.changeRelationship(invalidRequest)).thenReturn(failureResponse);

        // Create a UserService instance and wrap it with a spy that will use the mock service
        relationshipChangeServiceImpl = Mockito.spy(new RelationshipChangeServiceImpl());
        Mockito.when(relationshipChangeServiceImpl.getFollowDAO()).thenReturn(mockServiceRelationshipChangeDAO);
    }

    */
/**
     * Verify that for successful requests the {@link RelationshipChangeServiceImpl#changeRelationship(RelationshipChangeRequest)}
     * method returns the same result as the mockDAO.
     *//*

    @Test
    public void testGetUser_validRequest_correctResponse() {
        RelationshipChangeResponse response = relationshipChangeServiceImpl.changeRelationship(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    */
/**
     * Verify that for failed requests the {@link RelationshipChangeServiceImpl#changeRelationship(RelationshipChangeRequest)}
     * method throws an exception.
     *//*

    @Test
    public void testGetUser_invalidRequest_correctResponse() {
        RelationshipChangeResponse actual = relationshipChangeServiceImpl.changeRelationship(invalidRequest);
        Assertions.assertEquals(failureResponse, actual);
    }

}
*/
