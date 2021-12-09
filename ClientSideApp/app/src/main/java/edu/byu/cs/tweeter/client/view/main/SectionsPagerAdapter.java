package edu.byu.cs.tweeter.client.view.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import edu.byu.cs.tweeter.R;
/*
import edu.byu.cs.tweeter.client.view.main.tabs.FollowingFollowerFragment;
*/
import edu.byu.cs.tweeter.client.view.main.tabs.SaleListFragment;
/*import edu.byu.cs.tweeter.shared.model.domain.AuthToken;*/
import edu.byu.cs.tweeter.shared.model.domain.User;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to one of the sections/tabs/pages
 * of the Main Activity.
 * This controls the switching between different fragments or pages
 */
class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int ALL_SALES_FRAGMENT_POSITION = 0;
    private static final int SALE_MAP_FRAGMENT_POSITION = 1;
    private static final int FOLLOWED_SALES_FRAGMENT_POSITION = 2;
    private static final int FOLLOWED_MAP_FRAGMENT_POSITION = 3;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.feedTabTitle, R.string.storyTabTitle, R.string.followingTabTitle, R.string.followersTabTitle};
    private final Context mContext;
    private final User user;
    /*private final AuthToken authToken;*/

    public SectionsPagerAdapter(Context context, FragmentManager fm, User user/*, AuthToken authToken*/) {
        super(fm);
        mContext = context;
        this.user = user;
        /*this.authToken = authToken;*/
    }

    @Override
    public Fragment getItem(int position) {

        if (position == ALL_SALES_FRAGMENT_POSITION) {
            return SaleListFragment.newInstance(user, false);
        } else if (position == SALE_MAP_FRAGMENT_POSITION) {
            return PlaceholderFragment.newInstance(position + 1);
        } else if (position == FOLLOWED_SALES_FRAGMENT_POSITION ) {
            return SaleListFragment.newInstance(user, true);
        } else if(position == FOLLOWED_MAP_FRAGMENT_POSITION) {
            return PlaceholderFragment.newInstance(position + 1);
        } else {
            return PlaceholderFragment.newInstance(position + 1);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}