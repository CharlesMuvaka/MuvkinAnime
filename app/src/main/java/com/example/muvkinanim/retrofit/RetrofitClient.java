package com.example.muvkinanim.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static ApiMeal mealClient(){
        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiMeal.class);
    }
}
