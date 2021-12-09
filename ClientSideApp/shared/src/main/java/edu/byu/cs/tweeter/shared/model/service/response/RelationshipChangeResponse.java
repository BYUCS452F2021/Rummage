package edu.byu.cs.tweeter.shared.model.service.response;

import java.util.Objects;

/*
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
*/
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;

/**
 * A response for a {@link RelationshipChangeRequest}.
 */
public class RelationshipChangeResponse extends Response {

/*
    AuthToken authToken;
*/

    /**
     * true = the user is now following the other user
     * (after the request is processed)
     */
    boolean isFollowRelationship;

/*
    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
*/

    public void setIsFollowRelationship(boolean followRelationship) {
        isFollowRelationship = followRelationship;
    }

    /**
     * Creates a response indicating that the corresponding request was unsuccessful. Sets the
     * success indicator to false and displays an error message.
     *
     * @param message a message describing why the request was unsuccessful.
     */
    public RelationshipChangeResponse(String message) {super(false, message);  }

    /**
     * Creates a response indicating that the corresponding request was successful.
     *
     * @ param authToken authToken for the logged in user
     */
    public RelationshipChangeResponse(/*AuthToken authToken, */boolean isFollowRelationship) {
        super(true, null);
/*
        this.authToken = authToken;
*/
        this.isFollowRelationship = isFollowRelationship;
    }

    /**
     * Returns the AuthToken object for the logged in user.
     *
     * @return the  AuthToken object for the logged in user.
     */
/*
    public AuthToken getAuthToken() {
        return this.authToken;
    }
*/

    /**
     * Returns the relationship change
     *
     * @return the relationship change
     */
    public boolean isFollowRelationship() {
        return isFollowRelationship;
    }

    public boolean getIsFollowRelationship() {
        return isFollowRelationship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationshipChangeResponse that = (RelationshipChangeResponse) o;
        return isFollowRelationship == that.isFollowRelationship /*&&
                Objects.equals(authToken, that.authToken)*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(/*authToken, */isFollowRelationship);
    }
}
