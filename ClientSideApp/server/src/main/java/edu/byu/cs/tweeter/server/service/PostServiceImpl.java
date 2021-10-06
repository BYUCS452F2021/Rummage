package edu.byu.cs.tweeter.server.service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import edu.byu.cs.tweeter.server.dao.FeedDAO;
import edu.byu.cs.tweeter.server.dao.StoryDAO;
import edu.byu.cs.tweeter.server.dao.UserDAO;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.PostService;
import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.response.PostResponse;

/*
 * Contains the business logic to create a new status post
 */
public class PostServiceImpl implements PostService {

    @Override
    public PostResponse post(PostRequest request) throws Exception {
        List<Object> objects = getUserDAO().readUser(request.getAuthorAlias());
        User user = (User) objects.get(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");
        ZonedDateTime now = ZonedDateTime.now();
        String date = now.format(formatter);
        long dateNum = now.toInstant().atZone(ZoneOffset.UTC).toEpochSecond();

        getFeedDAO().createPost(request, user.getFirstName(), user.getLastName(), user.getImageUrl(), date, dateNum);


        return new PostResponse(getStoryDAO().createPost(request, user.getFirstName(), user.getLastName(), user.getImageUrl(), date, dateNum));
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

    /**
     * Returns an instance, helps with mocking.
     *
     * @return the DAO instance
     */
    UserDAO getUserDAO() {
        return new UserDAO();
    }

}
