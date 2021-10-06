package edu.byu.cs.tweeter.server.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.server.dao.UserDAO;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpServiceImplTest {

    private SignUpServiceImpl spySignUpServiceImpl;
    private UserDAO mockServiceSignUpDAO;

    private SignUpRequest validRequest;
    private SignUpRequest invalidRequest;

    private LoginResponse successResponse;
    private LoginResponse failResponse;

    /**
     * Create a SignUpServiceImpl spy that uses a mock DAO to return known responses to
     * requests.
     */
    @BeforeEach
    void setUp() throws Exception {
        spySignUpServiceImpl = Mockito.spy(new SignUpServiceImpl());
        mockServiceSignUpDAO = Mockito.mock(UserDAO.class);
        Mockito.when(spySignUpServiceImpl.getUserDAO()).thenReturn(mockServiceSignUpDAO);

        validRequest = new SignUpRequest("testAlias", "testPass", "name", "surname", null);
        invalidRequest = new SignUpRequest("testAlias", "testPass", "bad", "bad", null);

        User user1 = new User("FirstName1", "LastName1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        successResponse = new LoginResponse(user1, new AuthToken());
        failResponse = new LoginResponse("something went wrong");
        List<Object> pairSuccess = new ArrayList<>();
        pairSuccess.add(successResponse);
        pairSuccess.add(new AuthToken());
        pairSuccess.add("password");
        List<Object> pairFail = new ArrayList<>();
        pairFail.add(failResponse);
        pairFail.add(new AuthToken());
        pairFail.add("passwordNot");

        Mockito.when(mockServiceSignUpDAO.createUser(validRequest)).thenReturn(pairSuccess);
        Mockito.when(mockServiceSignUpDAO.createUser(invalidRequest)).thenReturn(pairFail);
    }

    /**
     * Verify that for successful requests the {@link SignUpServiceImpl#doLoginOperation(SignInRequest)}
     * method returns the same result as the
     */
    @Test
    void testSignup_whenValidRequest_thenValidResponse() throws Exception {
        LoginResponse actual = spySignUpServiceImpl.doLoginOperation(validRequest);
        assertEquals(successResponse, actual);
        Mockito.verify(mockServiceSignUpDAO).createUser(validRequest);
    }

    /**
     * Verify that for failed requests the {@link SignUpServiceImpl#doLoginOperation(SignInRequest)}
     * method returns the same result as the
     */
    @Test
    void testSignup_whenInvalidRequest_thenInvalidResponse() throws Exception {
        LoginResponse actual = spySignUpServiceImpl.doLoginOperation(invalidRequest);
        assertEquals(failResponse, actual);
        Mockito.verify(mockServiceSignUpDAO).createUser(invalidRequest);
    }
}
