package com.example.android.fud;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NgoRegisterActivity extends AppCompatActivity {

    private EditText usernameField, passwordField;
    private Button registerButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_register);

        dbHelper = new DatabaseHelper(this);

        usernameField = findViewById(R.id.usernameField);
        passwordField = findViewById(R.id.passwordField);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            if (dbHelper.registerUser(username, password, true)) { // true for NGO
                Toast.makeText(this, "NGO Registration Successful", Toast.LENGTH_SHORT).show();
                finish(); // Go back to login
            } else {
                Toast.makeText(this, "NGO Registration Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
