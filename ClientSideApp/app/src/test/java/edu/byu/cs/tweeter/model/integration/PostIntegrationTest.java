package edu.byu.cs.tweeter.model.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.client.model.service.PostProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.response.PostResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostIntegrationTest {
    private PostProxyService postService;

    private PostRequest validRequest;

    private PostResponse successResponse;

    /**
     * Create a PostService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    void setUp() throws IOException, TweeterRemoteException {
        postService = Mockito.spy(new PostProxyService());
        String user1 = "@testUser"; //"FirstName1" , "LastName1",
        //"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        validRequest = new PostRequest(user1, "post a message");

        successResponse = new PostResponse(true);

    }

    @Test
    void testPost_whenValidRequest_thenValidResponse() throws IOException, TweeterRemoteException {
        PostResponse actual = postService.post(validRequest);
        assertEquals(successResponse.isSuccess(), actual.isSuccess());
    }
    @Test
    void testPost_reallyBit() throws IOException, TweeterRemoteException {
        postService.post(new PostRequest("@testMickey","bigtest"));
    }
}
