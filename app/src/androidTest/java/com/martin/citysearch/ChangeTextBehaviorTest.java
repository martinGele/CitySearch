package com.martin.citysearch;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.martin.citysearch.about.AboutActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChangeTextBehaviorTest {


    @Rule
    public ActivityScenarioRule<AboutActivity> activityScenarioRule
            = new ActivityScenarioRule<>(AboutActivity.class);

    @Test
    public void changeText_sameActivity() {


        onView(withId(R.id.companyName)).check(matches(withText("Backbase")));
        onView(withId(R.id.companyCity)).check(matches(withText("Amsterdam")));
        onView(withId(R.id.aboutInfo)).check(matches(withText("This is the Backbase assignment for Android engineering positions.")));
        onView(withId(R.id.companyAdress)).check(matches(withText("Jacob Bontiusplaats 9")));


    }


}