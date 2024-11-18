package com.example.android.fud;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NgoLoginActivity extends AppCompatActivity {

    private EditText usernameField, passwordField;
    private Button loginButton, goToRegisterButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_login);

        dbHelper = new DatabaseHelper(this);

        usernameField = findViewById(R.id.usernameField);
        passwordField = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);
        goToRegisterButton = findViewById(R.id.goToRegisterButton);

        loginButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            if (dbHelper.authenticateNgo(username, password)) {
                Toast.makeText(this, "NGO Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, NgoDashboardActivity.class)); // Navigate to NGO Dashboard
            } else {
                Toast.makeText(this, "Invalid NGO Credentials", Toast.LENGTH_SHORT).show();
            }
        });

        goToRegisterButton.setOnClickListener(v -> {
            startActivity(new Intent(this, NgoRegisterActivity.class)); // Navigate to NGO Registration
        });
    }
}
