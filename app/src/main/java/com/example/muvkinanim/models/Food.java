
package com.example.muvkinanim.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Food {

    @SerializedName("meals")
    @Expose
    private List<Meal> meals = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Food() {
    }

    /**
     * 
     * @param meals
     */
    public Food(List<Meal> meals) {
        super();
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}
