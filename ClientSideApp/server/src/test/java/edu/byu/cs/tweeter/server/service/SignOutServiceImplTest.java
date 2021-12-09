package edu.byu.cs.tweeter.server.service;
/*

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignOutServiceImplTest {

    private SignOutServiceImpl spySignOutServiceImpl;
    private AuthTokenDAO mockSignOutDAO;

    private SignOutRequest validRequest;
    private SignOutRequest invalidRequest;
    private AuthToken badAuth;

    private SignOutResponse successResponse;
    private SignOutResponse failResponse;

    */
/**
     * Create a SignOutServiceImpl spy that uses a mock DAO to return known responses to
     * requests.
     *//*

    @BeforeEach
    void setUp() {
        spySignOutServiceImpl = Mockito.spy(new SignOutServiceImpl());
        mockSignOutDAO = Mockito.mock(AuthTokenDAO.class);
        Mockito.when(spySignOutServiceImpl.getAuthTokenDAO()).thenReturn(mockSignOutDAO);

        validRequest = new SignOutRequest(new AuthToken());
        badAuth = new AuthToken();
        badAuth.setKeyId("bad");
        invalidRequest = new SignOutRequest(badAuth);
        successResponse = new SignOutResponse(true);
        failResponse = new SignOutResponse(false);

        //Mockito.when(mockSignOutDAO.delete(validRequest.getSessionAuthToken());).thenReturn(successResponse);
        Mockito.doThrow(Exception.class).when(mockSignOutDAO).delete(badAuth);
    }

    */
/**
     * Verify that for successful requests the {@link SignOutServiceImpl#signOut(SignOutRequest)}
     * method returns the same result as the
     *//*

    @Test
    void testSignOut_whenValidRequest_thenValidResponse() {
        SignOutResponse actual = spySignOutServiceImpl.signOut(validRequest);
        assertTrue(actual.isSuccess());
        Mockito.verify(mockSignOutDAO).delete(validRequest.getSessionAuthToken());
    }

    */
/**
     * Verify that for failed requests the
     * method returns the same result as the
     *//*

    @Test
    void testSignOut_whenInvalidRequest_thenInvalidResponse() {
        Assertions.assertThrows(Exception.class, () -> {
            SignOutResponse actual = spySignOutServiceImpl.signOut(invalidRequest);
        });
    }
}
*/
