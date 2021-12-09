package edu.byu.cs.tweeter.client.view.asyncTasks;
/*

import android.os.AsyncTask;

import java.io.IOException;


import edu.byu.cs.tweeter.client.presenter.FollowingFollowerPresenter;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowingFollowersResponse;

*/
/**
 * An {@link AsyncTask} for retrieving followees (or followers) for a user.
 *//*

public class GetFollowingFollowersTask extends AsyncTask<FollowingFollowersRequest, Void, FollowingFollowersResponse> {

    private final FollowingFollowerPresenter presenter;
    private final Observer observer;
    private Exception exception;

    */
/**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     *//*

    public interface Observer {
        void followeesRetrieved(FollowingFollowersResponse followingFollowersResponse);
        void handleException(Exception exception);
    }

    */
/**
     * Creates an instance.
     *
     * @param presenter the presenter from whom this task should retrieve followees/followers.
     * @param observer the observer who wants to be notified when this task completes.
     *//*

    public GetFollowingFollowersTask(FollowingFollowerPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    */
/**
     * The method that is invoked on the background thread to retrieve followees/followers. This method is
     * invoked indirectly by calling {@link #execute(FollowingFollowersRequest...)}.
     *
     * @param followingRequests the request object (there will only be one).
     * @return the response.
     *//*

    @Override
    protected FollowingFollowersResponse doInBackground(FollowingFollowersRequest... followingRequests) {

        FollowingFollowersResponse response = null;

        try {
            response = presenter.getFollowList(followingRequests[0]);
        } catch (Exception ex) {
            exception = ex;
        }

        return response;
    }

    */
/**
     * Notifies the observer (on the UI thread) when the task completes.
     *
     * @param followingFollowersResponse the response that was received by the task.
     *//*

    @Override
    protected void onPostExecute(FollowingFollowersResponse followingFollowersResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else {
            observer.followeesRetrieved(followingFollowersResponse);
        }
    }
}*/
