package com.example.muvkinanim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.muvkinanim.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding signBind;
    FirebaseAuth myAuth;
    FirebaseAuth.AuthStateListener myAuthListener;
    SharedPreferences myData;
    SharedPreferences.Editor myDataEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signBind = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signBind.getRoot());

        myAuth = FirebaseAuth.getInstance();
        myData = PreferenceManager.getDefaultSharedPreferences(this);
        myDataEditor = myData.edit();

    }
}