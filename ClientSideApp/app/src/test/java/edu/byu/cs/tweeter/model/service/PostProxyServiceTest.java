package edu.byu.cs.tweeter.model.service;
/*

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

class PostProxyServiceTest {

    private PostProxyService spyPostService;
    private ServerFacade mockServerFacade;

    private PostRequest validRequest;
    private PostRequest invalidRequest;

    private PostResponse successResponse;
    private PostResponse failResponse;

    */
/**
     * Create a PostService spy that uses a mock ServerFacade to return known responses to
     * requests.
     *//*

    @BeforeEach
    void setUp() throws IOException, TweeterRemoteException {
        spyPostService = Mockito.spy(new PostProxyService());
        mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(spyPostService.getServerFacade()).thenReturn(mockServerFacade);
        String user1 = "aliasTest"; //"FirstName1" , "LastName1",
                //"https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        validRequest = new PostRequest(user1, "post a message");
        invalidRequest = new PostRequest(user1, "bad post?");

        successResponse = new PostResponse(true);
        failResponse = new PostResponse(false);
        Mockito.when(mockServerFacade.post(validRequest, "/poststatus")).thenReturn(successResponse);
        Mockito.when(mockServerFacade.post(invalidRequest, "/poststatus")).thenReturn(failResponse);
    }

    */
/**
     * Verify that for successful requests the {@link PostProxyService#post(PostRequest)} (PostRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     *//*

    @Test
    void testPost_whenValidRequest_thenValidResponse() throws IOException, TweeterRemoteException {
        PostResponse actual = spyPostService.post(validRequest);
        assertEquals(successResponse, actual);
        Mockito.verify(mockServerFacade).post(validRequest, "/poststatus");

    }

    */
/**
     * Verify that for failed requests the {@link PostProxyService#post(PostRequest)} (PostRequest)}
     * method returns the same result as the {@link ServerFacade}.
     *
     * @throws IOException if an IO error occurs.
     *//*

    @Test
    void testPost_whenInvalidRequest_thenInvalidResponse() throws IOException, TweeterRemoteException {
        PostResponse actual = spyPostService.post(invalidRequest);
        assertEquals(failResponse, actual);
        Mockito.verify(mockServerFacade).post(invalidRequest, "/poststatus");
    }

}
*/
