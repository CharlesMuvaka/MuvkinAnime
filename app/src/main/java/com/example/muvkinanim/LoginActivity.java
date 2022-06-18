package com.example.muvkinanim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.muvkinanim.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding logBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logBind = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(logBind.getRoot());

        logBind.btnSign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == logBind.btnSign){
            Intent newIntent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(newIntent);
        }

    }
}