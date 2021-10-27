package edu.byu.cs.tweeter.shared.model.service.request;

/*import edu.byu.cs.tweeter.shared.model.domain.AuthToken;*/
import edu.byu.cs.tweeter.shared.model.domain.Sale;

/**
 * Contains all the information needed to make a request to have the server return the next page of
 * followees for a specified follower.
 */
public class FollowedSalesRequest {
    private int limit;
    private boolean isFeed;
    private String userAlias;
    private Sale lastPageBottom;
/*
    private AuthToken authToken;
*/

    public FollowedSalesRequest() {}

    /**
     * Creates an instance.
     *
     * @param userAlias the alias of the user whose statusList is to be returned.
     * @param pageSize the maximum number of statuses to return.
     * @param yardSale the alias of the last yardsale that was returned in the previous request (null if
     *                     there was no previous request or if no status were returned in the
     *                     previous request).
     * @param isFeed whether to return a feed statusList or a Story statusList
     */
    public FollowedSalesRequest(String userAlias, int pageSize, Sale yardSale, boolean isFeed) {
        this.limit = pageSize;
        this.isFeed = isFeed;
        this.userAlias = userAlias;
        this.lastPageBottom = yardSale;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setFeed(boolean feed) {
        isFeed = feed;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public void setLastPageBottom(Sale lastPageBottom) {
        this.lastPageBottom = lastPageBottom;
    }

    /**
     * Returns the maximum number of statuses to return.
     *
     * @return the limit.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Returns whether to return a feed statusList or a Story statusList
     *
     * @return the isFeed.
     */
    public boolean isFeed() {
        return isFeed;
    }

    /**
     * Returns the alias of the user whose statusList is to be returned.
     *
     * @return the user.
     */
    public String getUserAlias() {
        return userAlias;
    }

    /**
     * Returns the alias of the last status that was returned in the previous request (null if
     *                      there was no previous request or if no status were returned in the
     *                      previous request).
     *
     * @return the lastPageBottom.
     */
    public Sale getLastPageBottom() {
        return lastPageBottom;
    }

    @Override
    public String toString() {
        return "FollowedSalesRequest{" +
                "limit=" + limit +
                ", isFeed=" + isFeed +
                ", userAlias='" + userAlias + '\'' +
                ", lastPageBottom=" + lastPageBottom +
                '}';
    }

/*    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }*/
}
