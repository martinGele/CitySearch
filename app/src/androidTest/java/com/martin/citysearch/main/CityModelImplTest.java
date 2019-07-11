package com.martin.citysearch.main;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.google.common.collect.Ordering;
import com.martin.citysearch.R;
import com.martin.citysearch.adapters.CitySearchAdapter;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class CityModelImplTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void getCityInfo() {
        onView(withId(R.id.recycler_view_city)).check(matches(isSortedAlphabetically()));


    }

    private static Matcher<View> isSortedAlphabetically() {
        return new TypeSafeMatcher<View>() {

            private final List<String> cityNames = new ArrayList<>();

            @Override
            protected boolean matchesSafely(View item) {
                RecyclerView recyclerView = (RecyclerView) item;
                CitySearchAdapter citySearchAdapter = (CitySearchAdapter) recyclerView.getAdapter();
                cityNames.clear();
                cityNames.addAll(extractCitynames(citySearchAdapter.getCityList()));
                return Ordering.natural().isOrdered(cityNames);


            }

            private List<String> extractCitynames(List<City> cities) {
                List<String> cityNames = new ArrayList<>();
                for (City city : cities) {
                    cityNames.add(city.getName());
                }
                return cityNames;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has cities sorted alphabetically: " + cityNames);
            }
        };
    }


}