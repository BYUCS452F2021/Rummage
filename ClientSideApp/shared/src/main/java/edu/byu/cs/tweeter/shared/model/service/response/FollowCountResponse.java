/*
package edu.byu.cs.tweeter.shared.model.service.response;
import edu.byu.cs.tweeter.shared.model.domain.FollowCount;

*/
/* *
 * A response for a FollowCountRequest. Contains the number of followees/followers.
 *//*

public class FollowCountResponse extends Response {

    private FollowCount followCount;

    public FollowCountResponse() {}

    */
/* *
     * Creates a response indicating that the corresponding request was unsuccessful. Sets the
     * success indicator to false and displays an error message.
     *
     * @param message a message describing why the request was unsuccessful.
     *//*

    public FollowCountResponse(String message) { super(false, message); }


    */
/* *
     * Creates a response indicating that the corresponding request was successful.
     *
     * @param followCount object containing userAlias, numFollowing, & numFollowers
     *//*

    public FollowCountResponse(FollowCount followCount) {
        super(true, null);
        this.followCount = followCount;
    }

    */
/* *
     * Returns the followCount object for the corresponding request.
     *
     * @return the followCount object.
     *//*

    public FollowCount getFollowCount() { return this.followCount; }
}
*/
