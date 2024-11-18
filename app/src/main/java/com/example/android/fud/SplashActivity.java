package com.example.android.fud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import org.jetbrains.annotations.NotNull;

public class SplashActivity extends AppCompatActivity {

    ImageView logo, splashImg;
    TextView appName;
    LottieAnimationView burgerAnim;

    // For Onboarding activity
    private  static final int NUM_PAGES = 3;
    private ViewPager viewPager;
    private ScreenSlidePageAdapter pagerAdapter;

    // Fade In LiquidPager
    Animation anim1;

    private static int SPLASH_TIME_OUT = 5000;
    SharedPreferences mSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TO hide the status bar (which contains charging ana all)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        logo = findViewById(R.id.app_logo);
        appName = findViewById(R.id.app_name);
        splashImg = findViewById(R.id.background_image);
        burgerAnim = findViewById(R.id.lottie);


        //OnBoarding
        // Calling the Fragments
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        // Fade In LiquidPager
        anim1 = AnimationUtils.loadAnimation(this, R.anim.liquid_pager_anim);
        viewPager.setAnimation(anim1);


        // Animation in JAVA itself
        splashImg.animate().translationY(-2000).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(1000).setDuration(1000).setStartDelay(4000);
        appName.animate().translationY(1000).setDuration(1000).setStartDelay(4000);
        burgerAnim.animate().translationY(1000).setDuration(1000).setStartDelay(4000);
        // Learning to Use alpha;
        splashImg.setAlpha(1f);
        splashImg.animate()
                .alpha(0f)
                .setDuration(2000)
                .setStartDelay(4000);
        logo.setAlpha(1f);
        logo.animate()
                .alpha(0f)
                .setDuration(2000)
                .setStartDelay(4000);
        appName.setAlpha(1f);
        appName.animate()
                .alpha(0f)
                .setDuration(2000)
                .setStartDelay(4000);
        burgerAnim.setAlpha(1f);
        burgerAnim.animate()
                .alpha(0f)
                .setDuration(2000)
                .setStartDelay(4000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSharedPref = getSharedPreferences("SharedPref", MODE_PRIVATE);
                boolean isFirstTime = mSharedPref.getBoolean("firstTime", true);

                if(isFirstTime){
                    // go to onBoarding Activity (If onBoarding Activity was another activity, here we have the both in the same activity)
                    // As we dont need to go to another activity here we will just set the boolean variable to false

                    SharedPreferences.Editor editor = mSharedPref.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();
                }
                else{
                    // Directly go to Login Activity
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);


    }



    private class ScreenSlidePageAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePageAdapter(@NonNull @NotNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    com.example.android.fud.OnBoardingFragment1 tab1 = new com.example.android.fud.OnBoardingFragment1();
                    return tab1;

                case 1:
                    com.example.android.fud.OnBoardingFragment2 tab2 = new com.example.android.fud.OnBoardingFragment2();
                    return tab2;

                case 2:
                    com.example.android.fud.OnBoardingFragment3 tab3 = new com.example.android.fud.OnBoardingFragment3();
                    return tab3;
            }
            return  null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}