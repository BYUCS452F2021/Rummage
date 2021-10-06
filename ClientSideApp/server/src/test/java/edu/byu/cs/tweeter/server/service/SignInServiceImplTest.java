package edu.byu.cs.tweeter.server.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.server.dao.UserDAO;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.FollowCount;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignInServiceImplTest {

    private SignInServiceImpl spySignInServiceImpl;
    private UserDAO mockServiceSignInDAO;

    private SignInRequest validRequest;
    private SignInRequest invalidRequest;

    private LoginResponse successResponse;
    private LoginResponse failResponse;

    /**
     * Create a SignInServiceImpl spy that uses a mock DAO to return known responses to
     * requests.
     */
    @BeforeEach
    void setUp() throws Exception {
        spySignInServiceImpl = Mockito.spy(new SignInServiceImpl());
        mockServiceSignInDAO = Mockito.mock(UserDAO.class);
        Mockito.when(spySignInServiceImpl.getUserDAO()).thenReturn(mockServiceSignInDAO);

        validRequest = new SignInRequest("@testMickey", "password");
        invalidRequest = new SignInRequest("@testMickey", "testPass");

        User user1 = new User("FirstName1", "LastName1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        successResponse = new LoginResponse(user1, new AuthToken());
        failResponse = new LoginResponse("something went wrong");
        List<Object> pairSuccess = new ArrayList<>();
        pairSuccess.add(successResponse.getUser());
        pairSuccess.add(new FollowCount());
        pairSuccess.add("password");
        List<Object> pairFail = new ArrayList<>();
        pairFail.add(failResponse.getUser());
        pairFail.add(new FollowCount());
        pairFail.add("passwordNot");

        Mockito.when(mockServiceSignInDAO.readUser(validRequest.getAlias())).thenReturn(pairSuccess);
        Mockito.when(mockServiceSignInDAO.readUser(invalidRequest.getAlias())).thenReturn(pairFail);
    }

    /**
     * Verify that for successful requests the
     * method returns the same result as the
     */
    @Test
    void testSignIn_whenValidRequest_thenValidResponse() throws Exception {
        LoginResponse actual = spySignInServiceImpl.doLoginOperation(validRequest);
        assertEquals(successResponse, actual);
        Mockito.verify(mockServiceSignInDAO).readUser(validRequest.getAlias());
    }

    /**
     * Verify that for failed requests the
     * method returns the same result as the
     */
    @Test
    void testSignIn_whenInvalidRequest_thenInvalidResponse() throws Exception {
        LoginResponse actual = spySignInServiceImpl.doLoginOperation(invalidRequest);
        assertEquals(failResponse, actual);
        Mockito.verify(mockServiceSignInDAO).readUser(invalidRequest.getAlias());
    }
}
