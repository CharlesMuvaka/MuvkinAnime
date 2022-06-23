package com.example.muvkinanim;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FavouritesActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<FavouritesActivity> fireRule = new ActivityScenarioRule<>(FavouritesActivity.class);

    @Test
    public void checkTextViewVisibility() {
        onView(withId(R.id.welcome)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void checkRecyclerViewVisibility() {
        onView(withId(R.id.recView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void checkProgressBarVisibility() {

        onView(withId(R.id.progress)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

    }

    @Test
    public void checkBottomNavVisibility() {

        onView(withId(R.id.bottomNav)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

}



