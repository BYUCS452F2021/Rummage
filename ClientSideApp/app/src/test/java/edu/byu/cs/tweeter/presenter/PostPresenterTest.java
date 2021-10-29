package edu.byu.cs.tweeter.presenter;
/*

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.client.presenter.PostPresenter;
import edu.byu.cs.tweeter.client.presenter.Presenter;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.client.model.service.PostProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.CheckAuthorizedService;
import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.PostResponse;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostPresenterTest {

    private PostPresenter spyPostPresenter;
    private PostProxyService mockPostService;
    private CheckAuthorizedService mockCheckAuthorizedService;

    private PostRequest request;
    private PostResponse response;

    */
/**
     * Create a PostPresenter spy that uses a mock service to return known responses to
     * requests.
     *//*

    @BeforeEach
    void setUp() throws Exception {
        mockCheckAuthorizedService = Mockito.mock(CheckAuthorizedService.class);
        Mockito.when(mockCheckAuthorizedService.checkAuthorized(Mockito.any(SignOutRequest.class))).thenReturn(new SignOutResponse());
        spyPostPresenter = Mockito.spy(new PostPresenter(new Presenter.View() {
        }));
        mockPostService = Mockito.mock(PostProxyService.class);
        Mockito.when(spyPostPresenter.getPostService()).thenReturn(mockPostService);
        Mockito.when(spyPostPresenter.getCheckAuthorizedService()).thenReturn(mockCheckAuthorizedService);

        User user1 = new User("FirstName1", "LastName1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        request = new PostRequest(user1.getAlias(), "some message");
        response = new PostResponse(true);
    }

    */
/*
     * Tests if the Post presenter returns the same response as the service
     *//*

    @Test
    void testPost_returnsServiceResult() throws Exception {
        Mockito.when(mockPostService.post(request)).thenReturn(response);
        PostResponse actual = spyPostPresenter.post(request);
        assertEquals(response, actual);
    }

    // TODO
    // we would add a test for throwing an exception,
    // but with dummy data it doesn't make sense to throw any exceptions for post yet

}
*/
