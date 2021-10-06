package edu.byu.cs.tweeter.server.service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import edu.byu.cs.tweeter.server.dao.AuthTokenDAO;
import edu.byu.cs.tweeter.server.dao.DaoUtil;
import edu.byu.cs.tweeter.server.dao.UserDAO;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.LoginService;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

/*
 * Contains the business login for signing in a user
 */
public class SignInServiceImpl implements LoginService {

    /**
     * Creates a response with the data from the DAO,
     * or catches an exception from the DAO and creates an errorResponse.
     *
     * @param request the request
     * @return the response
     */
    @Override
    public LoginResponse doLoginOperation(SignInRequest request) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
        String date = ZonedDateTime.now().format(formatter);
        List<Object> pair = getUserDAO().readUser(request.getAlias());
        byte[] hashedPassword = (byte[]) pair.get(2);
        byte[] hash = DaoUtil.hash(request.getPassword());
        if (!Arrays.equals(hash, hashedPassword)) {
            return new LoginResponse("Incorrect Password");
        }

        return new LoginResponse((User) pair.get(0), getAuthTokenDAO().create(date, request.getAlias()));
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
    AuthTokenDAO getAuthTokenDAO() {
        return new AuthTokenDAO();
    }

}
