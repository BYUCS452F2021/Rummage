package edu.byu.cs.tweeter.client.view.login.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.view.asyncTasks.LoginTask;
import edu.byu.cs.tweeter.client.presenter.LoginPresenter;
import edu.byu.cs.tweeter.client.presenter.Presenter;
import edu.byu.cs.tweeter.client.view.main.MainActivity;
import edu.byu.cs.tweeter.shared.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

/**
 * The fragment that displays on the 'SignIn' tab.
 */
public class SignInFragment extends Fragment implements Presenter.View, LoginTask.Observer {

    private class EmptyFieldTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // do nothing
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // do nothing
        }

        @Override
        public void afterTextChanged(Editable editable) {
            signInButton.setEnabled(isValidInputs());
        }

        private boolean isValidInputs() {
            return editTextAlias.getText().toString().length() > 0
                    && editTextPassword.getText().toString().length() > 0;
        }

    }

    private static final String LOG_TAG = "SignInFragment";

    private LoginPresenter presenter;

    private EditText editTextAlias;
    private EditText editTextPassword;
    private Button signInButton;

    /**
     * Creates an instance of the fragment.
     *
     * @return the fragment.
     */
    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        TextWatcher textWatcher = new EmptyFieldTextWatcher();
        presenter = new LoginPresenter(this);

        editTextAlias = view.findViewById(R.id.input_alias);
        editTextAlias.addTextChangedListener(textWatcher);

        editTextPassword = view.findViewById(R.id.input_password);
        editTextPassword.addTextChangedListener(textWatcher);

        signInButton = view.findViewById(R.id.sign_up_button);
        signInButton.setOnClickListener((View view1) -> {
            SignInRequest signInRequest = new SignInRequest(editTextAlias.getText().toString(),
                    editTextPassword.getText().toString());
            LoginTask loginTask = new LoginTask(presenter, SignInFragment.this);
            loginTask.execute(signInRequest);
        });

        return view;
    }

    /**
     * The callback method that gets invoked for a successful login. Displays the MainActivity.
     *
     * @param loginResponse the response from the login request.
     */
    @Override
    public void loginSuccessful(LoginResponse loginResponse) {
        Intent intent = new Intent(this.getContext(), MainActivity.class);

        intent.putExtra(MainActivity.CURRENT_USER_KEY, loginResponse.getUser());
        //intent.putExtra(MainActivity.USER_IMAGE, loginResponse.getUser().getImageBytes());
        //intent.putExtra(MainActivity.AUTH_TOKEN_KEY, loginResponse.getAuthToken()); //FIXME maybe we should do auth tokens

        Toast.makeText(SignInFragment.this.getContext(), R.string.signInSuccess,
                Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    /**
     * The callback method that gets invoked for an unsuccessful login. Displays a toast with a
     * message indicating why the login failed.
     *
     * @param loginResponse the response from the login request.
     */
    @Override
    public void loginUnsuccessful(LoginResponse loginResponse) {
        Toast.makeText(this.getContext(), R.string.signInFailure + loginResponse.getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * A callback indicating that an exception was thrown in an asynchronous method called on the
     * presenter.
     *
     * @param exception the exception.
     */
    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(this.getContext(), "Failed to login because of exception: " + exception.getMessage(),
                Toast.LENGTH_LONG).show();
    }

}
