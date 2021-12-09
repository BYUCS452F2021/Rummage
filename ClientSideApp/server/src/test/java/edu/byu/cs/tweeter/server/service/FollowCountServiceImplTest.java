package edu.byu.cs.tweeter.server.service;

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;

//import java.util.ArrayList;
//import java.util.List;

//import edu.byu.cs.tweeter.server.dao.UserDAO;
//import edu.byu.cs.tweeter.shared.model.domain.FollowCount;
//import edu.byu.cs.tweeter.shared.model.domain.User;
//import edu.byu.cs.tweeter.shared.model.service.request.FollowCountRequest;
//import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;
/*

public class FollowCountServiceImplTest {

    private FollowCountServiceImpl spyFollowCountServiceImpl;
    private UserDAO mockDAO;

    private FollowCountRequest validRequest;
    private FollowCountRequest invalidRequest;
    private FollowCountResponse successResponse;


    */
/**
     * Create a FollowCountImpl spy that uses a mock DAO to return known responses to
     * requests.
     *//*

    @BeforeEach
    public void setup() throws Exception {
        User currentUser = new User("FirstName", "LastName", "@testFollowCountService", null);
        FollowCount followCount = new FollowCount(currentUser.getAlias());
        followCount.setNumFollowers(43);
        followCount.setNumFollowing(41);

        // Setup request objects to use in the tests
        validRequest = new FollowCountRequest(currentUser.getAlias());
        invalidRequest = new FollowCountRequest("");

        List<Object> mockReturnList = new ArrayList<>();
        mockReturnList.add(new User());
        mockReturnList.add(followCount);

        // Setup a mock
        successResponse = new FollowCountResponse(followCount);

        mockDAO = Mockito.mock(UserDAO.class);
        Mockito.when(mockDAO.readUser(validRequest.getFollowerAlias())).thenReturn(mockReturnList);

        // Create a FollowingService instance and wrap it with a spy that will use the mock service
        spyFollowCountServiceImpl = Mockito.spy(new FollowCountServiceImpl());
        Mockito.when(spyFollowCountServiceImpl.getUserDAO()).thenReturn(mockDAO);
    }

    */
/**
     * Verify that for successful requests the {@link FollowCountServiceImpl#getFollowCount(FollowCountRequest)}
     * method returns the same result as the mockDAO.
     *//*

    @Test
    public void testGetFollowCount_validRequest_correctResponse() throws Exception {
        FollowCountResponse response = spyFollowCountServiceImpl.getFollowCount(validRequest);
        Assertions.assertEquals(successResponse.getFollowCount(), response.getFollowCount());
    }

    */
/**
     * Verify that for failed requests the {@link FollowCountServiceImpl#getFollowCount(FollowCountRequest)}
     * method throws an exception.
     *//*

    @Test
    public void testGetFollowCount_invalidRequest_returnsNoFollowees() {
        Assertions.assertThrows(Exception.class, () -> spyFollowCountServiceImpl.getFollowCount(invalidRequest));
    }
}
*/
