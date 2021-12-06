package edu.byu.cs.tweeter.client.view.main.tabs;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.SalesListPresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.GetSalesTask;
/*import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.Status;*/
import edu.byu.cs.tweeter.client.view.asyncTasks.PostTask;
import edu.byu.cs.tweeter.client.view.main.MainActivity;
import edu.byu.cs.tweeter.shared.model.domain.Sale;
import edu.byu.cs.tweeter.shared.model.domain.User;
import edu.byu.cs.tweeter.shared.model.service.request.FollowedSalesRequest;
import edu.byu.cs.tweeter.shared.model.service.request.PostRequest;
import edu.byu.cs.tweeter.shared.model.service.response.FollowedSalesResponse;

import static edu.byu.cs.tweeter.client.view.main.MainActivity.STATUS_CHAR_LIMIT;
//import edu.byu.cs.tweeter.client.presenter.StatusListPresenter;
//import edu.byu.cs.tweeter.client.view.asyncTasks.GetStatusTask;
/*
import edu.byu.cs.tweeter.client.view.main.UserViewActivity;
*/

//import edu.byu.cs.tweeter.shared.model.service.request.StatusListRequest;
//import edu.byu.cs.tweeter.shared.model.service.response.StatusListResponse;


/**
 * The fragment that displays on the 'Feed' and 'Story' tab.
 */
public class SaleListFragment extends Fragment implements SalesListPresenter.View/*, GetUserTask.Observer*/ {
    private static final String LOG_TAG = "SaleListFragment";
    private static final String USER_KEY = "UserKey";
/*
    private static final String AUTH_TOKEN_KEY = "AuthTokenKey";
*/
    private static final String IS_FOLLOW_LIST_KEY = "IsFeedKey";
    private static final int PAGE_SIZE = 10;

    private static final int LOADING_DATA_VIEW = 0;
    private static final int ITEM_VIEW = 1;

    private boolean isFeed;
    private User user;
    /*private AuthToken authToken;*/
    private SalesListPresenter presenter;
    private SaleRecyclerViewAdapter saleRecyclerViewAdapter;


    boolean isFeed() {
        return isFeed;
    }

    User getUser() {
        return user;
    }

    SalesListPresenter getPresenter() {
        return presenter;
    }


    SaleRecyclerViewAdapter getSaleRecyclerViewAdapter() {
        return saleRecyclerViewAdapter;
    }


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
        args.putBoolean(IS_FOLLOW_LIST_KEY, isFollowList);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales, container, false);

        //noinspection ConstantConditions
        user = (User) getArguments().getSerializable(USER_KEY);
/*
        authToken = (AuthToken) getArguments().getSerializable(AUTH_TOKEN_KEY);
*/
        isFeed = getArguments().getBoolean(IS_FOLLOW_LIST_KEY);

        presenter = new SalesListPresenter(this);

        RecyclerView statusListRecyclerView = view.findViewById(R.id.saleListRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        statusListRecyclerView.setLayoutManager(layoutManager);

        saleRecyclerViewAdapter = new SaleRecyclerViewAdapter(this);
        statusListRecyclerView.setAdapter(saleRecyclerViewAdapter);

        statusListRecyclerView.addOnScrollListener(new FeedRecyclerViewPaginationScrollListener(layoutManager));

        return view;
    }

    //FIXME re-purpose into sale view activity
    /*@Override
    public void userRetrieved(UserResponse userResponse) {
        Intent intent = new Intent();*//*this.getContext(), UserViewActivity.class);
        intent.putExtra(UserViewActivity.USER_KEY, user);
        intent.putExtra(UserViewActivity.AUTH_TOKEN_KEY, authToken);
        intent.putExtra(UserViewActivity.MENTIONED_KEY, userResponse.getUser());
        intent.putExtra(UserViewActivity.IS_FOLLOWING_KEY, userResponse.getIsFollowing());
        intent.putExtra(UserViewActivity.USER_IMAGE, userResponse.getUser().getImageBytes());*//*
        startActivity(intent);
    }*/

   /* @Override
    public void handleException(Exception ex) {
        Log.e(LOG_TAG, ex.getMessage(), ex);
        Toast.makeText(this.getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        if (Objects.equals(ex.getMessage(), "Unauthorized")) {
            this.getActivity().finish();
        }
    }*/

   /**
     * The ViewHolder for the RecyclerView that displays the Feed data.
     */
   private class SaleListHolder extends RecyclerView.ViewHolder {
        private final SaleListFragment statusesFragment;
        /*private final ImageView authorImage;*/
        private final TextView authorAlias;
        private final TextView postDate;
        private final TextView statusContent;
        private final Button edit;
        private final Button delete;
        private final Button follow;
        private AlertDialog.Builder postDialogBuilder;
        private AlertDialog postAlertDialog;
        private int charsUsed = 0;


        /**
         * Creates an instance and sets an OnClickListener for the status's row.
         *
         * @param itemView the view on which the user will be displayed.
         */
        SaleListHolder(SaleListFragment statusListFragment, @NonNull View itemView, int viewType) {
            super(itemView);
            this.statusesFragment = statusListFragment;

            /*authorImage = itemView.findViewById(R.id.authorImage);*/
            authorAlias = itemView.findViewById(R.id.authorAlias);
            postDate = itemView.findViewById(R.id.postDate);

            statusContent = itemView.findViewById(R.id.statusContent);

            edit = itemView.findViewById(R.id.edit_sale);
            delete = itemView.findViewById(R.id.delete_sale);
            follow = itemView.findViewById(R.id.follow_sale);

            //itemView.setOnClickListener(
            //        view -> Toast.makeText(StatusListHolder.this.statusesFragment.getContext(),
            //                "You selected '" + statusContent.getText() + "'.", Toast.LENGTH_SHORT).show()
            //);
        }


        /**
         * Binds the status's data to the view.
         *
         * @param sale the status.
         */
        @SuppressLint("StringFormatInvalid")
        void bindSale(Sale sale) {
            /*authorImage.setImageDrawable(ImageUtils.drawableFromByteArray(sale.getPoster().getImageBytes()));*/
            String author = sale.getUsername();
            authorAlias.setText(author);
            /*applyMentionsToAuthor(author, authorAlias);*/
            postDate.setText(sale.generateDate().toString());

            /*String stringToEdit = sale.getDescription();*/
            statusContent.setText(Html.fromHtml(sale.getDescription()));

            if (author.equals(user.getUsername())) {
                edit.setOnClickListener(fabView -> {

                    EditText statusInput = new EditText(saleRecyclerViewAdapter.saleListFragment.getContext());
                    statusInput.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            // do nothing
                        }

                        @SuppressLint("StringFormatInvalid")
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
                    postDialogBuilder = new AlertDialog.Builder(saleRecyclerViewAdapter.saleListFragment.getContext());
                    postDialogBuilder.setTitle(R.string.postTitle);
                    postDialogBuilder.setMessage(getResources().getString(R.string.postFullMessage,
                            0,
                            STATUS_CHAR_LIMIT));
                    postDialogBuilder.setView(statusInput);
                    postDialogBuilder.setCancelable(false);
                    postDialogBuilder.setPositiveButton(R.string.postPosButton, (dialogInterface, i) -> {
                        // do logic and dismiss
                        /*PostTask postTask = new PostTask(postPresenter, MainActivity.this);
                        PostRequest request = new PostRequest(user.getContactID(), statusInput.getText().toString());
                        //request.setAuthToken(this.authToken);
                        postTask.execute(request);*/
                        //FIXME actually post the updates
                        statusContent.setText(statusInput.getText());

                        dialogInterface.dismiss();
                    });
                    postDialogBuilder.setNegativeButton(R.string.postNegButton, (dialogInterface, i) -> {
                        Toast.makeText(this.statusesFragment.getContext(), R.string.postCancelMessage, Toast.LENGTH_LONG).show();
                        dialogInterface.cancel();
                    });

                    // create the specified dialog
                    postAlertDialog = postDialogBuilder.create();
                    postAlertDialog.show();

                    // finish setup on created dialog
                    postAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
                });

                delete.setOnClickListener(fabView -> {

                    itemView.setVisibility(View.INVISIBLE);
                });

                follow.setVisibility(View.INVISIBLE);
            }
            else {
                edit.setVisibility(View.INVISIBLE);
                delete.setVisibility(View.INVISIBLE);

                follow.setOnClickListener(fabView -> {
                    CharSequence text = follow.getText();
                    if (statusesFragment.isFeed) {
                        itemView.setVisibility(View.INVISIBLE);
                    }
                    else if (text.equals("Follow")) {
                        follow.setText("Unfollow");
                    }
                    else if (text.equals("Unfollow")) {
                        follow.setText("Follow");
                    }
                });
            }

            //FIXME bind a delete to the delete button


            /*applyMentionsToMessage(stringToEdit, statusContent);*/
        }

        /*private boolean IsValidUrl(String urlString) {
            try {
                URL url = new URL(urlString);
                return URLUtil.isValidUrl(urlString) && Patterns.WEB_URL.matcher(urlString).matches();
            } catch (MalformedURLException ignored) {
            }
            return false;
        }*/

        /*private void applyMentionsToAuthor(String stringToModify, TextView view) {

            List<Pair<String, View.OnClickListener>> links = new ArrayList<>();

            final String mention = stringToModify;
            links.add(new Pair<>(stringToModify, v -> {
                UserPresenter userPresenter = new UserPresenter(statusesFragment);
                GetUserTask userTask = new GetUserTask(userPresenter, statusesFragment);
                UserRequest userRequest = new UserRequest(mention, user.getAlias());
                userRequest.setAuthToken(this.statusesFragment.authToken);
                userTask.execute(userRequest);

                Intent intent = new Intent(statusesFragment.getContext(), UserViewActivity.class);
                intent.putExtra(UserViewActivity.USER_KEY, user);
                intent.putExtra(UserViewActivity.AUTH_TOKEN_KEY, authToken);
                intent.putExtra(UserViewActivity.MENTIONED_KEY, mention);
                startActivity(intent);
            }));

            SpanningLinksUtils.makeLinks(view, links);
        }*/

        /*private void applyMentionsToMessage(String stringToModify, TextView view) {

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

                        Intent intent = new Intent(statusesFragment.getContext(), UserViewActivity.class);
                        intent.putExtra(UserViewActivity.USER_KEY, user);
                        intent.putExtra(UserViewActivity.AUTH_TOKEN_KEY, authToken);
                        intent.putExtra(UserViewActivity.MENTIONED_KEY, mention);
                        startActivity(intent);
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
        }*/
    }

    /**
     * The adapter for the RecyclerView that displays the Feed data.
     */
    private class SaleRecyclerViewAdapter extends RecyclerView.Adapter<SaleListHolder> implements GetSalesTask.Observer {

        private SaleListFragment saleListFragment;
        private final List<Sale> sales = new ArrayList<>();

        private Sale lastSale;

        private boolean hasMorePages;
        private boolean isLoading = false;


        boolean hasMorePages() {
            return hasMorePages;
        }

        boolean isLoading() {
            return isLoading;
        }

        /**
         * Creates an instance and loads the first page of feed data.
         */
        SaleRecyclerViewAdapter(SaleListFragment statusesFragment) {
            this.saleListFragment = statusesFragment;
            loadMoreItems();
        }

        /**
         * Adds new statuses to the list from which the RecyclerView retrieves the statuses it
         * displays and notifies the RecyclerView that items have been added.
         *
         * @param newSales the users to add.
         */
        void addItems(List<Sale> newSales) {
            int startInsertPosition = sales.size();
            sales.addAll(newSales);
            this.notifyItemRangeInserted(startInsertPosition, newSales.size());
        }

        /**
         * Adds a single status to the list from which the RecyclerView retrieves the statuses it
         * displays and notifies the RecyclerView that an item has been added.
         *
         * @param sale the status to add.
         */
        void addItem(Sale sale) {
            sales.add(sale);
            this.notifyItemInserted(sales.size() - 1);
        }

        /**
         * Removes a status from the list from which the RecyclerView retrieves the status it displays
         * and notifies the RecyclerView that an item has been removed.
         *
         * @param status the status to remove.
         */
        void removeItem(Sale status) {
            int position = sales.indexOf(status);
            sales.remove(position);
            this.notifyItemRemoved(position);
        }

        /**
         *  Creates a view holder for a status to be displayed in the RecyclerView or for a message
         *  indicating that new rows are being loaded if we are waiting for rows to load.
         *
         * @param parent the parent view.
         * @param viewType the type of the view (ignored in the current implementation).
         * @return the view holder.
         */
        @NonNull
        @Override
        public SaleListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(SaleListFragment.this.getContext());
            View view;

            if (viewType == LOADING_DATA_VIEW) {
                view = layoutInflater.inflate(R.layout.loading_row, parent, false);

            } else {
                view = layoutInflater.inflate(R.layout.sale_row, parent, false);
            }

            return new SaleListHolder(saleListFragment, view, viewType);
        }

        /**
         * Binds the status at the specified position unless we are currently loading new data. If
         * we are loading new data, the display at that position will be the data loading footer.
         *
         * @param statusListHolder the ViewHolder to which the status should be bound.
         * @param position the position (in the list of statuses) that contains the status to be
         *                 bound.
         */
        @Override
        public void onBindViewHolder(@NonNull SaleListHolder statusListHolder, int position) {
            if(!isLoading) {
                statusListHolder.bindSale(sales.get(position));
            }
        }

        /**
         * Returns the current number of statuses available for display.
         * @return the number of statuses available for display.
         */
        @Override
        public int getItemCount() {
            return sales.size();
        }

        /**
         * Returns the type of the view that should be displayed for the item currently at the
         * specified position.
         *
         * @param position the position of the items whose view type is to be returned.
         * @return the view type.
         */
        @Override
        public int getItemViewType(int position) {
            return (position == sales.size() - 1 && isLoading) ? LOADING_DATA_VIEW : ITEM_VIEW;
        }

        /**
         * Causes the Adapter to display a loading footer and make a request to get more following
         * data.
         */
        void loadMoreItems() {
            isLoading = true;
            addLoadingFooter();

            GetSalesTask getSalesTask = new GetSalesTask(this, saleListFragment.getPresenter());
            boolean isFeed = saleListFragment.isFeed();
            FollowedSalesRequest request = new FollowedSalesRequest(saleListFragment.getUser().getUsername(), SaleListFragment.PAGE_SIZE, lastSale, isFeed);
            /*request.setAuthToken(saleListFragment.authToken);*/
            getSalesTask.execute(request);

        }

        /**
         * A callback indicating more story data has been received. Loads the new statuses
         * and removes the loading footer.
         *
         * @param statusListResponse the asynchronous response to the request to load more items.
         */
        @Override
        public void salesRetrieved(FollowedSalesResponse statusListResponse) {
            List<Sale> statuses = statusListResponse.getSaleList();

            lastSale = (statuses.size() > 0) ? statuses.get(statuses.size() -1) : null;
            hasMorePages = statusListResponse.getHasMorePages();

            isLoading = false;
            removeLoadingFooter();
            saleRecyclerViewAdapter.addItems(statuses);
        }

        /**
         * A callback indicating that an exception was thrown by the presenter.
         *
         * @param exception the exception.
         */
        @Override
        public void handleException(Exception exception) {
            Log.e(LOG_TAG, exception.getMessage(), exception);
            removeLoadingFooter();
            Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            if (Objects.equals(exception.getMessage(), "Unauthorized")) {
                this.saleListFragment.getActivity().finish();
            }
        }

        /**
         * Adds a dummy user to the list of statuses so the RecyclerView will display a view (the
         * loading footer view) at the bottom of the list.
         */
        private void addLoadingFooter() {
            addItem(new Sale());
        }

        /**
         * Removes the dummy status from the list of statuses so the RecyclerView will stop displaying
         * the loading footer at the bottom of the list.
         */
        private void removeLoadingFooter() {
            removeItem(sales.get(sales.size() - 1));
        }
    }

    /**
     * A scroll listener that detects when the user has scrolled to the bottom of the currently
     * available data.
     */
    private class FeedRecyclerViewPaginationScrollListener extends RecyclerView.OnScrollListener {

        private final LinearLayoutManager layoutManager;

        /**
         * Creates a new instance.
         *
         * @param layoutManager the layout manager being used by the RecyclerView.
         */
        FeedRecyclerViewPaginationScrollListener(LinearLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }

        /**
         * Determines whether the user has scrolled to the bottom of the currently available data
         * in the RecyclerView and asks the adapter to load more data if the last load request
         * indicated that there was more data to load.
         *
         * @param recyclerView the RecyclerView.
         * @param dx           the amount of horizontal scroll.
         * @param dy           the amount of vertical scroll.
         */
        @Override
        public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!saleRecyclerViewAdapter.isLoading && saleRecyclerViewAdapter.hasMorePages) {
                if ((visibleItemCount + firstVisibleItemPosition) >=
                        totalItemCount && firstVisibleItemPosition >= 0) {
                    saleRecyclerViewAdapter.loadMoreItems();
                }
            }
        }
    }
}

