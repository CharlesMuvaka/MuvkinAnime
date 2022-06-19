
package com.example.muvkinanim.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Food {

    @SerializedName("meals")
    @Expose
    private List<Meals> meals = null;

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
    public Food(List<Meals> meals) {
        super();
        this.meals = meals;
    }

    public List<Meals> getMeals() {
        return meals;
    }

    public void setMeals(List<Meals> meals) {
        this.meals = meals;
    }

}
