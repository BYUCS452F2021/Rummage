package edu.byu.cs.tweeter.server.service;

import java.util.List;

import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.server.dao.UserDAO;
import edu.byu.cs.tweeter.shared.model.domain.FollowCount;
import edu.byu.cs.tweeter.shared.model.service.FollowCountService;
import edu.byu.cs.tweeter.shared.model.service.request.FollowCountRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;

/**
 * Contains the business logic for getting the number of followees
 * and followers for the given userAlias.
 */
public class FollowCountServiceImpl implements FollowCountService {

    @Override
    public FollowCountResponse getFollowCount(FollowCountRequest followCountRequest) throws Exception {
        List<Object> objectList = getUserDAO().readUser(followCountRequest.getFollowerAlias());
        FollowCount count = (FollowCount) objectList.get(1);

        return new FollowCountResponse(count);
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
