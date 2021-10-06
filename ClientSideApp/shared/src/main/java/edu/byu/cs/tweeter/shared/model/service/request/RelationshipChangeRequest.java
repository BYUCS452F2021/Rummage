package edu.byu.cs.tweeter.shared.model.service.request;

import edu.byu.cs.tweeter.shared.model.domain.AuthToken;

/**
 * Contains all of the information needed to make a request to the server to
 * change the relationship with the currentUser with another user (follow or unfollow)
 */
public class RelationshipChangeRequest {

    /**
     * true = we are requesting to follow
     */
    public boolean isFollowRelationship;

    private String currUserAlias;
    private String otherUser;
    private AuthToken authToken;

    public RelationshipChangeRequest() {}

    /**
     * Creates an instance.
     *
     * @param currUserAlias the alias of the current user who changes their relationship with otherUser
     * @param otherUser the user to who the currentUser will be changing their relationship with
     * @param isFollowRelationship indicates if the current user will be following or unfollowing the other user
     */
    public RelationshipChangeRequest(String currUserAlias, String otherUser, boolean isFollowRelationship) {
        this.currUserAlias = currUserAlias;
        this.otherUser = otherUser;
        this.isFollowRelationship = isFollowRelationship;
    }

    /**
     * Returns the relationship change
     *
     * @return the relationship change
     */
    public boolean isFollowRelationship() {
        return isFollowRelationship;
    }

    /**
     * Returns the current user alias
     *
     * @return the current user alias
     */
    public String getCurrUserAlias() {
        return currUserAlias;
    }

    /**
     * Returns the other user
     *
     * @return the other user
     */
    public String getOtherUser() {
        return otherUser;
    }

    public void setFollowRelationship(boolean followRelationship) {
        isFollowRelationship = followRelationship;
    }

    public void setCurrUserAlias(String currUserAlias) {
        this.currUserAlias = currUserAlias;
    }

    public void setOtherUser(String otherUser) {
        this.otherUser = otherUser;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}
