package edu.byu.cs.tweeter.shared.model.service.request;

//import edu.byu.cs.tweeter.shared.model.domain.AuthToken;

/**
 * Contains all the information needed to make a request to have the server return the number
 * of users they are following and the number of users that following the specified user
 */
public class FollowCountRequest {

    private String followerAlias;
    //private AuthToken authToken;

    public FollowCountRequest() {}

    /**
     * Creates an instance.
     *
     * @param followerAlias the alias of the user whose number followees/followers are to be returned.
     */
    public FollowCountRequest(String followerAlias) {
        this.followerAlias = followerAlias;
    }

    /**
     * Returns the curr follower whose followees/followers are to be returned by this request.
     *
     * @return the follower.
     */
    public String getFollowerAlias() {
        return followerAlias;
    }

    public void setFollowerAlias(String followerAlias) {
        this.followerAlias = followerAlias;
    }

    //public AuthToken getAuthToken() {
    //    return authToken;
    //}

    //public void setAuthToken(AuthToken authToken) {
    //    this.authToken = authToken;
    //}
}
