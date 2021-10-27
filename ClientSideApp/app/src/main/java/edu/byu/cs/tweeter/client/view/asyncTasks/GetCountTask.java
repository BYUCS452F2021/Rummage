/*
package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;


*/
/*
import edu.byu.cs.tweeter.client.presenter.FollowCountPresenter;
*//*

import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.FollowCountRequest;
*/
/*
import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;
*//*


*/
/**
 * An {@link AsyncTask} for retrieving the num followees and num followers given a user alias
 *//*

public class GetCountTask extends AsyncTask<FollowCountRequest, Void, FollowCountResponse> {

    private FollowCountPresenter presenter;
    private Observer observer;
    private Exception exception;

    */
/**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     *//*

    public interface Observer {
        void followCountRetrieved(FollowCountResponse followCountResponse);
        void handleException(Exception exception);
    }

    */
/**
     * Creates an instance.
     *  @param presenter the presenter from whom this task should retrieve FollowCount.
     * @param observer the observer who wants to be notified when this task completes.
     *//*

    public GetCountTask(FollowCountPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    */
/**
     * The method that is invoked on the background thread to retrieve FollowCount. This method is
     * invoked indirectly by calling {@link #execute(FollowCountRequest...)}.
     *
     * @param followCountRequests the request object (there will only be one).
     * @return the response.
     *//*

    @Override
    protected FollowCountResponse doInBackground(FollowCountRequest... followCountRequests) {

        FollowCountResponse response = null;

        try {
            response = presenter.getCount(followCountRequests[0]);
        } catch (Exception ex) {
            exception = ex;
        }

        return response;
    }

    */
/**
     * Notifies the observer (on the UI thread) when the task completes.
     *
     * @param followCountResponse the response that was received by the task.
     *//*

    @Override
    protected void onPostExecute(FollowCountResponse followCountResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else {
            observer.followCountRetrieved(followCountResponse);
        }
    }
}
*/
