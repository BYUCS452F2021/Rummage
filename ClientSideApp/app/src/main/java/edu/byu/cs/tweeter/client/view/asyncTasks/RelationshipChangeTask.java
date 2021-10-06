package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.client.presenter.RelationshipChangePresenter;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;

/**
 * An {@link AsyncTask} for changing the relationship between two users
 */
public class RelationshipChangeTask extends AsyncTask<RelationshipChangeRequest, Void, RelationshipChangeResponse>{

    private final RelationshipChangePresenter presenter;
    private final RelationshipChangeTask.Observer observer;
    private Exception exception;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface Observer {
        void changeRelationship(RelationshipChangeResponse relationshipChangeResponse);
        void handleException(Exception exception);
    }

    /**
     * Creates an instance.
     *
     * @param presenter the presenter from whom this task should update relationship.
     * @param observer the observer who wants to be notified when this task completes.
     */
    public RelationshipChangeTask(RelationshipChangePresenter presenter, RelationshipChangeTask.Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    /**
     * The method that is invoked on the background thread to update relationship. This method is
     * invoked indirectly by calling {@link #execute(RelationshipChangeRequest...)}.
     *
     * @param relationshipChangeRequests the request object (there will only be one).
     * @return the response.
     */
    @Override
    protected RelationshipChangeResponse doInBackground(RelationshipChangeRequest... relationshipChangeRequests) {

        RelationshipChangeResponse response = null;

        try {
            response = presenter.changeRelationship(relationshipChangeRequests[0]);
        } catch (Exception ex) {
            exception = ex;
        }

        return response;
    }

    /**
     * Notifies the observer (on the UI thread) when the task completes.
     *
     * @param relationshipChangeResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(RelationshipChangeResponse relationshipChangeResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else {
            observer.changeRelationship(relationshipChangeResponse);
        }
    }
}
