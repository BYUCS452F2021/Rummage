package edu.byu.cs.tweeter.client.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.RelationshipChangePresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.GetCountTask;
import edu.byu.cs.tweeter.client.view.asyncTasks.RelationshipChangeTask;
import edu.byu.cs.tweeter.client.view.asyncTasks.SignOutTask;
import edu.byu.cs.tweeter.client.view.login.LoginActivity;
import edu.byu.cs.tweeter.client.view.util.ImageUtils;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.client.presenter.FollowCountPresenter;
import edu.byu.cs.tweeter.client.presenter.Presenter;
import edu.byu.cs.tweeter.client.presenter.SignOutPresenter;
import edu.byu.cs.tweeter.client.presenter.UserPresenter;
import edu.byu.cs.tweeter.shared.model.service.request.FollowCountRequest;
import edu.byu.cs.tweeter.shared.model.service.request.RelationshipChangeRequest;
import edu.byu.cs.tweeter.shared.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowCountResponse;
import edu.byu.cs.tweeter.shared.model.service.response.RelationshipChangeResponse;
import edu.byu.cs.tweeter.shared.model.service.response.SignOutResponse;


/**
 * The user view activity for the application.
 *
 * This could be used to show a list of sales
 */
public class UserViewActivity  extends AppCompatActivity
        implements Presenter.View, SignOutTask.Observer, GetCountTask.Observer, RelationshipChangeTask.Observer {
    public static final String LOG_TAG = "USER_VIEW_LOGS";

    public static final String USER_KEY = "UserKey";
    public static final String AUTH_TOKEN_KEY = "AuthTokenKey";
    public static final String MENTIONED_KEY = "MentionedKey";
    public static final String IS_FOLLOWING_KEY = "IsFollowingKey";
    public static final String USER_IMAGE = "UserImageKey";

    private AuthToken authToken;
    private SignOutPresenter signOutPresenter;
    private UserPresenter userPresenter;
    private FollowCountPresenter followCountPresenter;
    private RelationshipChangePresenter relationshipChangePresenter;
    private boolean isFollowRelationship = false;
    private User user;
    private User viewedUser;
    private Button followButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        User viewedUser = (User) getIntent().getSerializableExtra(MENTIONED_KEY);
        user = (User) getIntent().getSerializableExtra(USER_KEY);
        user.setImageBytes((byte[]) getIntent().getSerializableExtra(USER_IMAGE));

        Boolean isFollowingTemp = (Boolean) getIntent().getSerializableExtra(IS_FOLLOWING_KEY); //.getSerializableExtra(IS_FOLLOWING_KEY); //userResponse.getIsFollowing();
        if (isFollowingTemp == null) {
            throw new RuntimeException("Following Status not passed to activity");
        }
        else {
            isFollowRelationship = isFollowingTemp;
        }
        if (user == null) {
            throw new RuntimeException("Current User not passed to activity");
        }

        if (viewedUser == null) {
            throw new RuntimeException("User to view not passed to activity");
        }

        //viewedUser = new User("", "", "https://faculty.cs.byu.edu" +
        //        "/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        /*userPresenter = new UserPresenter(this);
        GetUserTask userTask = new GetUserTask(userPresenter,  UserViewActivity.this);
        UserRequest userRequest = new UserRequest(viewedUserAlias);
        userTask.execute(userRequest);*/

        followCountPresenter = new FollowCountPresenter(this);
        GetCountTask countTask = new GetCountTask(followCountPresenter, UserViewActivity.this);
        FollowCountRequest countRequest = new FollowCountRequest(viewedUser.getAlias());
        authToken = (AuthToken) getIntent().getSerializableExtra(AUTH_TOKEN_KEY);
        countRequest.setAuthToken(this.authToken);
        countTask.execute(countRequest);
        signOutPresenter = new SignOutPresenter(this);
        //postPresenter = new PostPresenter(this);
        followButton = findViewById(R.id.followButton);

        relationshipChangePresenter = new RelationshipChangePresenter(this);



        if(!isFollowRelationship) {
            followButton.setText(R.string.followButtonText);
        } else {
            followButton.setText(R.string.unfollowButtonText);
        }
        followButton.setOnClickListener(view -> {
            if(!isFollowRelationship) {
                followButton.setText(R.string.followButtonText);
            } else {
                followButton.setText(R.string.unfollowButtonText);
            }

            RelationshipChangeRequest relationshipChangeRequest = new RelationshipChangeRequest(user.getAlias(), viewedUser.getAlias(), !isFollowRelationship);
            RelationshipChangeTask relationshipChangeTask = new RelationshipChangeTask(relationshipChangePresenter, UserViewActivity.this);
            relationshipChangeRequest.setAuthToken(this.authToken);
            relationshipChangeTask.execute(relationshipChangeRequest);
        });

        SectionsPagerAdapterUserView sectionsPagerAdapterUserView = new SectionsPagerAdapterUserView(this,
                getSupportFragmentManager(),
                viewedUser,
                authToken);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapterUserView);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        TextView userName = findViewById(R.id.userName);
        userName.setText(viewedUser.getName());

        TextView userAlias = findViewById(R.id.userAlias);
        userAlias.setText(viewedUser.getAlias());

        ImageView userImageView = findViewById(R.id.userImage);
        userImageView.setImageDrawable(ImageUtils.drawableFromByteArray(user.getImageBytes()));
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
                SignOutTask signOutTask = new SignOutTask(signOutPresenter, UserViewActivity.this);
                SignOutRequest request = new SignOutRequest(authToken);
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
    public void signOutSuccessful(SignOutResponse loginResponse) {
        Toast.makeText(this,
                R.string.signOutSuccess,
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void signOutUnsuccessful(SignOutResponse loginResponse) {
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

    // ---------------------------------
    // implement methods for GetUserTask
    // ---------------------------------

    /*@Override
    public void userRetrieved(UserResponse userResponse) {

        SectionsPagerAdapterUserView sectionsPagerAdapterUserView = new SectionsPagerAdapterUserView(this,
                getSupportFragmentManager(),
                userResponse.getUser(),
                authToken);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapterUserView);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        TextView userName = findViewById(R.id.userName);
        userName.setText(userResponse.getUser().getName());

        TextView userAlias = findViewById(R.id.userAlias);
        userAlias.setText(userResponse.getUser().getAlias());

        ImageView userImageView = findViewById(R.id.userImage);
        userImageView.setImageDrawable(ImageUtils.drawableFromByteArray(userResponse.getUser().getImageBytes()));

        isFollowing = userResponse.getIsFollowing();

        if(isFollowing) {
            followButton.setText(R.string.followButtonText);

        } else {
            followButton.setText(R.string.unfollowButtonText);
        }
        isFollowRelationship = !isFollowRelationship;
        followButton.setOnClickListener(view -> {
//            isFollowing = userResponse.getIsFollowing();

            if(isFollowing) {
                followButton.setText(R.string.followButtonText);
            } else {
                followButton.setText(R.string.unfollowButtonText);
            }
            isFollowRelationship = !isFollowRelationship;

            RelationshipChangeRequest relationshipChangeRequest = new RelationshipChangeRequest(user.getAlias(), viewedUser.getAlias(), isFollowRelationship);
            RelationshipChangeTask relationshipChangeTask = new RelationshipChangeTask(relationshipChangePresenter, UserViewActivity.this);
            relationshipChangeTask.execute(relationshipChangeRequest);
        });
    }*/

    // ---------------------------------
    // implement methods for GetFollowCountTask
    // ---------------------------------

    @Override
    public void followCountRetrieved(FollowCountResponse followCountResponse) {
        int numFollowees = followCountResponse.getFollowCount().getNumFollowing();
        int numFollowers = followCountResponse.getFollowCount().getNumFollowers();

        TextView followeeCount = findViewById(R.id.followeeCount);
        followeeCount.setText(getString(R.string.followeeCount, numFollowees));

        TextView followerCount = findViewById(R.id.followerCount);
        followerCount.setText(getString(R.string.followerCount, numFollowers));
    }

    // ---------------------------------
    // implement methods for RelationshipChangeTask
    // ---------------------------------

    @Override
    public void changeRelationship(RelationshipChangeResponse relationshipChangeResponse) {
        if(relationshipChangeResponse.isFollowRelationship()) {
            followButton.setText(R.string.followButtonText);
        }
        else {
            followButton.setText(R.string.unfollowButtonText);
        }
        isFollowRelationship = relationshipChangeResponse.isFollowRelationship();
    }

    @Override
    public void handleException(Exception ex) {
        Log.e(LOG_TAG, ex.getMessage(), ex);
        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        if (Objects.equals(ex.getMessage(), "Unauthorized")) {
            finish();
        }
    }
}
