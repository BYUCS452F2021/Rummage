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
import edu.byu.cs.tweeter.shared.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.shared.model.service.response.LoginResponse;

/**
 * The fragment that displays on the 'SignIn' tab.
 */
public class SignUpFragment extends Fragment implements Presenter.View, LoginTask.Observer {

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
            signUpButton.setEnabled(isValidInputs());
        }

        private boolean isValidInputs() {
            return editTextAlias.getText().toString().length() > 0
                    && editTextPassword.getText().toString().length() > 0
                    && editTextFirstName.getText().toString().length() > 0
                    && editTextLastName.getText().toString().length() > 0
                    && editTextImageUrl.getText().toString().length() > 0;
        }

    }

    private static final String LOG_TAG = "SignUpFragment";

    private LoginPresenter presenter;

    private EditText editTextAlias;
    private EditText editTextPassword;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextImageUrl;
    private Button signUpButton;

    private Toast loginToast;

    /**
     * Creates an instance of the fragment.
     *
     * @return the fragment.
     */
    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        TextWatcher textWatcher = new EmptyFieldTextWatcher();
        presenter = new LoginPresenter(this);

        editTextAlias = view.findViewById(R.id.input_alias);
        editTextAlias.addTextChangedListener(textWatcher);

        editTextPassword = view.findViewById(R.id.input_password);
        editTextPassword.addTextChangedListener(textWatcher);

        editTextFirstName = view.findViewById(R.id.input_first_name);
        editTextFirstName.addTextChangedListener(textWatcher);

        editTextLastName = view.findViewById(R.id.input_last_name);
        editTextLastName.addTextChangedListener(textWatcher);

        editTextImageUrl = view.findViewById(R.id.input_image_url);
        editTextImageUrl.addTextChangedListener(textWatcher);

        signUpButton = view.findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener((View signUpButtonView) -> {
            SignInRequest signUpRequest = new SignUpRequest(editTextAlias.getText().toString(),
                    editTextPassword.getText().toString(),
                    editTextFirstName.getText().toString(),
                    editTextLastName.getText().toString(),
                    editTextImageUrl.getText().toString());
            LoginTask loginTask = new LoginTask(presenter, SignUpFragment.this);
            loginTask.execute(signUpRequest);
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
        intent.putExtra(MainActivity.USER_IMAGE, loginResponse.getUser().getImageBytes());
        intent.putExtra(MainActivity.AUTH_TOKEN_KEY, loginResponse.getAuthToken());

        Toast.makeText(SignUpFragment.this.getContext(), R.string.signUpSuccess,
                Toast.LENGTH_LONG).show();
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
        Toast.makeText(this.getContext(), R.string.signUpFailure + loginResponse.getMessage(),
                Toast.LENGTH_LONG).show();
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
