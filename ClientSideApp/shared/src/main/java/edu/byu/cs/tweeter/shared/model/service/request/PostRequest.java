package edu.byu.cs.tweeter.shared.model.service.request;

//import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.User;

/**
 * Contains all of the information needed to post a new status.
 */
public class PostRequest {

    private String authorAlias;
    private String message;
    //private AuthToken authToken;

    public PostRequest() {}

    public PostRequest(String authorAlias, String message) {
        this.authorAlias = authorAlias;
        this.message = message;
    }

    public String getAuthorAlias() {
        return authorAlias;
    }

    public String getMessage() {
        return message;
    }

    public void setAuthorAlias(String authorAlias) {
        this.authorAlias = authorAlias;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }*/
}
