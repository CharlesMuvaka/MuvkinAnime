package com.example.muvkinanim.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.muvkinanim.fragments.MealDetailFragment;

public class FragmentMealAdapter extends FragmentPagerAdapter {
    List<Meal> allMeals;
    public FragmentMealAdapter(@NonNull FragmentManager fm, int behavior, List<Meal> allMeals) {
        super(fm, behavior);
        this.allMeals = allMeals;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        MealDetailFragment mealDetailFragment = MealDetailFragment.newInstance(allMeals.get(position));
        return mealDetailFragment;
    }

    @Override
    public int getCount() {
        return allMeals.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return allCats.get(position).getStrMeal();
    }
}
