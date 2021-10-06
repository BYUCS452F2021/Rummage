package edu.byu.cs.tweeter.client.view.login;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.view.login.tabs.SignInFragment;
import edu.byu.cs.tweeter.client.view.login.tabs.SignUpFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the tabs of the Login Activity.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int SIGN_IN_FRAGMENT_INDEX = 0;
    private static final int SIGN_UP_FRAGMENT_INDEX = 1;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.signInTabTitle, R.string.signUpTabTitle};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int index) {
        if (index == SIGN_IN_FRAGMENT_INDEX) {
            return SignInFragment.newInstance();
            // return PlaceholderFragment.newInstance(index + 1);
        } else if (index == SIGN_UP_FRAGMENT_INDEX) {
            return SignUpFragment.newInstance();
            // return PlaceholderFragment.newInstance(index + 1);
        } else {
            throw new UnsupportedOperationException("only 2 tabs are supported");
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

}