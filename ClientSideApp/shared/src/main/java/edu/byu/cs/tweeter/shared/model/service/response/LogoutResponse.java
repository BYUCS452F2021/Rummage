package edu.byu.cs.tweeter.shared.model.service.response;

/**
 * Contains all of the information about the result of signing out.
 */
public class LogoutResponse extends Response {

    public LogoutResponse() {
    }

    public LogoutResponse(String message) {
        super(false, message);
    }

    public LogoutResponse(boolean isSuccess) {
        super(isSuccess);
    }


}
