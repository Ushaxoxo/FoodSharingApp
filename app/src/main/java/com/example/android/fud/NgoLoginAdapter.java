package com.example.android.fud;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NgoLoginAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public NgoLoginAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NgoLoginFragment();
        } else {
            return new NgoSignUpFragment();
        }
    }

    @Override
    public int getCount() {
        return 2; // Two tabs: Login and Signup
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.login);
        } else {
            return mContext.getString(R.string.signup);
        }
    }
}
