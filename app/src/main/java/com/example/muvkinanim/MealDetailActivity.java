package com.example.muvkinanim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.muvkinanim.adapters.FragmentMealAdapter;
import com.example.muvkinanim.databinding.ActivityMealDetailBinding;
import com.example.muvkinanim.models.Meal;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        mealList  = (List<Meal>) newIntent.getSerializableExtra("FragmentMeals");
        adp = new FragmentMealAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mealList);
        mealDetBind.viewPager.setAdapter(adp);
        mealDetBind.viewPager.setCurrentItem(position);

        setupNavigation(mealDetBind.bottomView);
    }

    private void setupNavigation(BottomNavigationView bottomView) {

        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(MealDetailActivity.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favourites:
                        return true;
                }
                return false;
            }
        });
    }
}