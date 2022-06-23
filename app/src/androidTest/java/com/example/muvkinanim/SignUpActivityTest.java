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
public class SignUpActivityTest {


    @Rule
    public ActivityScenarioRule<SignUpActivity> signRule = new ActivityScenarioRule<SignUpActivity>(SignUpActivity.class);

    @Test
    public void addTextsToUserInputs() {


        onView(allOf(
                isDescendantOfA(withId(R.id.userName)),
                withClassName(endsWith("TextInputEditText"))
        )).perform(typeText("Charles Muvaka"));

        onView(allOf(
                isDescendantOfA(withId(R.id.userPhone)),
                withClassName(endsWith("TextInputEditText"))
        )).perform(typeText("0711223344"));

        onView(allOf(
                isDescendantOfA(withId(R.id.userTicket)),
                withClassName(endsWith("TextInputEditText"))
        )).perform(typeText("muvakacharles@gmail.com"));

        onView(allOf(
                isDescendantOfA(withId(R.id.userSeat)),
                withClassName(endsWith("TextInputEditText"))
        )).perform(typeText("muvakacharles"));

        onView(allOf(
                isDescendantOfA(withId(R.id.userCoach)),
                withClassName(endsWith("TextInputEditText"))
        )).perform(typeText("muvakacharles"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("got interrupted!");
        }
    }

    @Test
    public void clickSubmitButton(){
        onView(withId(R.id.btnSubmit)).perform(click());
    }
}



