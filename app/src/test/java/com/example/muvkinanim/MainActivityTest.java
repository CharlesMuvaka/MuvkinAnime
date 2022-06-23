package com.example.muvkinanim;

import static org.junit.Assert.*;

import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    MainActivity act;

    @Before
    public void setUp() {
        act = Robolectric.buildActivity(MainActivity.class).create().start().resume().get();
    }

    @Test
    public void getParagraphText_ReturnsString(){

        TextView order = act.findViewById(R.id.welcome);
        String text = "Welcome";

        assertEquals(text, order.getText());
    }

}

