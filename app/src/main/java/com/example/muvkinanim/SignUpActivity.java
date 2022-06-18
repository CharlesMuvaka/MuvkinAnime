package com.example.muvkinanim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.muvkinanim.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding signBind;
    FirebaseAuth myAuth;
    FirebaseAuth.AuthStateListener myAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signBind = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signBind.getRoot());

        myAuth = FirebaseAuth.getInstance();

    }
}