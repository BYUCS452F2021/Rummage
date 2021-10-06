package edu.byu.cs.tweeter.server.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.server.dao.StoryDAO;
import edu.byu.cs.tweeter.server.dao.UserDAO;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.response.PostResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;

public class PostServiceImplTest {

    private PostServiceImpl spyPostServiceImpl;
    private UserDAO mockUserDAO;
    private StoryDAO mockStoryDAO;

    private PostRequest validRequest;
    private PostRequest invalidRequest;

    private PostResponse successResponse;
    private PostResponse failResponse;

    /**
     * Create a PostService spy that uses a mock ServerFacade to return known responses to
     * requests.
     */
    @BeforeEach
    void setUp() throws Exception {
        spyPostServiceImpl = Mockito.spy(new PostServiceImpl());
        mockUserDAO = Mockito.mock(UserDAO.class);
        Mockito.when(spyPostServiceImpl.getUserDAO()).thenReturn(mockUserDAO);
        mockStoryDAO = Mockito.mock(StoryDAO.class);
        Mockito.when(spyPostServiceImpl.getStoryDAO()).thenReturn(mockStoryDAO);
        String user1 = "aliasTest";
        User user2 = new User("FirstName1" , "LastName1", user1, "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        validRequest = new PostRequest(user1, "post a message");
        invalidRequest = new PostRequest(user1, "bad post?");

        successResponse = new PostResponse(true);
        failResponse = new PostResponse(false);
        List<Object> objectList = new ArrayList<>();
        objectList.add(user2);
        Mockito.when(mockUserDAO.readUser(user1)).thenReturn(objectList);
        Mockito.when(mockStoryDAO.createPost(any(PostRequest.class), any(String.class), any(String.class), any(String.class), any(String.class), any(Long.class))).thenReturn(true);
        //Mockito.when(mockStoryDAO.createPost(invalidRequest, user2.getFirstName(), user2.getLastName(), user2.getImageUrl(), any(String.class), any(Long.class))).thenReturn(false);
    }

    /**
     * Verify that for successful requests the
     * method returns the same result as the
     */
    @Test
    void testPost_whenValidRequest_thenValidResponse() throws Exception {
        PostResponse actual = spyPostServiceImpl.post(validRequest);
        assertEquals(successResponse.isSuccess(), actual.isSuccess());
        //Mockito.verify(mockPostDAO).post(validRequest);
    }

    /* *
     * Verify that for failed requests the {@link PostServiceImpl#post(PostRequest)}
     * method returns the same result as the {@link PostDAO}.
     */
    //@Test
    //void testPost_whenInvalidRequest_thenInvalidResponse() throws Exception {
    //    PostResponse actual = spyPostServiceImpl.post(invalidRequest);
    //    assertEquals(failResponse, actual);
    //    //Mockito.verify(mockPostDAO).post(invalidRequest);
    //}
}
