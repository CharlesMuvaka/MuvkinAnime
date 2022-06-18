package com.example.muvkinanim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.muvkinanim.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBind.getRoot());


    }
}