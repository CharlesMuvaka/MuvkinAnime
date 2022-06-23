package com.example.muvkinanim;

import static org.junit.Assert.assertEquals;

import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {
    LoginActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(LoginActivity.class).create().start().resume().get();


    }

    @Test
    public void checkAppLogo_returnsAppLogo(){

        TextView logo = activity.findViewById(R.id.logWelcome);
        String text = "Welcome to muvkinTours";

        assertEquals(text,logo.getText());
    }

    @Test
    public void getLoginButtonText_ReturnsString(){

        Button order = activity.findViewById(R.id.btnLogin);
        String text = "login";

        assertEquals(text, order.getText());
    }

    @Test
    public void getSignUpButtonText_ReturnsString(){

        Button order = activity.findViewById(R.id.btnSign);
        String text = "SignUp";

        assertEquals(text, order.getText());
    }

    @Test
    public void getText_ReturnsString(){

        TextView slogan = activity.findViewById(R.id.logSlogan);
        String text = " Home of Tasty meals, Where they all died of obesity";

        assertEquals(text, slogan.getText());
    }

    @Test
    public void getUserNameEditTextHint_ReturnsString(){

        TextInputLayout userPhone = activity.findViewById(R.id.userName);
        String text = "Enter your Email Address";

        assertEquals(text, userPhone.getEditText().getHint());
    }



    public void getPasswordTextHint_ReturnsString(){

        TextInputLayout userPhone = activity.findViewById(R.id.userPhone);
        String text = "Enter your password";

        assertEquals(text, userPhone.getEditText().getHint());
    }

}
