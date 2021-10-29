package edu.byu.cs.tweeter.shared.model.service.response;

import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.request.LoginRequest;

/**
 * A response for a {@link LoginRequest}.
 */
public class LoginResponse extends Response {

    private User user;

    public LoginResponse() {}

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Creates a response indicating that the corresponding request was unsuccessful.
     *
     * @param message a message describing why the request was unsuccessful.
     */
    public LoginResponse(String message) {
        super(false, message);
    }

    /**
     * Creates a response indicating that the corresponding request was successful.
     *
     * @param user the now logged in user.
     */
    public LoginResponse(User user) {
        super(true, null);
        this.user = user;
    }

    /**
     * Returns the logged in user.
     *
     * @return the user.
     */
    public User getUser() {
        return user;
    }
}
