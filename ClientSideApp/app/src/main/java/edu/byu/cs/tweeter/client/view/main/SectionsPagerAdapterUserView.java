package edu.byu.cs.tweeter.client.view.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.view.main.tabs.FollowingFollowerFragment;
import edu.byu.cs.tweeter.client.view.main.tabs.StatusListFragment;
import edu.byu.cs.tweeter.shared.model.domain.AuthToken;
import edu.byu.cs.tweeter.shared.model.domain.User;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to one of the sections/tabs/pages
 * of the UserView Activity.
 */
class SectionsPagerAdapterUserView extends FragmentPagerAdapter {

    private static final int STORY_FRAGMENT_POSITION = 0;
    private static final int FOLLOWING_FRAGMENT_POSITION = 1;
    private static final int FOLLOWERS_FRAGMENT_POSITION = 2;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.storyTabTitle, R.string.followingTabTitle, R.string.followersTabTitle};
    private final Context mContext;
    private final User user;
    private final AuthToken authToken;

    public SectionsPagerAdapterUserView(Context context, FragmentManager fm, User user, AuthToken authToken) {
        super(fm);
        mContext = context;
        this.user = user;
        this.authToken = authToken;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == STORY_FRAGMENT_POSITION) {
            return StatusListFragment.newInstance(user, authToken, false);
        } else if (position == FOLLOWING_FRAGMENT_POSITION ) {
            return FollowingFollowerFragment.newInstance(user, authToken, true);
        } else if(position == FOLLOWERS_FRAGMENT_POSITION) {
            return FollowingFollowerFragment.newInstance(user, authToken, false);
        } else {
            return PlaceholderFragment.newInstance(position + 1);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]); //skips feed
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}