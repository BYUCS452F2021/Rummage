package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.server.dao.FeedDAO;
import edu.byu.cs.tweeter.server.dao.StoryDAO;
import edu.byu.cs.tweeter.shared.model.service.StatusListService;
import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;

/**
 * Contains the business logic to support the story and feed operations
 */
public class StatusListServiceImpl implements StatusListService {

    @Override
    public StatusListResponse getStatuses(StatusListRequest statusListRequest) {
        System.out.println("in getStatuses()");
        if (statusListRequest.isFeed()) {
            return getFeedDAO().readStatuses(statusListRequest);
        }
        return getStoryDAO().readStatuses(statusListRequest);
    }

    /**
     * Returns an instance, helps with mocking.
     *
     * @return the DAO instance
     */
    StoryDAO getStoryDAO() {
        return new StoryDAO();
    }

    /**
     * Returns an instance, helps with mocking.
     *
     * @return the DAO instance
     */
    FeedDAO getFeedDAO() {
        return new FeedDAO();
    }
}
