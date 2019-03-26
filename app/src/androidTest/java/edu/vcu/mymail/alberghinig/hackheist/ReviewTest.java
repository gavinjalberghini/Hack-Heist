package edu.vcu.mymail.alberghinig.hackheist;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.Espresso;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;


import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Review test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ReviewTest {


    @Rule
    public final IntentsTestRule<Review> ReviewIntentsTestRule = new IntentsTestRule<Review>(Review.class);

    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static ViewAction setRating(final float rating) {
        if (rating % 0.5 != 0) {
            throw new IllegalArgumentException("Rating must be multiple of 0.5f");
        }

        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(RatingBar.class);
            }

            @Override
            public String getDescription() {
                return "Set rating on RatingBar in 0.5f increments";
            }

            @Override
            public void perform(UiController uiController, View view) {
                GeneralClickAction viewAction = new GeneralClickAction(
                        Tap.SINGLE,
                        new CoordinatesProvider() {
                            @Override
                            public float[] calculateCoordinates(View view) {
                                int[] locationOnScreen = new int[2];
                                view.getLocationOnScreen(locationOnScreen);
                                int screenX = locationOnScreen[0];
                                int screenY = locationOnScreen[1];
                                int numStars = ((RatingBar) view).getNumStars();
                                float widthPerStar = 1f * view.getWidth() / numStars;
                                float percent = rating / numStars;
                                float x = screenX + view.getWidth() * percent;
                                float y = screenY + view.getHeight() * 0.5f;
                                return new float[]{x - widthPerStar * 0.5f, y};
                            }
                        },
                        Press.FINGER,
                        InputDevice.SOURCE_UNKNOWN,
                        MotionEvent.BUTTON_PRIMARY
                );
                viewAction.perform(uiController, view);
            }
        };
    }

    @Test
    public void ReviewPageBackButton() {
        //Check the back button
        Espresso.onView(withId(R.id.Review_BackButton)).perform(scrollTo(), click());
        intended(hasComponent(hasClassName(UserSettings.class.getName())));
    }

    /*@Test
    public void ReviewPageUserInput() {
        //Check the back button
        Espresso.onView(withId(R.id.Review_ReviewCommentInputField)).perform(clearText(), typeText("User Review Entered Here"));
        pauseTestFor(500);

        Espresso.onView(withId(R.id.Review_RatingBar)).perform(scrollTo(), setRating(5f));

        Espresso.onView(withId(R.id.Review_SubmitButton)).perform(scrollTo(), click());
        Espresso.onView(withId(R.id.Review_ReviewCommentInputField)).perform(scrollTo(), clearText());
        Espresso.onView(withId(R.id.Review_RatingBar)).perform(scrollTo(), setRating(0f));


        intended(hasAction(Intent.ACTION_SEND));
        //intended(allOf(hasAction(Intent.ACTION_CHOOSER), hasExtra(is(Intent.EXTRA_INTENT), allOf( hasAction(Intent.ACTION_SEND), hasExtra(Intent.EXTRA_TEXT, "Expected url")))));

    }*/

}

