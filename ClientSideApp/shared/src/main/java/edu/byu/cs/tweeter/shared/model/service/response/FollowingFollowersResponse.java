package edu.byu.cs.tweeter.shared.model.service.response;

import java.util.List;
import java.util.Objects;

import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;

/**
 * A paged response for a {@link FollowingFollowersRequest}.
 */
public class FollowingFollowersResponse extends PagedResponse {

    private List<User> followees;

    public FollowingFollowersResponse() {}

    public void setFollowees(List<User> followees) {
        this.followees = followees;
    }
    /**
     * Creates a response indicating that the corresponding request was unsuccessful. Sets the
     * success and more pages indicators to false.
     *
     * @param message a message describing why the request was unsuccessful.
     */
    public FollowingFollowersResponse(String message) {
        super(false, message, false);
    }

    /**
     * Creates a response indicating that the corresponding request was successful.
     *
     * @param followees the followees to be included in the result.
     * @param hasMorePages an indicator of whether more data is available for the request.
     */
    public FollowingFollowersResponse(List<User> followees, boolean hasMorePages) {
        super(true, hasMorePages);
        this.followees = followees;
    }

    /**
     * Returns the followees for the corresponding request.
     *
     * @return the followees.
     */
    public List<User> getFollowees() {
        return followees;
    }

    @Override
    public boolean equals(Object param) {
        if (this == param) {
            return true;
        }

        if (param == null || getClass() != param.getClass()) {
            return false;
        }

        FollowingFollowersResponse that = (FollowingFollowersResponse) param;

        return (Objects.equals(followees, that.followees) &&
                Objects.equals(this.getMessage(), that.getMessage()) &&
                this.isSuccess() == that.isSuccess());
    }

    @Override
    public int hashCode() {
        return Objects.hash(followees);
    }


}
