package com.example.muvkinanim.retrofit;

import com.example.muvkinanim.models.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static ApiMeal mealClient(){
        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(Constants.URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiMeal.class);
    }
}
