package com.example.muvkinanim.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muvkinanim.MealDetailActivity;
import com.example.muvkinanim.R;
import com.example.muvkinanim.models.Constants;
import com.example.muvkinanim.models.Meal;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MealViewHolder> {
    List<Meal> allMeals;
    Context cont;

    public RecyclerViewAdapter(List<Meal> allMeals, Context cont) {
        this.allMeals = allMeals;
        this.cont = cont;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item,parent, false);
        return new MealViewHolder(v, cont);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        holder.setMealData(allMeals.get(position));

    }

    @Override
    public int getItemCount() {
        return allMeals.size();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ShapeableImageView mealImage;
        TextView mealName, mealDesc;
        MaterialButton readMore, save;
        int position;
        Meal meal;
        Context cont;


        public MealViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.mealImage);
            mealName = itemView.findViewById(R.id.mealName);
            mealDesc = itemView.findViewById(R.id.mealPrice);
            readMore = itemView.findViewById(R.id.readMore);
            save = itemView.findViewById(R.id.save);
            position = getLayoutPosition();
            this.cont = context;

            readMore.setOnClickListener(this);
            save.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        public void setMealData(Meal meal){
            mealName.setText(meal.getStrMeal());
            mealDesc.setText(meal.getStrInstructions());
            Picasso.get().load(meal.getStrMealThumb()).into(mealImage);
            this.meal = meal;
        }

        @Override
        public void onClick(View v) {
            if (v == readMore || v == itemView){
                Intent detail = new Intent(cont, MealDetailActivity.class);
                detail.putExtra("FragmentMeal", (Serializable) allMeals);
                detail.putExtra("position", position);
                cont.startActivity(detail);
            }if (v == save){
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String userId = currentUser.getUid();
                meal.setUserIdMeal(userId);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(Constants.USER_MEALS);

                reference.child(meal.getUserIdMeal()).child(meal.getIdMeal()).setValue(meal);
            }

        }
    }
}
