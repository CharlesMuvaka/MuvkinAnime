package com.example.muvkinanim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.muvkinanim.adapters.RecyclerViewAdapter;
import com.example.muvkinanim.databinding.ActivityMainBinding;
import com.example.muvkinanim.models.Food;
import com.example.muvkinanim.models.Meal;
import com.example.muvkinanim.retrofit.ApiMeal;
import com.example.muvkinanim.retrofit.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBind;
    List<Meal> apiMeals;
    RecyclerViewAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBind.getRoot());

        mainBind.bottomNav.setSelectedItemId(R.id.home);
        navigationBarClick(mainBind.bottomNav);
        makeApiCall("d");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView userSearch = (SearchView) item.getActionView();
        userSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() != 1){
                    mainBind.welcome.setTextColor(Color.RED);
                    mainBind.welcome.setText("Please enter a single character");
                    mainBind.welcome.setVisibility(View.VISIBLE);
                }else{
                    makeApiCall(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout){
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void makeApiCall(String query) {
        ApiMeal getMeals = RetrofitClient.mealClient();
        Call<Food> foodCall = getMeals.getMeals(query);

        foodCall.enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, Response<Food> response) {
                if (response.isSuccessful()){

                    apiMeals = response.body().getMeals();
                    adp = new RecyclerViewAdapter(apiMeals, MainActivity.this);
                    mainBind.recView.setAdapter(adp);
                    mainBind.recView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    mainBind.recView.setHasFixedSize(true);
                    successful();
                }else{
                    unSuccessFul();
                }
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {
                failed();
            }
        });
    }

    public void successful(){
        mainBind.progress.setVisibility(View.GONE);
        mainBind.welcome.setVisibility(View.VISIBLE);
        mainBind.welcome.setText("Welcome "+ FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        mainBind.recView.setVisibility(View.VISIBLE);
    }

    public void unSuccessFul(){
        mainBind.progress.setVisibility(View.GONE);
        mainBind.welcome.setVisibility(View.VISIBLE);
        mainBind.welcome.setText("Check your internet connection and PLEASE TRY AGAIN ");
        mainBind.recView.setVisibility(View.GONE);

    }

    public void failed(){
        mainBind.progress.setVisibility(View.GONE);
        mainBind.welcome.setVisibility(View.VISIBLE);
        mainBind.welcome.setText("Something happened Please try again later ");
        mainBind.recView.setVisibility(View.GONE);

    }


    public void navigationBarClick(BottomNavigationView bottomNav){
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.favourites:
                        startActivity(new Intent(MainActivity.this, FavouritesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}