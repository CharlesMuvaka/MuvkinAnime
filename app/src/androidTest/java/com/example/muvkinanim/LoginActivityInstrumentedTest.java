package com.example.muvkinanim;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void typeEditTextInputs(){

        onView(allOf(
                isDescendantOfA(withId(R.id.userName)),
                withClassName(endsWith("TextInputEditText"))
        )).perform(typeText("muvakacharles@gmail.com"));

        onView(allOf(isDescendantOfA(withId(R.id.userPhone)),withClassName(endsWith("TextInputEditText")))).perform(typeText("0768761611"));

    }

    @Test
    public void clickCheckButton(){
        onView(withId(R.id.checkBox)).perform(click());
    }

    @Test
    public void clickLoginButton(){
        onView(withId(R.id.btnLogin)).perform(click());
    }



    @Test
    public void clickSinUpButton(){

        onView(withId(R.id.btnSign)).perform(click());
    }
}


