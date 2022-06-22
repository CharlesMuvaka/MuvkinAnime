package com.example.muvkinanim.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.muvkinanim.databinding.FragmentMealDetailBinding;
import com.example.muvkinanim.models.Constants;
import com.example.muvkinanim.models.Meal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;




public class MealDetailFragment extends Fragment {
    Meal fragmentMeal;
    FragmentMealDetailBinding detBind;




    public MealDetailFragment() {
        // Required empty public constructor
    }


    public static MealDetailFragment newInstance(Meal meal) {
        MealDetailFragment fragment = new MealDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("meal", meal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentMeal = (Meal) getArguments().getSerializable("meal");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        detBind = FragmentMealDetailBinding.inflate(inflater, container, false);
        View v = detBind.getRoot();

        Picasso.get().load(fragmentMeal.getStrMealThumb()).into(detBind.image);
        detBind.name.setText(fragmentMeal.getStrMeal());
        detBind.ingNum.setText(fragmentMeal.getIdMeal());
        detBind.ingre.setText("Meal Ingredients");
        detBind.ingre1.setText(fragmentMeal.getStrIngredient1());
        detBind.ingre2.setText(fragmentMeal.getStrIngredient2());
        detBind.ingre3.setText(fragmentMeal.getStrIngredient3());
        detBind.ingre4.setText(fragmentMeal.getStrIngredient4());
        detBind.ingre5.setText(fragmentMeal.getStrIngredient5());
        detBind.instr.setText("Meal Recipe Measures");
        detBind.instr1.setText(fragmentMeal.getStrMeasure1());
        detBind.instr2.setText(fragmentMeal.getStrMeasure2());
        detBind.instr3.setText(fragmentMeal.getStrMeasure3());
        detBind.instr4.setText(fragmentMeal.getStrMeasure4());
        detBind.instr5.setText(fragmentMeal.getStrMeasure5());
        detBind.desc.setText(fragmentMeal.getStrInstructions());

        detBind.fragSave.setOnClickListener(v1 -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String userId = user.getUid();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(Constants.USER_MEALS);
            reference.child(userId).child(fragmentMeal.getIdMeal()).setValue(fragmentMeal);
        });

        return v;
    }
}