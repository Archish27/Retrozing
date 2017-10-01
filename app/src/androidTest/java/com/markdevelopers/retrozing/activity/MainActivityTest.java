package com.markdevelopers.retrozing.activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.markdevelopers.retrozing.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.support.test.espresso.contrib.RecyclerViewActions;

/**
 * Created by Archish on 7/25/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void onCreate() throws Exception {
        //IdlingResource idlingResource = mainActivityActivityTestRule.getActivity().        Espresso.registerIdlingResources(idlingResource);

        onView(withId(R.id.rvData))
                .perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));

    }

}