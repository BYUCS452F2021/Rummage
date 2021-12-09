package edu.byu.cs.tweeter.server.service;

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
///import org.mockito.Mockito;

//import java.util.ArrayList;
//import java.util.List;

//import edu.byu.cs.tweeter.server.dao.FollowDAO;
//import edu.byu.cs.tweeter.shared.model.domain.User;
//import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;
//import edu.byu.cs.tweeter.shared.model.service.response.FollowingFollowersResponse;
/*

public class FollowingFollowerServiceImplTest {

    private FollowingFollowerServiceImpl spyFollowingFollowerService;
    private FollowDAO mockFollowDAO;

    private FollowingFollowersRequest validRequest;
    private FollowingFollowersRequest invalidRequest;
    private FollowingFollowersRequest validFollowerRequest;
    private FollowingFollowersRequest invalidFollowerRequest;

    private FollowingFollowersResponse successResponse;

    private User currentUser;

    @BeforeEach
    public void setup() {
        currentUser = new User("FirstName", "LastName", null);

        User resultUser1 = new User("FirstName1", "LastName1", "@testGetFollowerUser1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        User resultUser2 = new User("FirstName2", "LastName2","@testGetFollowerUser2",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
        User resultUser3 = new User("FirstName3", "LastName3","@testGetFollowerUser3",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
        List<User> userList = new ArrayList<>();
        userList.add(resultUser1);
        userList.add(resultUser2);
        userList.add(resultUser3);

        List<String> userAliasList = new ArrayList<>();
        userAliasList.add("@testGetFollowerUser1");
        userAliasList.add("@testGetFollowerUser2");
        userAliasList.add("@testGetFollowerUser3");
        userAliasList.add(null);

        // Setup request objects to use in the tests
        validRequest = new FollowingFollowersRequest(currentUser.getAlias(),
                3,
                null,
                true);
        invalidRequest = new FollowingFollowersRequest(null,
                0,
                null,
                true);
        validFollowerRequest = new FollowingFollowersRequest(currentUser.getAlias(),
                3,
                null,
                false);
        invalidFollowerRequest = new FollowingFollowersRequest(null,
                0,
                null,
                false);

        // Setup a mock
        successResponse = new FollowingFollowersResponse(userList,false);

        mockFollowDAO = Mockito.mock(FollowDAO.class);
        Mockito.when(mockFollowDAO.getPagedFolloweeAliases(validRequest.getCurrFollowerAlias(),
                validRequest.getLastFolloweeAlias()))
                .thenReturn(userAliasList);
        Mockito.when(mockFollowDAO.getPagedFollowerAliases(validFollowerRequest.getCurrFollowerAlias(),
                validFollowerRequest.getLastFolloweeAlias()))
                .thenReturn(userAliasList);

        // Create a FollowingService instance and wrap it with a spy that will use the mock
        spyFollowingFollowerService = Mockito.spy(new FollowingFollowerServiceImpl());
        Mockito.when(spyFollowingFollowerService.getFollowDAO()).thenReturn(mockFollowDAO);
    }

    */
/**
     * Verify that for successful requests the {@link FollowingFollowerServiceImpl#getFollowingFollowers(FollowingFollowersRequest)} (FollowingFollowersRequest)}
     * method returns the same result as the mockDAO.
     *//*

    @Test
    public void testGetFollowees_validRequest_correctResponse() throws Exception {
        FollowingFollowersResponse actual = spyFollowingFollowerService.getFollowingFollowers(validRequest);
        Assertions.assertEquals(successResponse, actual);
    }

    */
/**
     * Verify that for failed requests the {@link FollowingFollowerServiceImpl#getFollowingFollowers(FollowingFollowersRequest)} (FollowingFollowersRequest)}
     * method it throws an exception.
     *//*

    @Test
    public void testGetFollowees_invalidRequest_thenThrows() {
        Assertions.assertThrows(Exception.class, () -> spyFollowingFollowerService.getFollowingFollowers(invalidRequest));
    }

    */
/**
     * Verify that for successful requests the {@link FollowingFollowerServiceImpl#getFollowingFollowers(FollowingFollowersRequest)} (FollowingFollowersRequest)}
     * method returns the same result as the mockDAO.
     *//*

    @Test
    public void testGetFollowers_validRequest_correctResponse() throws Exception {

        FollowingFollowersResponse actual = spyFollowingFollowerService.getFollowingFollowers(validFollowerRequest);
        Assertions.assertEquals(successResponse, actual);
    }

    */
/**
     * Verify that for failed requests the {@link FollowingFollowerServiceImpl#getFollowingFollowers(FollowingFollowersRequest)} (FollowingFollowersRequest)}
     * method it throws an exception.
     *//*

    @Test
    public void testGetFollowers_invalidRequest_thenThrows() {
        Assertions.assertThrows(Exception.class, () -> spyFollowingFollowerService.getFollowingFollowers(invalidFollowerRequest));
    }

}
*/
