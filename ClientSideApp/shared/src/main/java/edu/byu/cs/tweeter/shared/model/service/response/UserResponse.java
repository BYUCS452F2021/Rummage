package edu.byu.cs.tweeter.shared.model.service.response;


import edu.byu.cs.tweeter.shared.model.domain.User;

/**
 * A response for a userRequest.
 */
public class UserResponse extends Response {

    private User currUser;
    private boolean isFollowing = false;

    public UserResponse() {}

    public User getCurrUser() {
        return currUser;
    }

    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    /**
     * Creates a response indicating that the corresponding request was unsuccessful. Sets the
     * success indicator to false and returns an error message.
     *
     * @param message a message describing why the request was unsuccessful.
     */
    public UserResponse(String message) {
        super(false, message);
    }


    /**
     * Creates a response indicating that the corresponding request was successful.
     *
     * @param currUser references a user object
     */
    public UserResponse(User currUser) {
        super(true, null);
        this.currUser = currUser;
    }

    /**
     * Creates a response indicating that the corresponding request was successful.
     *
     * @param currUser references a user object
     * @param isFollowing whether the user returned is followed by the application user
     */
    public UserResponse(User currUser, boolean isFollowing) {
        super(true, null);
        this.currUser = currUser;
        this.isFollowing = isFollowing;
    }

    /*
     * Returns the user associated with the user alias.
     */
    public User getUser() { return this.currUser; }

    /*
     * Returns whether user is being followed
     */
    public boolean getIsFollowing() { return this.isFollowing; }
}
