package com.example.android.fud;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.android.fud.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class LSignUpTabFragment extends Fragment {

    EditText emailId, passwordId, nameId, surnameId;
    Button btnSignUp;
    FirebaseAuth mFirebaseAuth;

    //Firebase Database
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    ProgressBar progressBar;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_signup_tab, container, false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = root.findViewById(R.id.email);
        passwordId = root.findViewById(R.id.pass);
        nameId = root.findViewById(R.id.user_FirstName);
        surnameId = root.findViewById(R.id.user_lastName);
        btnSignUp = root.findViewById(R.id.signup_button);
        progressBar = root.findViewById(R.id.signup_progress);

        progressBar.setVisibility(View.GONE);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                //Firebase Database
                rootNode = FirebaseDatabase.getInstance(); //Calling the root node
                reference = rootNode.getReference("users");
                String name=  nameId.getText().toString();
                String password=  passwordId.getText().toString();
                String surname=  surnameId.getText().toString();
                String email_store=  emailId.getText().toString();
                UserHelperCLass helperCLass = new UserHelperCLass(name, surname, email_store, password);
                reference.push().setValue(helperCLass);


                //Email-Pass Login
                String email = emailId.getText().toString();
                String pwd = passwordId.getText().toString();
                if(email.isEmpty()){
                    progressBar.setVisibility(View.GONE);
                    emailId.setError("Please Enter Email id");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    progressBar.setVisibility(View.GONE);
                    passwordId.setError("Please enter valid Password");
                    passwordId.requestFocus();
                }
                else if(pwd.isEmpty() && email.isEmpty()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Fields are Empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(pwd.isEmpty() && email.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Please Use a valid email and a Strong Password", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(getActivity(), MainActivity.class));
                            }
                        }
                    });
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "There was some Error, Plz try again Later", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return root;
    }
}
