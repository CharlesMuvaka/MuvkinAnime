package com.example.muvkinanim;

import static org.junit.Assert.*;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class FavouritesActivityTest {
    FavouritesActivity activity;

    @Before
    public void setUp() throws Exception {

        activity = Robolectric.buildActivity(FavouritesActivity.class).create().start().resume().get();
    }

    @Test
    public void checkWelcomeText(){
        TextView welcome = activity.findViewById(R.id.welcome);
        String text = "Welcome";

        assertEquals(text, welcome.getText());
    }

}