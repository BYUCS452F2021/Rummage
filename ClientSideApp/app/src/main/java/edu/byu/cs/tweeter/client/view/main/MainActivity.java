package edu.byu.cs.tweeter.client.view.main;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import edu.byu.cs.tweeter.R;
//import edu.byu.cs.tweeter.client.view.asyncTasks.GetCountTask;
import edu.byu.cs.tweeter.client.view.asyncTasks.PostTask;
import edu.byu.cs.tweeter.client.view.asyncTasks.SignOutTask;
import edu.byu.cs.tweeter.client.view.util.ImageUtils;
//import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.User;
//import edu.byu.cs.tweeter.client.presenter.FollowCountPresenter;
import edu.byu.cs.tweeter.client.presenter.PostPresenter;
import edu.byu.cs.tweeter.client.presenter.Presenter;
import edu.byu.cs.tweeter.client.presenter.SignOutPresenter;
import edu.byu.cs.tweeter.shared.model.service.request.FollowCountRequest;
import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.request.LogoutRequest;
//import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;
import edu.byu.cs.tweeter.shared.model.service.response.PostResponse;
import edu.byu.cs.tweeter.shared.model.service.response.LogoutResponse;

/**
 * The main activity for the application.
 */
public class MainActivity extends AppCompatActivity
        implements Presenter.View, PostTask.Observer, SignOutTask.Observer {

    public static final String LOG_TAG = "MAIN_LOGS";

    public static final String CURRENT_USER_KEY = "CurrentUser";
    public static final String AUTH_TOKEN_KEY = "AuthTokenKey";
    public static final String USER_IMAGE = "UserImage";
    public static final int STATUS_CHAR_LIMIT = 200;

    //private AuthToken authToken;
    private SignOutPresenter signOutPresenter;
    private PostPresenter postPresenter;
    //private FollowCountPresenter followCountPresenter;

    private AlertDialog.Builder postDialogBuilder;
    private AlertDialog postAlertDialog;
    private int charsUsed = 0;

    @SuppressLint("StringFormatInvalid")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = (User) getIntent().getSerializableExtra(CURRENT_USER_KEY);
        if (user == null) {
            throw new RuntimeException("User not passed to activity");
        }
        //user.setImageBytes((byte[]) getIntent().getSerializableExtra(USER_IMAGE));
        //intent.putExtra(MainActivity.USER_IMAGE, loginResponse.getUser().getImageBytes());

        //authToken = (AuthToken) getIntent().getSerializableExtra(AUTH_TOKEN_KEY);
        signOutPresenter = new SignOutPresenter(this);
        postPresenter = new PostPresenter(this);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this,
                getSupportFragmentManager(),
                user/*,
                authToken*/);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton postFab = findViewById(R.id.fab);

        // postFab could be used as a createNewSale Fab
        postFab.setOnClickListener(fabView -> {

            EditText statusInput = new EditText(MainActivity.this);
            statusInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    // do nothing
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    charsUsed = charSequence.length();
                    postAlertDialog.setMessage(getResources().getString(R.string.postFullMessage,
                            charsUsed,
                            STATUS_CHAR_LIMIT));
                    if ((charsUsed <= 0) || (charsUsed > STATUS_CHAR_LIMIT)) {
                        postAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                                .setEnabled(false);
                    } else {
                        postAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                                .setEnabled(true);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    // do nothing
                }
            });

            // setup a dialog
            postDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            postDialogBuilder.setTitle(R.string.postTitle);
            postDialogBuilder.setMessage(getResources().getString(R.string.postFullMessage,
                    0,
                    STATUS_CHAR_LIMIT));
            postDialogBuilder.setView(statusInput);
            postDialogBuilder.setCancelable(false);
            postDialogBuilder.setPositiveButton(R.string.postPosButton, (dialogInterface, i) -> {
                // do logic and dismiss
                PostTask postTask = new PostTask(postPresenter, MainActivity.this);
                PostRequest request = new PostRequest(user.getUsername(), statusInput.getText().toString());
                //request.setAuthToken(this.authToken);
                postTask.execute(request);

                dialogInterface.dismiss();
            });
            postDialogBuilder.setNegativeButton(R.string.postNegButton, (dialogInterface, i) -> {
                Toast.makeText(this, R.string.postCancelMessage, Toast.LENGTH_LONG).show();
                dialogInterface.cancel();
            });

            // create the specified dialog
            postAlertDialog = postDialogBuilder.create();
            postAlertDialog.show();

            // finish setup on created dialog
            postAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
        });

        /*TextView userName = findViewById(R.id.userName);
        userName.setText(user.getName());

        TextView userAlias = findViewById(R.id.userAlias);
        userAlias.setText(user.getAlias());*/

        /*ImageView userImageView = findViewById(R.id.userImage);
        userImageView.setImageDrawable(ImageUtils.drawableFromByteArray(user.getImageBytes()));*/

        //with the current user, get their following and follower count
        /*followCountPresenter = new FollowCountPresenter(this);
        GetCountTask countTask = new GetCountTask(followCountPresenter, MainActivity.this);
        FollowCountRequest countRequest = new FollowCountRequest(user.getAlias());
        countRequest.setAuthToken(this.authToken);
        countTask.execute(countRequest);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logoutMenu) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.signOutConfirmText);
            builder.setPositiveButton(R.string.signOutConfirmYes, (dialogInterface, i) -> {
                SignOutTask signOutTask = new SignOutTask(signOutPresenter, MainActivity.this);
                LogoutRequest request = new LogoutRequest(/*authToken*/);
                signOutTask.execute(request);

                dialogInterface.dismiss();
            });
            builder.setNegativeButton(R.string.signOutConfirmNo, (dialogInterface, i) -> dialogInterface.cancel());

            AlertDialog alert = builder.create();
            alert.setTitle(R.string.signOutConfirmTitle);
            alert.show();
            return true;
        }
        return false;
    }

    // ---------------------------------
    // implement methods for signOutTask
    // ---------------------------------
    @Override
    public void signOutSuccessful(LogoutResponse loginResponse) {
        Toast.makeText(this,
                R.string.signOutSuccess,
                Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void signOutUnsuccessful(LogoutResponse loginResponse) {
        Toast.makeText(this,
                R.string.signOutFail,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleSignOutException(Exception ex) {
        Log.e(LOG_TAG, ex.getMessage(), ex);
        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        if (Objects.equals(ex.getMessage(), "Unauthorized")) {
            finish();
        }
    }

    // ------------------------------
    // implement methods for postTask
    // ------------------------------
    @Override
    public void postSuccessful(PostResponse postResponse) {
        Toast.makeText(this, R.string.postSuccessMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postUnsuccessful(PostResponse postResponse) {
        Toast.makeText(this, R.string.postFailMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handlePostException(Exception ex) {
        Log.e(LOG_TAG, ex.getMessage(), ex);
        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        if (Objects.equals(ex.getMessage(), "Unauthorized")) {
            finish();
        }
    }


    // ------------------------------
    // implement methods for GetCountTask
    // ------------------------------
    /*@Override
    public void followCountRetrieved(FollowCountResponse followCountResponse) {
        int numFollowees = followCountResponse.getFollowCount().getNumFollowing();
        int numFollowers = followCountResponse.getFollowCount().getNumFollowers();
        TextView followeeCount = findViewById(R.id.followeeCount);
        followeeCount.setText(getString(R.string.followeeCount, numFollowees));

        TextView followerCount = findViewById(R.id.followerCount);
        followerCount.setText(getString(R.string.followerCount, numFollowers));
    }*/

    /*@Override
    public void handleException(Exception ex) {
        Log.e(LOG_TAG, ex.getMessage(), ex);
        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        if (Objects.equals(ex.getMessage(), "Unauthorized")) {
            finish();
        }
    }*/
}
