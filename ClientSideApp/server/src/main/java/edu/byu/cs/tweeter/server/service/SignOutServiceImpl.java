package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.shared.model.service.SignOutService;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

/*
 * Contains the business logic for signing out a user
 */
public class SignOutServiceImpl implements SignOutService {

    @Override
    public SignOutResponse signOut(SignOutRequest request) {
        getAuthTokenDAO().delete(request.getSessionAuthToken());
        return new SignOutResponse(true);
        //return getSignOutDao().signOut(request);
    }

    /**
     * Returns an instance, helps with mocking.
     *
     * @return the DAO instance
     */
    //SignOutDAO getSignOutDao() {
    //    return new SignOutDAO();
    //}

    /**
     * Returns an instance, helps with mocking.
     *
     * @return the DAO instance
     */
    AuthTokenDAO getAuthTokenDAO() {
        return new AuthTokenDAO();
    }

}
