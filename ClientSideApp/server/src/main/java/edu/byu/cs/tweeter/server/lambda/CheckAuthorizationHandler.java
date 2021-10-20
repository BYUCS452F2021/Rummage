//package edu.byu.cs.tweeter.server.lambda;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//
//import java.io.IOException;
//
//import edu.byu.cs.tweeter.server.service.CheckAuthroizedServiceImpl;
//import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
//import edu.byu.cs.tweeter.shared.model.service.CheckAuthorizedService;
//import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
//import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;
//
//public class CheckAuthorizationHandler implements RequestHandler<SignOutRequest, SignOutResponse> {
//    @Override
//    public SignOutResponse handleRequest(SignOutRequest authCheck, Context context) {
//        CheckAuthorizedService checkAuthorizedService = new CheckAuthroizedServiceImpl();
//        try {
//            return checkAuthorizedService.checkAuthorized(authCheck);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new SignOutResponse(e.getMessage());
//        }
//    }
//}
