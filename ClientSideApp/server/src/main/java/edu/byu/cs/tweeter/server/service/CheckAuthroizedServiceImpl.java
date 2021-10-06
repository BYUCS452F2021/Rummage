package edu.byu.cs.tweeter.server.service;

import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.CheckAuthorizedService;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

public class CheckAuthroizedServiceImpl implements CheckAuthorizedService {
    private final long MAX_AUTH_TIME = 60*60;

    @Override
    public SignOutResponse checkAuthorized(SignOutRequest signOutRequest) throws Exception {
        System.out.println("SignOutRequestAtCheckAuth");
        System.out.println(signOutRequest);
        AuthTokenDAO authTokenDAO = getAuthTokenDAO();
        String read = authTokenDAO.read(signOutRequest.getSessionAuthToken());
        SignOutResponse response = new SignOutResponse();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
        ZonedDateTime authTime = ZonedDateTime.parse(read, formatter);

        Duration d = Duration.between( authTime , ZonedDateTime.now() );
        long seconds = d.getSeconds();

        if (seconds < MAX_AUTH_TIME) {
            response.setSuccess(true);
        }
        else {
            response.setSuccess(false);
            response.setMessage("Unauthorized");
            authTokenDAO.delete(signOutRequest.getSessionAuthToken());
        }


        return response;
    }

    /**
     * Returns an instance, helps with mocking.
     *
     * @return the DAO instance
     */
    AuthTokenDAO getAuthTokenDAO() {
        return new AuthTokenDAO();
    }
}
