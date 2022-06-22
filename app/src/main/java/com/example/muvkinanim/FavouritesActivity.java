package com.example.muvkinanim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.muvkinanim.adapters.RecyclerViewAdapter;
import com.example.muvkinanim.databinding.ActivityMainBinding;
import com.example.muvkinanim.models.Constants;
import com.example.muvkinanim.models.Meal;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {
    ActivityMainBinding mainBind;
    DatabaseReference ref;
    List<Meal> favouriteMeals;
    RecyclerViewAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mainBind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBind.getRoot());

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference(Constants.USER_MEALS).child(userId);

        favouriteMeals = new ArrayList<>();
        adp = new RecyclerViewAdapter(favouriteMeals, FavouritesActivity.this);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot != null){
                    for (DataSnapshot mealSnapshot: snapshot.getChildren()){
                        Meal meal = mealSnapshot.getValue(Meal.class);
                        favouriteMeals.add(meal);
                    }
                    adp.notifyDataSetChanged();

                    mainBind.recView.setAdapter(adp);
                    mainBind.recView.setLayoutManager(new LinearLayoutManager(FavouritesActivity.this));
                    mainBind.recView.setHasFixedSize(true);
                    successful();

                }else {
                    unSuccessFul();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                failed();

            }
        });



        setupNavigation(mainBind.bottomNav);
    }

    public void successful(){
        mainBind.progress.setVisibility(View.GONE);
        mainBind.welcome.setVisibility(View.VISIBLE);
        mainBind.recView.setVisibility(View.VISIBLE);
        mainBind.welcome.setText("Below is a list of your saved Meals");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
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
        Intent intent = new Intent(FavouritesActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void setupNavigation(BottomNavigationView bottomView) {

        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(FavouritesActivity.this, MainActivity.class));
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