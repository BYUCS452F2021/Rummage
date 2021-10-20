//package edu.byu.cs.tweeter.server.lambda;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//
//import java.io.IOException;
//
//import edu.byu.cs.tweeter.server.service.StatusListServiceImpl;
//import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
//import edu.byu.cs.tweeter.shared.model.service.StatusListService;
//import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
//import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;
//
///**
// * An AWS lambda function that returns the status's for a given user
// */
//public class StatusListHandler implements RequestHandler<StatusListRequest, StatusListResponse> {
//
//    @Override
//    public StatusListResponse handleRequest(StatusListRequest statusListRequest, Context context) {
//        StatusListService statusListService = new StatusListServiceImpl();
//        System.out.println(statusListRequest.toString());
//        try {
//            return statusListService.getStatuses(statusListRequest);
//        } catch (IOException | TweeterRemoteException e) {
//            e.printStackTrace();
//            return new StatusListResponse(e.getMessage());
//        }
//    }
//
//}
