package com.example.android.fud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Context;
import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

public class LoginAdapter extends FragmentPagerAdapter {

    // Creating Adapter Class to switch between Fragments
    private Context mContext;

    public LoginAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new LoginTabFragment();
        } else {
            return new LSignUpTabFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
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
