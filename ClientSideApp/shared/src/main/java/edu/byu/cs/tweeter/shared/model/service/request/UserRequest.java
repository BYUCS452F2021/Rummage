package edu.byu.cs.tweeter.shared.model.service.request;

import edu.byu.cs.tweeter.shared.model.domain.AuthToken;

/**
 * Contains all the information needed to make a request to have the server return the user
 * associated with the given user alias.
 */
public class UserRequest {

    private String loggedInAlias;
    private String userAlias;
    private AuthToken authToken;

    public UserRequest() {}

    /**
     * Creates an instance.
     *
     * @param userAlias alias associated with a user
     */
    public UserRequest(String userAlias, String loggedInAlias) {
        this.userAlias = userAlias;
        this.loggedInAlias = loggedInAlias;
    }

    /**
     * Returns the user alias.
     *
     * @return the follower.
     */
    public String getUserAlias() {
        return this.userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public String getLoggedInAlias() {
        return loggedInAlias;
    }

    public void setLoggedInAlias(String loggedInAlias) {
        this.loggedInAlias = loggedInAlias;
    }
}
