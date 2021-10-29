package edu.byu.cs.tweeter.server.service;
/*

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.server.dao.UserDAO;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.request.UserRequest;
import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;

public class UserServiceImplTest {

    private String loggedInAlias = "@testLoggedInUser";

    private UserRequest validRequest;
    private UserRequest invalidRequest;
    private UserResponse successResponse;
    UserServiceImpl userServiceImpl;

    */
/**
     * Create a UserServiceImpl spy that uses a mock DAO to return known responses to
     * requests.
     *//*

    @BeforeEach
    public void setup() throws Exception {
        String userAlias = "FirstNameLastName";

        User resultUser = new User("FirstName", "LastName", "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        // Setup request objects to use in the tests
        validRequest = new UserRequest(userAlias, loggedInAlias);
        invalidRequest = new UserRequest("dapladdfjalsdfjpaslksdfjp", loggedInAlias);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new UserResponse(resultUser);
        List<Object> returnList = new ArrayList<>();
        returnList.add(resultUser);
        //returnList.add(new FollowCount(resultUser.getAlias()));
        UserDAO mockDAO = Mockito.mock(UserDAO.class);
        Mockito.when(mockDAO.readUser(validRequest.getUserAlias())).thenReturn(returnList);


        Mockito.when(mockDAO.readUser(invalidRequest.getUserAlias())).thenThrow(new Exception("user doesn't exist"));

        // Create a UserService instance and wrap it with a spy that will use the mock service
        userServiceImpl = Mockito.spy(new UserServiceImpl());
        Mockito.when(userServiceImpl.getUserDAO()).thenReturn(mockDAO);
    }


    */
/**
     * Verify that for successful requests the {@link UserServiceImpl#getUser(UserRequest)}
     * method returns the same result as the mockDAO.
     *//*

    @Test
    public void testGetUser_validRequest_correctResponse() throws Exception {
        UserResponse response = userServiceImpl.getUser(validRequest);
        Assertions.assertEquals(successResponse.getUser(), response.getUser());
    }

   */
/**
    * Verify that for failed requests the {@link UserServiceImpl#getUser(UserRequest)}
    * method returns the same result as the {@link UserDAO}.
    *//*

   @Test
   public void testGetUser_invalidRequest_throwsError() {
       Assertions.assertThrows(Exception.class, () -> userServiceImpl.getUser(invalidRequest));
   }

   @Test
   public void removeUserForIntegrationTest() {
     userServiceImpl.getUserDAO().deleteUser("signUpTester");
   }
}
*/
