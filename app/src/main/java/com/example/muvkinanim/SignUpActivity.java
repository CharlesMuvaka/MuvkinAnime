package com.example.muvkinanim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.muvkinanim.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding signBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signBind = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signBind.getRoot());
    }
}