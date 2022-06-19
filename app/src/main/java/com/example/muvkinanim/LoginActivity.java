package com.example.muvkinanim;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.example.muvkinanim.databinding.ActivityLoginBinding;
import com.example.muvkinanim.models.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding logBind;
    FirebaseAuth myAuth;
    FirebaseAuth.AuthStateListener myAuthListener;
    SharedPreferences userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logBind = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(logBind.getRoot());

        logBind.btnSign.setOnClickListener(this);
        myAuth = FirebaseAuth.getInstance();
        userData = PreferenceManager.getDefaultSharedPreferences(this);
        createAuthListener();
    }

    private void createAuthListener() {
         myAuthListener = firebaseAuth -> {
             FirebaseUser currentUser = firebaseAuth.getCurrentUser();
             if (currentUser != null){
                 startActivity(new Intent(LoginActivity.this, MainActivity.class));
             }else{
                 Toast.makeText(LoginActivity.this, "User doesn't exist", Toast.LENGTH_SHORT).show();
             }

         };
    }

    @Override
    public void onClick(View v) {
        if (v == logBind.btnSign){
            Intent newIntent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(newIntent);
        }else if (v == logBind.btnLogin){
            login();
        }else if(v == logBind.checkBox){
            logBind.userName.getEditText().setText(userData.getString(Constants.USERNAME, null));
            logBind.userPhone.getEditText().setText(userData.getString(Constants.USER_PASSWORD, null));
        }

    }

    private void login() {
        String email = logBind.userName.getEditText().getText().toString().trim();
        String pass = logBind.userPhone.getEditText().getText().toString().trim();

        myAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()){
                Toast.makeText(this, "Welcome "+ Objects.requireNonNull(task.getResult().getUser()).getDisplayName(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myAuth.addAuthStateListener(myAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAuth.removeAuthStateListener(myAuthListener);
    }
}