package com.example.reunion;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.squareup.javawriter.JavaWriter.type;
import static org.junit.Assert.*;

public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void ListeFonctionnelle()
    {
        Espresso.onView(withId(R.id.recycleViewId)).check(matches(hasMinimumChildCount(1)));
        ;
    }
    @Test
    public  void isName(){
        onView(withId(R.id.ajout))
                .perform(click());
        onView(ViewMatchers.withId(R.id.nom_reunion))
                .check(matches(isDisplayed()));
    }
    @Test
    public  void retour_arriere(){
        onView(withId(R.id.ajout))
                .perform(click());
        onView(ViewMatchers.withId(R.id.imageView2))
                .perform(click());
        onView(ViewMatchers.withId(R.id.recycleViewId))
                .check(matches(isDisplayed()));
    }
    @Test
    public  void ajout_perso(){
        onView(withId(R.id.ajout))
                .perform(click());
        onView(ViewMatchers.withId(R.id.ajout_reunion))
                .check(matches(isDisplayed()));
    }
    @Test
    public  void valider(){
        onView(withId(R.id.ajout))
                .perform(click());
        onView(ViewMatchers.withId(R.id.valider))
                .check(matches(isDisplayed()));
    }
    @After
    public void tearDown() throws Exception {

    }

}