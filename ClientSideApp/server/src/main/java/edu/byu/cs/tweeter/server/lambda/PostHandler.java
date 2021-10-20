//package edu.byu.cs.tweeter.server.lambda;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//
//import java.io.IOException;
//
//import edu.byu.cs.tweeter.server.service.PostServiceImpl;
//import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
//import edu.byu.cs.tweeter.shared.model.service.PostService;
//import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
//import edu.byu.cs.tweeter.shared.model.service.response.PostResponse;
//
///**
// * An AWS lambda function to create a new status post
// */
//public class PostHandler implements RequestHandler<PostRequest, PostResponse> {
//
//    @Override
//    public PostResponse handleRequest(PostRequest postRequest, Context context) {
//        PostService postService = new PostServiceImpl();
//        try {
//            return postService.post(postRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new PostResponse(e.getMessage());
//        }
//    }
//}
