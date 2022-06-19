package com.example.muvkinanim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.example.muvkinanim.databinding.ActivitySignUpBinding;
import com.example.muvkinanim.models.Constants;
import com.example.muvkinanim.models.User;
import com.example.muvkinanim.models.Validator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    ActivitySignUpBinding signBind;
    FirebaseAuth myAuth;
    FirebaseAuth.AuthStateListener myAuthListener;
    SharedPreferences myData;
    SharedPreferences.Editor myDataEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signBind = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signBind.getRoot());

        myAuth = FirebaseAuth.getInstance();
        myData = PreferenceManager.getDefaultSharedPreferences(this);
        myDataEditor = myData.edit();

        signBind.btnSubmit.setOnClickListener(this);
        createAuthListener();
    }
    private void createAuthListener() {
        myAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                if (currentUser != null){
                    Intent mainIntent = new Intent(SignUpActivity.this, MainActivity.class);
                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();
                }
            }
        };
    }
    @Override
    public void onClick(View v) {
        if (v == signBind.btnSubmit){
            createUser();
        }
    }
    private void createUser() {
        String userName = signBind.userName.getEditText().getText().toString().trim();
        String userPhone = signBind.userPhone.getEditText().getText().toString().trim();
        String userEmail = signBind.userTicket.getEditText().getText().toString().trim();
        String password = signBind.userSeat.getEditText().getText().toString().trim();
        String pass = signBind.userCoach.getEditText().getText().toString().trim();

        if (!Validator.validateName(signBind.userName) || !Validator.validatePhone(signBind.userPhone) || !Validator.valiadateEmail(signBind.userTicket) || !Validator.validatePassword(signBind.userSeat) || !Validator.confirmPassword(signBind.userCoach, signBind.userSeat) ){
            return;
        }
        myDataEditor.putString(Constants.USERNAME, userName).apply();
        myDataEditor.putString(Constants.USER_PASSWORD, password).apply();
        myDataEditor.putString(Constants.USER_EMAIL, userEmail).apply();

        myAuth.createUserWithEmailAndPassword(userEmail, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()){
                FirebaseUser user = task.getResult().getUser();
                String userId = user.getUid();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(Constants.USERS);
                User newUser = new User(userId, userName,userPhone,userEmail);
                ref.child(userId).setValue(newUser);

                UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setDisplayName(userName).build();
                user.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SignUpActivity.this, "Successfully created", Toast.LENGTH_LONG
                        ).show();
                    }
                });
                Toast.makeText(this, "Successfully created " + task.getResult().getUser().getDisplayName(), Toast.LENGTH_LONG
                ).show();
            }else{
                Toast.makeText(this, "User Creation Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myAuth.addAuthStateListener(myAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAuth.removeAuthStateListener(myAuthListener);
    }
}