package edu.byu.cs.tweeter.shared.model.service.response;

import edu.byu.cs.tweeter.shared.model.domain.User;

public class RegisterUserResponse extends Response{

    private User user;

    public RegisterUserResponse() {    }

    public LogoutResponse(boolean isSuccess, String message) {
        super(isSuccess, message);
    }

    public User getUser() {
        return user;
    }
}