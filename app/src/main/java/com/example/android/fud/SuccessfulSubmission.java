package com.example.android.fud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class SuccessfulSubmission extends AppCompatActivity {

    Button RtnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_submission);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // For Vibrations
        Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        RtnHome = findViewById(R.id.rtn_btn);
        RtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(80);
                Intent intent  = new Intent(SuccessfulSubmission.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}