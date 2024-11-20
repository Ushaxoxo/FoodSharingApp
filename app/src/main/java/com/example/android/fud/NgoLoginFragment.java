package com.example.android.fud;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NgoLoginFragment extends Fragment {

    private EditText usernameField, passwordField;
    private Button loginButton;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ngo_login, container, false);

        usernameField = view.findViewById(R.id.email); // Match with the ID in fragment_ngo_login.xml
        passwordField = view.findViewById(R.id.pass);
        loginButton = view.findViewById(R.id.login_button);

        dbHelper = new DatabaseHelper(getContext());

        loginButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString();
            String password = passwordField.getText().toString();

            if (dbHelper.authenticateNgo(username, password)) {
                Toast.makeText(getContext(), "NGO Login Successful", Toast.LENGTH_SHORT).show();
                // Navigate to NGO dashboard activity
                Intent intent = new Intent(getActivity(), NgoDashboardActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "Invalid NGO Credentials", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
