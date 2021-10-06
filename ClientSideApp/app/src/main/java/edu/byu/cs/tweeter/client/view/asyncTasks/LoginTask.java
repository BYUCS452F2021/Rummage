package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;


import edu.byu.cs.tweeter.client.presenter.LoginPresenter;
import edu.byu.cs.tweeter.shared.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

/*
 * An {@link AsyncTask} for logging in a user.
 */
public class LoginTask extends AsyncTask<SignInRequest, Void, LoginResponse> {

    private final LoginPresenter loginPresenter;
    private final Observer observer;
    private Exception exception;

    /**
     * Creates an instance.
     *
     * @param presenter the presenter this task should use to login.
     * @param observer  the observer who wants to be notified when this task completes.
     */
    public LoginTask(LoginPresenter presenter, Observer observer) {
        if (observer == null) {
            throw new NullPointerException();
        }

        this.loginPresenter = presenter;
        this.observer = observer;
    }

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface Observer {
        void loginSuccessful(LoginResponse loginResponse);

        void loginUnsuccessful(LoginResponse loginResponse);

        void handleException(Exception ex);

    }

    /**
     * The method that is invoked on a background thread to log the user in. This method is
     * invoked indirectly by calling {@link #execute(SignInRequest...)}.
     *
     * @param signInRequests the request object (there will only be one).
     * @return the response.
     */
    @Override
    protected LoginResponse doInBackground(SignInRequest... signInRequests) {
        LoginResponse loginResponse = null;

        try {
            loginResponse = loginPresenter.login(signInRequests[0]);
        } catch (IOException | TweeterRemoteException ex) {
            exception = ex;
        }

        return loginResponse;
    }

    /**
     * Notifies the observer (on the thread of the invoker of the
     * {@link #execute(SignInRequest...)} method) when the task completes.
     *
     * @param loginResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(LoginResponse loginResponse) {
        if (exception != null) {
            observer.handleException(exception);
        } else if (loginResponse.isSuccess()) {
            observer.loginSuccessful(loginResponse);
        } else {
            observer.loginUnsuccessful(loginResponse);
        }
    }
}
