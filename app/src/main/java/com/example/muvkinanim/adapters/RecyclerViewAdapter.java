package com.example.muvkinanim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muvkinanim.R;
import com.example.muvkinanim.models.Meal;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MealViewHolder> {
    private List<Meal> allMeals;
    Context cont;

    public RecyclerViewAdapter(List<Meal> allMeals, Context cont) {
        this.allMeals = allMeals;
        this.cont = cont;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item,parent, false);
        return new MealViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        holder.setMealData(allMeals.get(position));

    }

    @Override
    public int getItemCount() {
        return allMeals.size();
    }

    public static class MealViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView mealImage;
        TextView mealName, mealDesc;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.mealImage);
            mealName = itemView.findViewById(R.id.mealName);
            mealDesc = itemView.findViewById(R.id.mealPrice);
        }

        public void setMealData(Meal meal){
            mealName.setText(meal.getStrMeal());
            mealDesc.setText(meal.getStrInstructions());
            Picasso.get().load(meal.getStrMealThumb()).into(mealImage);
        }
    }
}
