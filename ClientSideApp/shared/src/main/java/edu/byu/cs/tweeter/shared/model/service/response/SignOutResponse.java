package edu.byu.cs.tweeter.shared.model.service.response;

/**
 * Contains all of the information about the result of signing out.
 */
public class SignOutResponse extends Response {

    public SignOutResponse() {
    }

    public SignOutResponse(String message) {
        super(false, message);
    }

    public SignOutResponse(boolean isSuccess) {
        super(isSuccess);
    }


}
