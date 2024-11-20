package com.example.android.fud;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class NgoAuthActivity extends AppCompatActivity {

    FloatingActionButton fb, google, github;
    float v = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        setContentView(R.layout.activity_ngo_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        TabLayout tabLayout = findViewById(R.id.tab_layout_ngo);
        ViewPager viewPager = findViewById(R.id.view_pager_ngo);
        fb = findViewById(R.id.fab_facebook);
        google = findViewById(R.id.fab_google);
        github = findViewById(R.id.fab_twitter);

        NgoLoginAdapter adapter = new NgoLoginAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setAnimations();

        google.setOnClickListener(v ->
                Toast.makeText(NgoAuthActivity.this, "Feature Coming soon!\nPlease SignUp through Email\nand Password", Toast.LENGTH_SHORT).show()
        );

        github.setOnClickListener(v ->
                Toast.makeText(NgoAuthActivity.this, "Feature Coming soon!\nPlease SignUp through Email\nand Password", Toast.LENGTH_SHORT).show()
        );

        fb.setOnClickListener(v ->
                Toast.makeText(NgoAuthActivity.this, "Feature Coming soon!\nPlease SignUp through Email\nand Password", Toast.LENGTH_SHORT).show()
        );
    }

    public void setAnimations() {
        fb.setTranslationY(300);
        google.setTranslationY(300);
        github.setTranslationY(300);

        fb.setAlpha(v);
        google.setAlpha(v);
        github.setAlpha(v);

        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        github.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
    }
}
