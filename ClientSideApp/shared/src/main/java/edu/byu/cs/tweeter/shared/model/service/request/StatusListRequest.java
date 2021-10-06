package edu.byu.cs.tweeter.shared.model.service.request;

import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.Status;

/**
 * Contains all the information needed to make a request to have the server return the next page of
 * followees for a specified follower.
 */
public class StatusListRequest {
    private int limit;
    private boolean isFeed;
    private String userAlias;
    private Status lastPageBottom;
    private AuthToken authToken;

    public StatusListRequest() {}

    /**
     * Creates an instance.
     *
     * @param userAlias the alias of the user whose statusList is to be returned.
     * @param pageSize the maximum number of statuses to return.
     * @param lastStatus the alias of the last status that was returned in the previous request (null if
     *                     there was no previous request or if no status were returned in the
     *                     previous request).
     * @param isFeed whether to return a feed statusList or a Story statusList
     */
    public StatusListRequest(String userAlias, int pageSize, Status lastStatus, boolean isFeed) {
        this.limit = pageSize;
        this.isFeed = isFeed;
        this.userAlias = userAlias;
        this.lastPageBottom = lastStatus;
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

    public void setLastPageBottom(Status lastPageBottom) {
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
    public Status getLastPageBottom() {
        return lastPageBottom;
    }

    @Override
    public String toString() {
        return "StatusListRequest{" +
                "limit=" + limit +
                ", isFeed=" + isFeed +
                ", userAlias='" + userAlias + '\'' +
                ", lastPageBottom=" + lastPageBottom +
                '}';
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
}
