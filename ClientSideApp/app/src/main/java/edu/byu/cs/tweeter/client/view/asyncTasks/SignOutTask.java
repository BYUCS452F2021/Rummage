package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;


import edu.byu.cs.tweeter.client.presenter.SignOutPresenter;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;

/*
 * An {@link AsyncTask} for signing out a user.
 */
public class SignOutTask extends AsyncTask<SignOutRequest, Void, SignOutResponse> {
    private final SignOutPresenter signOutPresenter;
    private final Observer observer;
    private Exception exception;

    /**
     * Creates an instance.
     *
     * @param presenter the presenter this task should use to login.
     * @param observer  the observer who wants to be notified when this task completes.
     */
    public SignOutTask(SignOutPresenter presenter, SignOutTask.Observer observer) {
        if (observer == null) {
            throw new NullPointerException();
        }

        this.signOutPresenter = presenter;
        this.observer = observer;
    }

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface Observer {
        void signOutSuccessful(SignOutResponse loginResponse);

        void signOutUnsuccessful(SignOutResponse loginResponse);

        void handleSignOutException(Exception ex);

    }

    @Override
    protected SignOutResponse doInBackground(SignOutRequest... signOutRequests) {
        SignOutResponse signOutResponse = null;

        try {
            signOutResponse = signOutPresenter.signOut(signOutRequests[0]);
        } catch (Exception ex) {
            exception = ex;
        }

        return signOutResponse;
    }

    /**
     * Notifies the observer (on the thread of the invoker of the
     * {@link #execute(SignOutRequest...)} method) when the task completes.
     *
     * @param signOutResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(SignOutResponse signOutResponse) {
        if (exception != null) {
            observer.handleSignOutException(exception);
        } else if (signOutResponse.isSuccess()) {
            observer.signOutSuccessful(signOutResponse);
        } else {
            observer.signOutUnsuccessful(signOutResponse);
        }
    }

}
