package edu.byu.cs.tweeter.client.view.main.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.SalesListPresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.GetUserTask;
/*import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.Status;*/
import edu.byu.cs.tweeter.shared.model.domain.User;
//import edu.byu.cs.tweeter.client.presenter.StatusListPresenter;
//import edu.byu.cs.tweeter.client.view.asyncTasks.GetStatusTask;
/*
import edu.byu.cs.tweeter.client.view.main.UserViewActivity;
*/

//import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
//import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;
import edu.byu.cs.tweeter.shared.model.service.response.UserResponse;

/**
 * The fragment that displays on the 'Feed' and 'Story' tab.
 */
public class SaleListFragment extends Fragment implements SalesListPresenter.View, GetUserTask.Observer {
    private static final String LOG_TAG = "SaleListFragment";
    private static final String USER_KEY = "UserKey";
/*
    private static final String AUTH_TOKEN_KEY = "AuthTokenKey";
*/
    private static final String IS_FEED_KEY = "IsFeedKey";
    private static final int PAGE_SIZE = 10;

    private static final int LOADING_DATA_VIEW = 0;
    private static final int ITEM_VIEW = 1;

    private boolean isFeed;
    private User user;
    /*private AuthToken authToken;*/
    private SalesListPresenter presenter;
    /*private StatusRecyclerViewAdapter statusRecyclerViewAdapter;*/


    boolean isFeed() {
        return isFeed;
    }

    User getUser() {
        return user;
    }

    SalesListPresenter getPresenter() {
        return presenter;
    }

/*
    StatusRecyclerViewAdapter getStatusRecyclerViewAdapter() {
        return statusRecyclerViewAdapter;
    }
*/

    /**
     * Creates an instance of the fragment and places the status and auth token in an arguments
     * bundle assigned to the fragment.
     *
     * @param user      the logged in user.
     * @ param authToken the auth token for this status's session.
     * @param isFollowList    whether this fragment displays feed or story.
     * @return the fragment.
     */
    public static SaleListFragment newInstance(User user, boolean isFollowList) {

//        URLSpan urlSpan = new URLSpan("https://ocremix.org/") {
//            @Override
//            public void onClick(View widget) {
//                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
//                urlIntent.setData(Uri.parse(getURL()));
//                startActivity(urlIntent);
//            }
//        };

        SaleListFragment fragment = new SaleListFragment();

        Bundle args = new Bundle(3);
        args.putSerializable(USER_KEY, user);
        /*args.putSerializable(AUTH_TOKEN_KEY, authToken);*/
        args.putBoolean(IS_FEED_KEY, isFollowList);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statuses, container, false);

        //noinspection ConstantConditions
        user = (User) getArguments().getSerializable(USER_KEY);
/*
        authToken = (AuthToken) getArguments().getSerializable(AUTH_TOKEN_KEY);
*/
        isFeed = getArguments().getBoolean(IS_FEED_KEY);

        /*presenter = new StatusListPresenter(this);*/

        RecyclerView statusListRecyclerView = view.findViewById(R.id.statusListRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        statusListRecyclerView.setLayoutManager(layoutManager);

        /*statusRecyclerViewAdapter = new StatusRecyclerViewAdapter(this);
        statusListRecyclerView.setAdapter(statusRecyclerViewAdapter);

        statusListRecyclerView.addOnScrollListener(new SaleListFragment.FeedRecyclerViewPaginationScrollListener(layoutManager));*/

        return view;
    }

    @Override
    public void userRetrieved(UserResponse userResponse) {
        Intent intent = new Intent();/*this.getContext(), UserViewActivity.class);
        intent.putExtra(UserViewActivity.USER_KEY, user);
        intent.putExtra(UserViewActivity.AUTH_TOKEN_KEY, authToken);
        intent.putExtra(UserViewActivity.MENTIONED_KEY, userResponse.getUser());
        intent.putExtra(UserViewActivity.IS_FOLLOWING_KEY, userResponse.getIsFollowing());
        intent.putExtra(UserViewActivity.USER_IMAGE, userResponse.getUser().getImageBytes());*/
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

   /* *//**
     * The ViewHolder for the RecyclerView that displays the Feed data.
     *//*
    private class StatusListHolder extends RecyclerView.ViewHolder {
        private final SaleListFragment statusesFragment;
        private final ImageView authorImage;
        private final TextView authorAlias;
        private final TextView postDate;
        private final TextView statusContent;


        *//**
         * Creates an instance and sets an OnClickListener for the status's row.
         *
         * @param itemView the view on which the user will be displayed.
         *//*
        StatusListHolder(SaleListFragment statusListFragment, @NonNull View itemView, int viewType) {
            super(itemView);
            this.statusesFragment = statusListFragment;

            authorImage = itemView.findViewById(R.id.authorImage);
            authorAlias = itemView.findViewById(R.id.authorAlias);
            postDate = itemView.findViewById(R.id.postDate);

            statusContent = itemView.findViewById(R.id.statusContent);

            //itemView.setOnClickListener(
            //        view -> Toast.makeText(StatusListHolder.this.statusesFragment.getContext(),
            //                "You selected '" + statusContent.getText() + "'.", Toast.LENGTH_SHORT).show()
            //);
        }


        *//**
         * Binds the status's data to the view.
         *
         * @param status the status.
         *//*
        void bindStatus(Status status) {
            authorImage.setImageDrawable(ImageUtils.drawableFromByteArray(status.getPoster().getImageBytes()));
            String author = status.getPoster().getAlias();
            authorAlias.setText(author);
            applyMentionsToAuthor(author, authorAlias);
            postDate.setText(status.generateDate().toString());

            String stringToEdit = status.getMessage();
            statusContent.setText(Html.fromHtml(stringToEdit));
            applyMentionsToMessage(stringToEdit, statusContent);
        }

        private boolean IsValidUrl(String urlString) {
            try {
                URL url = new URL(urlString);
                return URLUtil.isValidUrl(urlString) && Patterns.WEB_URL.matcher(urlString).matches();
            } catch (MalformedURLException ignored) {
            }
            return false;
        }

        private void applyMentionsToAuthor(String stringToModify, TextView view) {

            List<Pair<String, View.OnClickListener>> links = new ArrayList<>();

            final String mention = stringToModify;
            links.add(new Pair<>(stringToModify, v -> {
                UserPresenter userPresenter = new UserPresenter(statusesFragment);
                GetUserTask userTask = new GetUserTask(userPresenter, statusesFragment);
                UserRequest userRequest = new UserRequest(mention, user.getAlias());
                userRequest.setAuthToken(this.statusesFragment.authToken);
                userTask.execute(userRequest);

                *//*Intent intent = new Intent(statusesFragment.getContext(), UserViewActivity.class);
                intent.putExtra(UserViewActivity.USER_KEY, user);
                intent.putExtra(UserViewActivity.AUTH_TOKEN_KEY, authToken);
                intent.putExtra(UserViewActivity.MENTIONED_KEY, mention);
                startActivity(intent);*//*
            }));

            SpanningLinksUtils.makeLinks(view, links);
        }

        private void applyMentionsToMessage(String stringToModify, TextView view) {

            List<Pair<String, View.OnClickListener>> links = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(stringToModify);
            while (st.hasMoreTokens()) {
                String currentString = st.nextToken();
                if (currentString.startsWith("@")) {
                    while (currentString.endsWith("?") || currentString.endsWith(".") || currentString.endsWith(",") || currentString.endsWith("!")) {
                        currentString = currentString.substring(0, currentString.length()-1);
                    }
                    final String mention = currentString.substring(1);
                    links.add(new Pair<>(currentString, v -> {
                        UserPresenter userPresenter = new UserPresenter(statusesFragment);
                        GetUserTask userTask = new GetUserTask(userPresenter, statusesFragment);
                        UserRequest userRequest = new UserRequest(mention, user.getAlias());
                        userRequest.setAuthToken(this.statusesFragment.authToken);
                        userTask.execute(userRequest);

                        *//*Intent intent = new Intent(statusesFragment.getContext(), UserViewActivity.class);
                        intent.putExtra(UserViewActivity.USER_KEY, user);
                        intent.putExtra(UserViewActivity.AUTH_TOKEN_KEY, authToken);
                        intent.putExtra(UserViewActivity.MENTIONED_KEY, mention);
                        startActivity(intent);*//*
                    }));
                }

                if (IsValidUrl(currentString)) {
                    Uri link = Uri.parse(currentString);
                    links.add(new Pair<>(currentString, v -> {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, link);
                        startActivity(browserIntent);
                    }));
                }
            }

            SpanningLinksUtils.makeLinks(view, links);
        }
    }

    *//**
     * The adapter for the RecyclerView that displays the Feed data.
     *//*
    private class StatusRecyclerViewAdapter extends RecyclerView.Adapter<StatusListHolder> implements GetStatusTask.Observer {

        private SaleListFragment statusListFragment;
        private final List<Status> statuses = new ArrayList<>();

        private Status lastStatus;

        private boolean hasMorePages;
        private boolean isLoading = false;


        boolean hasMorePages() {
            return hasMorePages;
        }

        boolean isLoading() {
            return isLoading;
        }

        *//**
         * Creates an instance and loads the first page of feed data.
         *//*
        StatusRecyclerViewAdapter(SaleListFragment statusesFragment) {
            this.statusListFragment = statusesFragment;
            loadMoreItems();
        }

        *//**
         * Adds new statuses to the list from which the RecyclerView retrieves the statuses it
         * displays and notifies the RecyclerView that items have been added.
         *
         * @param newStatuses the users to add.
         *//*
        void addItems(List<Status> newStatuses) {
            int startInsertPosition = statuses.size();
            statuses.addAll(newStatuses);
            this.notifyItemRangeInserted(startInsertPosition, newStatuses.size());
        }

        *//**
         * Adds a single status to the list from which the RecyclerView retrieves the statuses it
         * displays and notifies the RecyclerView that an item has been added.
         *
         * @param status the status to add.
         *//*
        void addItem(Status status) {
            statuses.add(status);
            this.notifyItemInserted(statuses.size() - 1);
        }

        *//**
         * Removes a status from the list from which the RecyclerView retrieves the status it displays
         * and notifies the RecyclerView that an item has been removed.
         *
         * @param status the status to remove.
         *//*
        void removeItem(Status status) {
            int position = statuses.indexOf(status);
            statuses.remove(position);
            this.notifyItemRemoved(position);
        }

        *//**
         *  Creates a view holder for a status to be displayed in the RecyclerView or for a message
         *  indicating that new rows are being loaded if we are waiting for rows to load.
         *
         * @param parent the parent view.
         * @param viewType the type of the view (ignored in the current implementation).
         * @return the view holder.
         *//*
        @NonNull
        @Override
        public StatusListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(SaleListFragment.this.getContext());
            View view;

            if (viewType == LOADING_DATA_VIEW) {
                view = layoutInflater.inflate(R.layout.loading_row, parent, false);

            } else {
                view = layoutInflater.inflate(R.layout.status_row, parent, false);
            }

            return new StatusListHolder(statusListFragment, view, viewType);
        }

        *//**
         * Binds the status at the specified position unless we are currently loading new data. If
         * we are loading new data, the display at that position will be the data loading footer.
         *
         * @param statusListHolder the ViewHolder to which the status should be bound.
         * @param position the position (in the list of statuses) that contains the status to be
         *                 bound.
         *//*
        @Override
        public void onBindViewHolder(@NonNull StatusListHolder statusListHolder, int position) {
            if(!isLoading) {
                statusListHolder.bindStatus(statuses.get(position));
            }
        }

        *//**
         * Returns the current number of statuses available for display.
         * @return the number of statuses available for display.
         *//*
        @Override
        public int getItemCount() {
            return statuses.size();
        }

        *//**
         * Returns the type of the view that should be displayed for the item currently at the
         * specified position.
         *
         * @param position the position of the items whose view type is to be returned.
         * @return the view type.
         *//*
        @Override
        public int getItemViewType(int position) {
            return (position == statuses.size() - 1 && isLoading) ? LOADING_DATA_VIEW : ITEM_VIEW;
        }

        *//**
         * Causes the Adapter to display a loading footer and make a request to get more following
         * data.
         *//*
        void loadMoreItems() {
            isLoading = true;
            addLoadingFooter();

            GetStatusTask getStatusTask = new GetStatusTask(this, statusListFragment.getPresenter());
            boolean isFeed = statusListFragment.isFeed();
            StatusListRequest request = new StatusListRequest(statusListFragment.getUser().getAlias(), SaleListFragment.PAGE_SIZE, lastStatus, isFeed);
            request.setAuthToken(statusListFragment.authToken);
            getStatusTask.execute(request);

        }

        *//**
         * A callback indicating more story data has been received. Loads the new statuses
         * and removes the loading footer.
         *
         * @param statusListResponse the asynchronous response to the request to load more items.
         *//*
        @Override
        public void statusesRetrieved(StatusListResponse statusListResponse) {
            List<Status> statuses = statusListResponse.getYardSaleList();

            lastStatus = (statuses.size() > 0) ? statuses.get(statuses.size() -1) : null;
            hasMorePages = statusListResponse.getHasMorePages();

            isLoading = false;
            removeLoadingFooter();
            statusRecyclerViewAdapter.addItems(statuses);
        }

        *//**
         * A callback indicating that an exception was thrown by the presenter.
         *
         * @param exception the exception.
         *//*
        @Override
        public void handleException(Exception exception) {
            Log.e(LOG_TAG, exception.getMessage(), exception);
            removeLoadingFooter();
            Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            if (Objects.equals(exception.getMessage(), "Unauthorized")) {
                this.statusListFragment.getActivity().finish();
            }
        }

        *//**
         * Adds a dummy user to the list of statuses so the RecyclerView will display a view (the
         * loading footer view) at the bottom of the list.
         *//*
        private void addLoadingFooter() {
            addItem(new Status("", null, null));
        }

        *//**
         * Removes the dummy status from the list of statuses so the RecyclerView will stop displaying
         * the loading footer at the bottom of the list.
         *//*
        private void removeLoadingFooter() {
            removeItem(statuses.get(statuses.size() - 1));
        }
    }

    *//**
     * A scroll listener that detects when the user has scrolled to the bottom of the currently
     * available data.
     *//*
    private class FeedRecyclerViewPaginationScrollListener extends RecyclerView.OnScrollListener {

        private final LinearLayoutManager layoutManager;

        *//**
         * Creates a new instance.
         *
         * @param layoutManager the layout manager being used by the RecyclerView.
         *//*
        FeedRecyclerViewPaginationScrollListener(LinearLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }

        *//**
         * Determines whether the user has scrolled to the bottom of the currently available data
         * in the RecyclerView and asks the adapter to load more data if the last load request
         * indicated that there was more data to load.
         *
         * @param recyclerView the RecyclerView.
         * @param dx           the amount of horizontal scroll.
         * @param dy           the amount of vertical scroll.
         *//*
        @Override
        public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!statusRecyclerViewAdapter.isLoading && statusRecyclerViewAdapter.hasMorePages) {
                if ((visibleItemCount + firstVisibleItemPosition) >=
                        totalItemCount && firstVisibleItemPosition >= 0) {
                    statusRecyclerViewAdapter.loadMoreItems();
                }
            }
        }
    }*/
}

