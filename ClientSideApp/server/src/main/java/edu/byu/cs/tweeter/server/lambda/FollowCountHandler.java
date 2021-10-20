//package edu.byu.cs.tweeter.server.lambda;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//
//import edu.byu.cs.tweeter.server.service.FollowCountServiceImpl;
//import edu.byu.cs.tweeter.shared.model.service.FollowCountService;
//import edu.byu.cs.tweeter.shared.model.service.request.FollowCountRequest;
//import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;
//
///**
// * An AWS lambda function that returns the follow count for a given user
// */
//public class FollowCountHandler implements RequestHandler<FollowCountRequest, FollowCountResponse> {
//
//    @Override
//    public FollowCountResponse handleRequest(FollowCountRequest followCountRequest, Context context) {
//        System.out.println("User Alias");
//        System.out.println(followCountRequest.getFollowerAlias());
//        System.out.println("authtoken");
//        System.out.println(followCountRequest.getAuthToken());
//        FollowCountService followCountService = new FollowCountServiceImpl();
//        try {
//            return followCountService.getFollowCount(followCountRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new FollowCountResponse(e.getMessage());
//        }
//    }
//
//}
