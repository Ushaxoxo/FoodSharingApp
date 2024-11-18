package com.example.android.fud;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class LoginTabFragment extends Fragment {

    EditText email, pass;
    TextView forgetPass;
    AppCompatButton btnLogin;
    float v = 0;

    //Firebase
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    //Firebase Database
    ProgressBar progressBar;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login_tab, container, false);
//        don't know why we used root
        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        forgetPass = root.findViewById(R.id.forgot_pass);
        btnLogin = root.findViewById(R.id.login_button);
        progressBar = root.findViewById(R.id.progressbar);

        // Applying Animations
        email.setTranslationX(800);
        pass.setTranslationX(800);
        forgetPass.setTranslationX(800);
        btnLogin.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        btnLogin.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btnLogin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        progressBar.setVisibility(View.GONE);

//        Firebase
        mFirebaseAuth = FirebaseAuth.getInstance();
        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull @NotNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                // If current User exists (not null) then do this
                if (mFirebaseUser != null) {
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "Logged In Succcessfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    getActivity().finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String emailId = email.getText().toString();
                String pwd = pass.getText().toString();
                if (emailId.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    email.setError("Please Enter Email id");
                    email.requestFocus();
                } else if (pwd.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    pass.setError("Please valid Password");
                    pass.requestFocus();
                } else if (pwd.isEmpty() && emailId .isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Fields are Empty", Toast.LENGTH_SHORT).show();
                } else if (!(pwd.isEmpty() && emailId.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(emailId, pwd).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(getActivity(), MainActivity.class));
                                getActivity().finish();
                            }
                        }
                    });
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "There was some Error, Plz try again Later", Toast.LENGTH_SHORT).show();
                }


                //Firebase Database
//                loginUser();
            }
        });
        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

//    public void loginUser() {
//        //Validate Login Info
//        if (!validateUsername() | !validatePassword()) {
//            return;
//        }
//        else{
//            isUser();
//        }
//    }
//    private Boolean validateUsername() {
//        String val = email.getText().toString();
//        if (val.isEmpty()) {
//            email.setError("Field cannot be empty");
//            return false;
//        } else {
//            email.setError(null);
////            email.setErrorEnabled(false);
//            return true;
//        }
//    }
//    private Boolean validatePassword() {
//        String val = pass.getText().toString();
//        if (val.isEmpty()) {
//            pass.setError("Field cannot be empty");
//            return false;
//        } else {
//            pass.setError(null);
////            pass.setErrorEnabled(false);
//            return true;
//        }
//    }
//    private void isUser(){
//        progressBar.setVisibility(View.VISIBLE);
//        final String userEnteredUsername = email.getText().toString().trim();
//        final String userEnteredPassword = pass.getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUser = reference.orderByChild("name").equalTo(userEnteredUsername);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    //To remove error
//                    email.setError(null);
//
//                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
//                    Toast.makeText(getActivity(), passwordFromDB, Toast.LENGTH_SHORT).show();
//
//                    if(passwordFromDB.equals(userEnteredPassword)){
//                        pass.setError(null);
//                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
//                        String surnameFromDB = snapshot.child(userEnteredUsername).child("surname").getValue(String.class);
//                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
//
//                        Intent intent = new Intent(getActivity(), MainActivity.class);
//                        intent.putExtra("name", nameFromDB);
//                        intent.putExtra("surname", surnameFromDB);
//                        intent.putExtra("email", emailFromDB);
//                        startActivity(intent);
//                    }
//                    else {
//                        progressBar.setVisibility(View.GONE);
//                        pass.setError("Wrong Password");
//                        pass.requestFocus();
//                    }
//                }
//                else{
//                    progressBar.setVisibility(View.GONE);
//                    email.setError("No Email Exists");
//                    pass.requestFocus();
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {}
//        });
//    }
}
