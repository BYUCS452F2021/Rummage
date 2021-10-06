package edu.byu.cs.tweeter.server.service;

import java.util.List;

import edu.byu.cs.tweeter.server.dao.FollowDAO;
import edu.byu.cs.tweeter.server.dao.UserDAO;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.UserService;
import edu.byu.cs.tweeter.shared.model.service.request.UserRequest;
import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;

/**
 * Contains the business logic for getting the user associated with a given user alias.
 */
public class UserServiceImpl implements UserService {

    @Override
    public UserResponse getUser(UserRequest userRequest) throws Exception {
        List<Object> objects = getUserDAO().readUser(userRequest.getUserAlias());
        User user = (User) objects.get(0);

        return new UserResponse(user, getFollowDAO().recordExists(userRequest.getUserAlias(), userRequest.getLoggedInAlias()));
    }

    /**
     * Returns an instance, helps with mocking.
     *
     * @return the DAO instance
     */
    UserDAO getUserDAO() {
        return new UserDAO();
    }

    /**
     * Returns an instance, helps with mocking.
     *
     * @return the DAO instance
     */
    FollowDAO getFollowDAO() {
        return new FollowDAO();
    }
}
