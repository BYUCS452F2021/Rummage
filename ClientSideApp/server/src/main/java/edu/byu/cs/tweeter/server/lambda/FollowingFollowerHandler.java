package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.server.service.FollowingFollowerServiceImpl;
import edu.byu.cs.tweeter.shared.model.service.FollowingFollowerService;
import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowingFollowersResponse;

/**
 * An AWS lambda function that returns the users a user is following.
 */
public class FollowingFollowerHandler implements RequestHandler<FollowingFollowersRequest, FollowingFollowersResponse> {

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param request contains the data required to fulfill the request.
     * @param context the lambda context.
     * @return the followees.
     */
    @Override
    public FollowingFollowersResponse handleRequest(FollowingFollowersRequest request, Context context) {
        FollowingFollowerService service = new FollowingFollowerServiceImpl();
        try {
            return service.getFollowingFollowers(request);
        } catch (Exception e) {
            e.printStackTrace();
            return new FollowingFollowersResponse(e.getMessage());
        }
    }
}
