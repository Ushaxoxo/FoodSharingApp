package com.example.android.fud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class OpeningActivity extends AppCompatActivity {

    ImageButton btnNgo, btnUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        btnNgo = findViewById(R.id.btnNgo);
        btnUser = findViewById(R.id.btnUser);

        btnNgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ngoIntent = new Intent(OpeningActivity.this, NgoAuthActivity.class);
                startActivity(ngoIntent);
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userIntent = new Intent(OpeningActivity.this, LoginActivity.class);
                startActivity(userIntent);
            }
        });
    }
}
