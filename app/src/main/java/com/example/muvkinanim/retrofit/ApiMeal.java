package com.example.muvkinanim.retrofit;

import com.example.muvkinanim.models.Food;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMeal {

    @GET("search.php")
    Call<Food> getMeals(
            @Query("f") String letter
    );
}
