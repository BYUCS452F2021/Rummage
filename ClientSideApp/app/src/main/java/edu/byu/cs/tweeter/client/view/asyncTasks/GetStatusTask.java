package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;


import edu.byu.cs.tweeter.client.presenter.StatusListPresenter;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;

/*
 * An {@link AsyncTask} for retrieving statuses for a user.
 */
public class GetStatusTask extends AsyncTask<StatusListRequest, Void, StatusListResponse> {
    private final Observer observer;
    private Exception exception;
    private StatusListPresenter statusesPresenter;

    /**
     * Creates an instance.
     *
     * @param observer          the observer who wants to be notified when this task completes.
     * @param statusesPresenter the presenter from whom this task should retrieve statuses.
     */
    public GetStatusTask(Observer observer, StatusListPresenter statusesPresenter) {
        if (observer == null) {
            throw new NullPointerException();
        }

        this.observer = observer;
        this.statusesPresenter = statusesPresenter;
    }

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     *
     */
    public interface Observer {

        void statusesRetrieved(StatusListResponse statusListResponse);

        void handleException(Exception exception);

    }

    /**
     * The method that is invoked on the background thread to retrieve statuses. This method is
     * invoked indirectly by calling {@link #execute(StatusListRequest...)}.
     *
     * @param statusListRequests the request object (there will only be one).
     * @return the response.
     */
    @Override
    protected StatusListResponse doInBackground(StatusListRequest... statusListRequests) {

        StatusListResponse response = null;

        try {
            response = statusesPresenter.getStatusList((statusListRequests[0]));
        } catch (Exception ex) {
            exception = ex;
        }

        return response;
    }

    /**
     * Notifies the observer (on the UI thread) when the task completes.
     *
     * @param statusListResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(StatusListResponse statusListResponse) {
        if (exception != null) {
            observer.handleException(exception);
        } else {
            observer.statusesRetrieved(statusListResponse);
        }
    }
}
