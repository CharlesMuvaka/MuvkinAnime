package com.example.muvkinanim;

import android.util.Patterns;

import com.google.android.material.textfield.TextInputLayout;

public class Validator {

    public static boolean validateName(TextInputLayout userName){
        String name = userName.getEditText().getText().toString().trim();

        if (name.isEmpty()){
            userName.setError("Please Enter your Name");
            userName.setErrorEnabled(true);
            return false;
        }else {
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    public static boolean validatePhone(TextInputLayout userPhone){
        String phone = userPhone.getEditText().getText().toString().toString().trim();

        if (phone.isEmpty()){
            userPhone.setError("Please Enter your Phone Number");
            userPhone.setErrorEnabled(true);
            return false;
        }else if (phone.length() < 10){
            userPhone.setErrorEnabled(true);
            userPhone.setError("Mobile Number should be ten digits");
            return false;
        }else{
            userPhone.setError(null);
            userPhone.setErrorEnabled(false);
            return true;
        }
    }

    public static boolean valiadateEmail(TextInputLayout userEmail){
        String email = userEmail.getEditText().getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            userEmail.setErrorEnabled(true);
            userEmail.setError("Please Enter a valid email Address");
            return false;
        }else if (email.isEmpty()){
            userEmail.setError("Please Enter your Email Address");
            userEmail.setErrorEnabled(true);
            return  false;
        }else{
            userEmail.setError(null);
            userEmail.setErrorEnabled(false);
            return  true;
        }
    }

    public static boolean validatePassword(TextInputLayout passWord){
        String password = passWord.getEditText().getText().toString().trim();

        if (password.isEmpty()){
            passWord.setErrorEnabled(true);
            passWord.setError("Please Enter your password");
            return false;
        }else if (password.length() < 6){
            passWord.setErrorEnabled(true);
            passWord.setError("password should be more than six characters");
            return false;
        }else{
            passWord.setError(null);
            passWord.setErrorEnabled(false);
            return true;
        }
    }

    public static boolean confirmPassword(TextInputLayout password1, TextInputLayout password2){
        String pass1 = password1.getEditText().getText().toString().trim();
        String pass2 = password2.getEditText().getText().toString().trim();

        if (pass1.isEmpty()){
            password1.setErrorEnabled(true);
            password1.setError("Please confirm your password");
            return false;
        }else if (!pass1.equals(pass2)){
            password1.setError("Your passwords should match");
            password1.setErrorEnabled(true);
            return false;
        }else {
            password1.setErrorEnabled(false);
            password1.setError(null);
            return true;
        }
    }


}
