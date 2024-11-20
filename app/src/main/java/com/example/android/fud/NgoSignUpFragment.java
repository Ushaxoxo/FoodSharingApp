package com.example.android.fud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NgoSignUpFragment extends Fragment {

    private EditText usernameField, passwordField;
    private Button signupButton;
    private ProgressBar progressBar;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_tab_ngo, container, false);

        usernameField = view.findViewById(R.id.email); // Match with the ID in fragment_signup_tab_ngo.xml
        passwordField = view.findViewById(R.id.pass);
        signupButton = view.findViewById(R.id.signup_button);
        progressBar = view.findViewById(R.id.signup_progress);

        dbHelper = new DatabaseHelper(getContext());
        progressBar.setVisibility(View.GONE);

        signupButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                progressBar.setVisibility(View.VISIBLE);
                boolean isRegistered = dbHelper.registerUser(username, password, true); // Register as NGO
                progressBar.setVisibility(View.GONE);

                if (isRegistered) {
                    Toast.makeText(getContext(), "NGO Registration Successful", Toast.LENGTH_SHORT).show();
                    // Optionally navigate back to the login screen or dashboard
                } else {
                    Toast.makeText(getContext(), "Registration Failed. Try a different username.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}