package edu.byu.cs.tweeter.shared.model.service.request;

//import edu.byu.cs.tweeter.shared.model.domain.AuthToken;

/**
 * Contains all the information needed to make a request to have the server return the next page of
 * followees or followers for a specified follower (for the Following and Followers tabs in UI, respectfully.
 */
public class FollowingFollowersRequest {

    private String currFollowerAlias;
    private int limit;
    private String lastFolloweeAlias;
    private boolean isFollowing;
    //private AuthToken authToken;

    public FollowingFollowersRequest() {}

    /**
     * Creates an instance.
     *
     * @param followerAlias the alias of the user whose followees/follwers are to be returned.
     * @param limit the maximum number of followees/followers to return.
     * @param lastFolloweeAlias the alias of the last followee/follower that was returned in the previous request (null if
     *                     there was no previous request or if no followees/followers were returned in the
     *                     previous request).
     * @param isFollowing indicates if the request if for Following or Followers
     */
    public FollowingFollowersRequest(String followerAlias, int limit, String lastFolloweeAlias, boolean isFollowing) {
        this.currFollowerAlias = followerAlias;
        this.limit = limit;
        this.lastFolloweeAlias = lastFolloweeAlias;
        this.isFollowing = isFollowing;
    }

    /**
     * Returns the curr follower whose followees/followers are to be returned by this request.
     *
     * @return the follower.
     */
    public String getCurrFollowerAlias() {
        return currFollowerAlias;
    }

    /**
     * Returns the number representing the maximum number of followees/followers to be returned by this request.
     *
     * @return the limit.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Returns the last followee/follower that was returned in the previous request or null if there was no
     * previous request or if no followees/followers were returned in the previous request.
     *
     * @return the last followee/follower.
     */
    public String getLastFolloweeAlias() {
        return lastFolloweeAlias;
    }

    /**
     * Returns boolean indicating if the request is for a Following or Followers.
     *
     * @return bool indicating if the req is for a Following.
     */
    public Boolean getIsFollowing() {
        return isFollowing;
    }

    public void setCurrFollowerAlias(String currFollowerAlias) {
        this.currFollowerAlias = currFollowerAlias;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setLastFolloweeAlias(String lastFolloweeAlias) {
        this.lastFolloweeAlias = lastFolloweeAlias;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    /*public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }*/
}
