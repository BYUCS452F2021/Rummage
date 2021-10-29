package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;

/*import edu.byu.cs.tweeter.client.model.service.CheckAuthorizedProxyService;*/
import edu.byu.cs.tweeter.client.model.service.PostProxyService;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
/*
import edu.byu.cs.tweeter.shared.model.service.CheckAuthorizedService;
*/
import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.PostResponse;

/*
 * Contains Post Presenter logic
 */
public class PostPresenter extends Presenter {

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public PostPresenter(View view) {
        super(view);
    }

    /*
     * Gets the results from a PostService with a post request
     * @param request postRequest
     * @ret PostResponse response from post service
     */
    public PostResponse post(PostRequest request) throws Exception {
        //getCheckAuthorizedService().checkAuthorized(new SignOutRequest(request.getAuthToken()));
        return getPostService().post(request);
    }

    /**
     * Returns an instance of {@link PostProxyService}. Allows mocking of the PostServer class
     * for testing purposes. All usages of PostServer should get their PostServer
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    public PostProxyService getPostService() {
        return new PostProxyService();
    }

}
