//package edu.byu.cs.tweeter.server.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import edu.byu.cs.tweeter.server.dao.FollowDAO;
//import edu.byu.cs.tweeter.shared.model.domain.User;
//import edu.byu.cs.tweeter.shared.model.service.FollowingFollowerService;
//import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;
//import edu.byu.cs.tweeter.shared.model.service.request.UserRequest;
//import edu.byu.cs.tweeter.shared.model.service.response.FollowingFollowersResponse;
//import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;
//
///**
// * Contains the business logic for getting the users a user is following.
// */
//public class FollowingFollowerServiceImpl implements FollowingFollowerService {
//
//    /**
//     * Returns the users that the user specified in the request is following. Uses information in
//     * the request object to limit the number of followees returned and to return the next set of
//     * followees after any that were returned in a previous request. Uses the {@link FollowDAO} to
//     * get the followees.
//     *
//     * @param request contains the data required to fulfill the request.
//     * @return the followees.
//     */
//    @Override
//    public FollowingFollowersResponse getFollowingFollowers(FollowingFollowersRequest request) throws Exception {
//        List<String> resultAliases;
//        if (request.isFollowing()) {
//            resultAliases = getFollowDAO().getPagedFolloweeAliases(request.getCurrFollowerAlias(),
//                    request.getLastFolloweeAlias());
//        } else {
//            resultAliases = getFollowDAO().getPagedFollowerAliases(request.getCurrFollowerAlias(),
//                    request.getLastFolloweeAlias());
//        }
//
//        // get users from aliases
//        List<User> userList = new ArrayList<>();
//
//        UserServiceImpl userService = new UserServiceImpl();
//        UserRequest currRequest;
//        // result has one extra element in it than the page contents itself
//        for(int i = 0; i < resultAliases.size() - 1; ++i) {
//            currRequest = new UserRequest(resultAliases.get(i), request.getCurrFollowerAlias());
//            UserResponse userResponse = userService.getUser(currRequest);
//            userList.add(userResponse.getCurrUser());
//        }
//
//        // see if last page or not
//        boolean hasMorePages = resultAliases.get(resultAliases.size() - 1) != null;
//
//        // make response object
//        FollowingFollowersResponse response = new FollowingFollowersResponse(userList, hasMorePages);
//
//        return response;
//    }
//
//    /**
//     * Returns an instance of {@link FollowDAO}. Allows mocking of the FollowingDAO class
//     * for testing purposes. All usages of FollowingDAO should get their FollowingDAO
//     * instance from this method to allow for mocking of the instance.
//     *
//     * @return the instance.
//     */
//    FollowDAO getFollowDAO() {
//        return new FollowDAO();
//    }
//
//}
