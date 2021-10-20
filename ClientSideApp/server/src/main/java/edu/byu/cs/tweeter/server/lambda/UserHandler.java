//package edu.byu.cs.tweeter.server.lambda;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//
//import java.io.IOException;
//
//import edu.byu.cs.tweeter.server.service.UserServiceImpl;
//import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
//import edu.byu.cs.tweeter.shared.model.service.UserService;
//import edu.byu.cs.tweeter.shared.model.service.request.UserRequest;
//import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;
//
///**
// * An AWS lambda function that returns a user with a given user alias
// */
//public class UserHandler implements RequestHandler<UserRequest, UserResponse> {
//
//    @Override
//    public UserResponse handleRequest(UserRequest userRequest, Context context) {
//        UserService userService = new UserServiceImpl();
//        try {
//            return userService.getUser(userRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new UserResponse(e.getMessage());
//        }
//    }
//
//}
