package edu.byu.cs.tweeter.client.view.main.tabs;
/*

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.UserPresenter;
//import edu.byu.cs.tweeter.client.view.asyncTasks.GetFollowingFollowersTask;
import edu.byu.cs.tweeter.client.view.asyncTasks.GetUserTask;
import edu.byu.cs.tweeter.client.view.util.ImageUtils;
//import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.client.presenter.FollowingFollowerPresenter;
import edu.byu.cs.tweeter.client.view.main.UserViewActivity;
import edu.byu.cs.tweeter.shared.model.service.request.FollowingFollowersRequest;
import edu.byu.cs.tweeter.shared.model.service.request.UserRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowingFollowersResponse;
import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;

*/
/**
 * The fragment that displays on the 'Following' or the 'Followers' tab.
 *//*

public class FollowingFollowerFragment extends Fragment implements FollowingFollowerPresenter.View, GetUserTask.Observer {

    private static final String LOG_TAG = "FollowingFragment";
    private static final String USER_KEY = "UserKey";
    private static final String AUTH_TOKEN_KEY = "AuthTokenKey";
    private static final String IS_FOLLOWING_KEY = "IsFollowingKey";

    private static final int LOADING_DATA_VIEW = 0;
    private static final int ITEM_VIEW = 1;

    private static final int PAGE_SIZE = 10;

    private User user;
    //private AuthToken authToken;
    private FollowingFollowerPresenter presenter;
    private boolean isFollowing;

    */
/*private FollowingRecyclerViewAdapter followingRecyclerViewAdapter;*//*


    */
/**
     * Creates an instance of the fragment and places the user and auth token in an arguments
     * bundle assigned to the fragment.
     *
     * @param user the logged in user.
     * @ param authToken the auth token for this user's session.
     * @param isFollowing whether this fragment displays Followers or Following
     * @return the fragment.
     *//*

    //public static FollowingFollowerFragment newInstance(User user, AuthToken authToken, boolean isFollowing) {
    public static FollowingFollowerFragment newInstance(User user, boolean isFollowing) {
        FollowingFollowerFragment fragment = new FollowingFollowerFragment();

        Bundle args = new Bundle(2);
        args.putSerializable(USER_KEY, user);
        //args.putSerializable(AUTH_TOKEN_KEY, authToken);
        args.putBoolean(IS_FOLLOWING_KEY, isFollowing);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_following, container, false);

        */
/*//*
/noinspection ConstantConditions
        user = (User) getArguments().getSerializable(USER_KEY);
        //authToken = (AuthToken) getArguments().getSerializable(AUTH_TOKEN_KEY);
        isFollowing = getArguments().getBoolean(IS_FOLLOWING_KEY);

        presenter = new FollowingFollowerPresenter(this);

        RecyclerView followingRecyclerView = view.findViewById(R.id.followingRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        followingRecyclerView.setLayoutManager(layoutManager);

        followingRecyclerViewAdapter = new FollowingRecyclerViewAdapter(this);
        followingRecyclerView.setAdapter(followingRecyclerViewAdapter);

        followingRecyclerView.addOnScrollListener(new FollowRecyclerViewPaginationScrollListener(layoutManager));*//*


        return view;
    }

    @Override
    public void userRetrieved(UserResponse userResponse) {
        Intent intent = new Intent(this.getContext(), UserViewActivity.class);
        intent.putExtra(UserViewActivity.USER_KEY, user);
        //intent.putExtra(UserViewActivity.AUTH_TOKEN_KEY, authToken);
        intent.putExtra(UserViewActivity.MENTIONED_KEY, userResponse.getUser());
        intent.putExtra(UserViewActivity.IS_FOLLOWING_KEY, userResponse.getIsFollowing());
        //intent.putExtra(UserViewActivity.USER_IMAGE, userResponse.getUser().getImageBytes());
        startActivity(intent);
    }

    @Override
    public void handleException(Exception ex) {
        Log.e(LOG_TAG, ex.getMessage(), ex);
        Toast.makeText(this.getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        if (Objects.equals(ex.getMessage(), "Unauthorized")) {
            this.getActivity().finish();
        }
    }

    */
/**
     * The ViewHolder for the RecyclerView that displays the Following data.
     *//*

    private class FollowingHolder extends RecyclerView.ViewHolder {

        private final ImageView userImage;
        private final TextView userAlias;
        private final TextView userName;

        */
/**
         * Creates an instance and sets an OnClickListener for the user's row.
         *
         * @param itemView the view on which the user will be displayed.
         *//*

        FollowingHolder(FollowingFollowerFragment followingFollowerFragment, @NonNull View itemView, int viewType) {
            super(itemView);

            if(viewType == ITEM_VIEW) {
                userImage = itemView.findViewById(R.id.userImage);
                userAlias = itemView.findViewById(R.id.userAlias);
                userName = itemView.findViewById(R.id.userName);

                itemView.setOnClickListener(view -> {
                    UserPresenter userPresenter = new UserPresenter(followingFollowerFragment);
                    GetUserTask userTask = new GetUserTask(userPresenter,  followingFollowerFragment);
                    UserRequest userRequest = new UserRequest((String) userAlias.getText(), user.getUsername());
                    //userRequest.setAuthToken(followingFollowerFragment.authToken);
                    userTask.execute(userRequest);

                    */
/*Intent intent = new Intent(itemView.getContext(), UserViewActivity.class);
                    intent.putExtra(UserViewActivity.USER_KEY, user);
                    intent.putExtra(UserViewActivity.AUTH_TOKEN_KEY, authToken);
                    intent.putExtra(UserViewActivity.MENTIONED_KEY, userName.getText());
                    startActivity(intent);*//*

                });
            } else {
                userImage = null;
                userAlias = null;
                userName = null;
            }
        }

        */
/**
         * Binds the user's data to the view.
         *
         * @param user the user.
         *//*

        void bindUser(User user) {
            //userImage.setImageDrawable(ImageUtils.drawableFromByteArray(user.getImageBytes()));
            //userAlias.setText(user.getAlias());
            //userName.setText(user.getName());
        }
    }

    */
/**
     * The adapter for the RecyclerView that displays the Following data.
     *//*

    */
/*private class FollowingRecyclerViewAdapter extends RecyclerView.Adapter<FollowingHolder> implements GetFollowingFollowersTask.Observer {

        private FollowingFollowerFragment followingFollowerFragment;
        private final List<User> users = new ArrayList<>();

        private User lastFollowee;

        private boolean hasMorePages;
        private boolean isLoading = false;
        private boolean isFollowing = false;

        *//*
*/
/**
         * Creates an instance and loads the first page of following data.
         *//*
*/
/*
        FollowingRecyclerViewAdapter(FollowingFollowerFragment followingFollowerFragment) {
            this.followingFollowerFragment = followingFollowerFragment;
            loadMoreItems();
        }

        *//*
*/
/**
         * Adds new users to the list from which the RecyclerView retrieves the users it displays
         * and notifies the RecyclerView that items have been added.
         *
         * @param newUsers the users to add.
         *//*
*/
/*
        void addItems(List<User> newUsers) {
            int startInsertPosition = users.size();
            users.addAll(newUsers);
            this.notifyItemRangeInserted(startInsertPosition, newUsers.size());
        }

        *//*
*/
/**
         * Adds a single user to the list from which the RecyclerView retrieves the users it
         * displays and notifies the RecyclerView that an item has been added.
         *
         * @param user the user to add.
         *//*
*/
/*
        void addItem(User user) {
            users.add(user);
            this.notifyItemInserted(users.size() - 1);
        }

        *//*
*/
/**
         * Removes a user from the list from which the RecyclerView retrieves the users it displays
         * and notifies the RecyclerView that an item has been removed.
         *
         * @param user the user to remove.
         *//*
*/
/*
        void removeItem(User user) {
            int position = users.indexOf(user);
            users.remove(position);
            this.notifyItemRemoved(position);
        }

        *//*
*/
/**
         *  Creates a view holder for a followee/follower to be displayed in the RecyclerView or for a message
         *  indicating that new rows are being loaded if we are waiting for rows to load.
         *
         * @param parent the parent view.
         * @param viewType the type of the view (ignored in the current implementation).
         * @return the view holder.
         *//*
*/
/*
        @NonNull
        @Override
        public FollowingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(FollowingFollowerFragment.this.getContext());
            View view;

            if(viewType == LOADING_DATA_VIEW) {
                view =layoutInflater.inflate(R.layout.loading_row, parent, false);

            } else {
                view = layoutInflater.inflate(R.layout.user_row, parent, false);
            }

            return new FollowingHolder(followingFollowerFragment, view, viewType);
        }

        *//*
*/
/**
         * Binds the followee/follower at the specified position unless we are currently loading new data. If
         * we are loading new data, the display at that position will be the data loading footer.
         *
         * @param followingHolder the ViewHolder to which the followee/follower should be bound.
         * @param position the position (in the list of followees/followers) that contains the followee to be
         *                 bound.
         *//*
*/
/*
        @Override
        public void onBindViewHolder(@NonNull FollowingHolder followingHolder, int position) {
            if(!isLoading) {
                followingHolder.bindUser(users.get(position));
            }
        }

        *//*
*/
/**
         * Returns the current number of followees/followers available for display.
         * @return the number of followees/followers available for display.
         *//*
*/
/*
        @Override
        public int getItemCount() {
            return users.size();
        }

        *//*
*/
/**
         * Returns the type of the view that should be displayed for the item currently at the
         * specified position.
         *
         * @param position the position of the items whose view type is to be returned.
         * @return the view type.
         *//*
*/
/*
        @Override
        public int getItemViewType(int position) {
            return (position == users.size() - 1 && isLoading) ? LOADING_DATA_VIEW : ITEM_VIEW;
        }

        *//*
*/
/**
         * Causes the Adapter to display a loading footer and make a request to get more following
         * data.
         *//*
*/
/*
        void loadMoreItems() {
            isLoading = true;
            addLoadingFooter();

            GetFollowingFollowersTask getFollowingTask = new GetFollowingFollowersTask(presenter, this);
            FollowingFollowersRequest request = new FollowingFollowersRequest(user.getUsername(), PAGE_SIZE, (lastFollowee == null ? null : lastFollowee.getUsername()), this.isFollowing);
            //request.setAuthToken(this.followingFollowerFragment.authToken);
            getFollowingTask.execute(request);
        }

        *//*
*/
/**
         * A callback indicating more following data has been received. Loads the new followees/followers
         * and removes the loading footer.
         *
         * @param followingFollowersResponse the asynchronous response to the request to load more items.
         *//*
*/
/*
        @Override
        public void followeesRetrieved(FollowingFollowersResponse followingFollowersResponse) {
            List<User> followees = followingFollowersResponse.getFollowees();

            lastFollowee = (followees.size() > 0) ? followees.get(followees.size() -1) : null;
            hasMorePages = followingFollowersResponse.getHasMorePages();

            isLoading = false;
            removeLoadingFooter();
            followingRecyclerViewAdapter.addItems(followees);
        }

        *//*
*/
/**
         * A callback indicating that an exception was thrown by the presenter.
         *
         * @param exception the exception.
         *//*
*/
/*
        @Override
        public void handleException(Exception exception) {
            Log.e(LOG_TAG, exception.getMessage(), exception);
            removeLoadingFooter();
            Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            if (Objects.equals(exception.getMessage(), "Unauthorized")) {
                this.followingFollowerFragment.getActivity().finish();
            }
        }

        *//*
*/
/**
         * Adds a dummy user to the list of users so the RecyclerView will display a view (the
         * loading footer view) at the bottom of the list.
         *//*
*/
/*
        private void addLoadingFooter() {
            addItem(new User("Dummy", "User", ""));
        }

        *//*
*/
/**
         * Removes the dummy user from the list of users so the RecyclerView will stop displaying
         * the loading footer at the bottom of the list.
         *//*
*/
/*
        private void removeLoadingFooter() {
            removeItem(users.get(users.size() - 1));
        }
    }*//*


    */
/**
     * A scroll listener that detects when the user has scrolled to the bottom of the currently
     * available data.
     *//*

    private class FollowRecyclerViewPaginationScrollListener extends RecyclerView.OnScrollListener {

        private final LinearLayoutManager layoutManager;

        */
/**
         * Creates a new instance.
         *
         * @param layoutManager the layout manager being used by the RecyclerView.
         *//*

        FollowRecyclerViewPaginationScrollListener(LinearLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }

        */
/**
         * Determines whether the user has scrolled to the bottom of the currently available data
         * in the RecyclerView and asks the adapter to load more data if the last load request
         * indicated that there was more data to load.
         *
         * @param recyclerView the RecyclerView.
         * @param dx the amount of horizontal scroll.
         * @param dy the amount of vertical scroll.
         *//*

       */
/* @Override
        public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!followingRecyclerViewAdapter.isLoading && followingRecyclerViewAdapter.hasMorePages) {
                if ((visibleItemCount + firstVisibleItemPosition) >=
                        totalItemCount && firstVisibleItemPosition >= 0) {
                    followingRecyclerViewAdapter.loadMoreItems();
                }
            }
        }*//*

    }
}
*/
