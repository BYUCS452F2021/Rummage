package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.server.dao.UserDAO;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.LoginService;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

/*
 * Contains the business login for signing in a user
 */
public class SignInServiceImpl implements LoginService {

    /**
     * Creates a response with the data from the DAO,
     * or catches an exception from the DAO and creates an errorResponse.
     *
     * @param request the request
     * @return the response
     */
    @Override
    public LoginResponse doLoginOperation(SignInRequest request) throws Exception {
        User user = getUserDAO().getUser(request.getUsername());
        if (!user.getPassword().equals(request.getPassword())) {
            return new LoginResponse("Incorrect Password");
        }

        return new LoginResponse(user);
    }

    /**
     * Returns an instance, helps with mocking.
     *
     * @return the DAO instance
     */
    UserDAO getUserDAO() {
        return new UserDAO();
    }
}
