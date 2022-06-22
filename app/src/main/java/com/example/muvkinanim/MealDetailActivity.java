package com.example.muvkinanim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.muvkinanim.adapters.FragmentMealAdapter;
import com.example.muvkinanim.databinding.ActivityMealDetailBinding;
import com.example.muvkinanim.models.Constants;
import com.example.muvkinanim.models.Meal;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MealDetailActivity extends AppCompatActivity {
    ActivityMealDetailBinding mealDetBind;
    FragmentMealAdapter adp;
    List<Meal> mealList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mealDetBind = ActivityMealDetailBinding.inflate(getLayoutInflater());
        setContentView(mealDetBind.getRoot());




        Intent newIntent = getIntent();
        int position = newIntent.getIntExtra("position", 0);
        mealList  = (List<Meal>) newIntent.getSerializableExtra("FragmentMeal");

        adp = new FragmentMealAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mealList);
        mealDetBind.viewPager.setAdapter(adp);
        mealDetBind.viewPager.setCurrentItem(position);


    }


}