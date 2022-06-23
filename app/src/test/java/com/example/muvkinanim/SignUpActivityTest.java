package com.example.muvkinanim;

import static org.junit.Assert.*;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class SignUpActivityTest {
    SignUpActivity activity;
    FirebaseAuth myAuth;

    @Before
    public void setUp() throws Exception {

        activity = Robolectric.buildActivity(SignUpActivity.class).create().start().resume().get();

    }

    @Test
    public void getHint_Returns_true(){

        TextInputLayout user = activity.findViewById(R.id.userName);
        TextInputLayout userPhone = activity.findViewById(R.id.userPhone);
        TextInputLayout userEmail = activity.findViewById(R.id.userTicket);
        TextInputLayout userPassword = activity.findViewById(R.id.userSeat);
        TextInputLayout password = activity.findViewById(R.id.userCoach);


        assertEquals("Enter you full name", user.getEditText().getHint());
        assertEquals("Enter you mobile Number", userPhone.getEditText().getHint());
        assertEquals("Enter your Email Address", userEmail.getEditText().getHint());
        assertEquals("Enter your password", userPassword.getEditText().getHint());
        assertEquals("Confirm your password", password.getEditText().getHint());
    }
}


