package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;


import edu.byu.cs.tweeter.client.presenter.FollowedSalesPresenter;
import edu.byu.cs.tweeter.shared.model.service.request.FollowedSalesRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowedSalesResponse;

/*
 * An {@link AsyncTask} for retrieving statuses for a user.
 */
public class GetFollowedSalesTask extends AsyncTask<FollowedSalesRequest, Void, FollowedSalesResponse> {
    private final Observer observer;
    private Exception exception;
    private FollowedSalesPresenter followedSalesPresenter;

    /**
     * Creates an instance.
     *
     * @param observer          the observer who wants to be notified when this task completes.
     * @param followedSalesPresenter the presenter from whom this task should retrieve statuses.
     */
    public GetFollowedSalesTask(Observer observer, FollowedSalesPresenter followedSalesPresenter) {
        if (observer == null) {
            throw new NullPointerException();
        }

        this.observer = observer;
        this.followedSalesPresenter = followedSalesPresenter;
    }

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     *
     */
    public interface Observer {

        void followedSalesRetrieved(FollowedSalesResponse follwedSalesResponse);

        void handleException(Exception exception);

    }

    /**
     * The method that is invoked on the background thread to retrieve statuses. This method is
     * invoked indirectly by calling {@link # execute(StatusListRequest...)}.
     *
     * @ param statusListRequests the request object (there will only be one).
     * @return the response.
     */
    @Override
    protected FollowedSalesResponse doInBackground(FollowedSalesRequest... followedSalesRequests) {

        FollowedSalesResponse response = null;

        try {
            response = followedSalesPresenter.getFollowedSales((followedSalesRequests[0]));
        } catch (Exception ex) {
            exception = ex;
        }

        return response;
    }

    /**
     * Notifies the observer (on the UI thread) when the task completes.
     *
     * @ param statusListResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(FollowedSalesResponse followedSalesResponse) {
        if (exception != null) {
            observer.handleException(exception);
        } else {
            observer.followedSalesRetrieved(followedSalesResponse);
        }
    }
}
