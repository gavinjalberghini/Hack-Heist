package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.Espresso;

import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;


import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Help test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ForgotTest {
    @Rule
    public IntentsTestRule<Forgot> helpIntentsTestRule = new IntentsTestRule<Forgot>(Forgot.class);

    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ForgotPageBackButton() {
        //Check the back button
        Espresso.onView(withId(R.id.Forgot_BackButton)).perform(scrollTo(), click());
        intended(hasComponent(hasClassName(Welcome.class.getName())));
    }

    /*@Test
    public void displaySecurityQuestionButton() {
        //Check the back button
        Espresso.onView(withId(R.id.Forgot_EmailInputField)).perform(clearText(), typeText("smfoley2nd@gmail.com"));
        pauseTestFor(500);
        Espresso.onView(withId(R.id.Forgot_SecurityQuestionTextView)).perform(scrollTo(), clearText(), typeText("Dog's Name"));
        pauseTestFor(500);
        Espresso.onView(withId(R.id.Forgot_SecurityQuestionAnswerInputField)).perform(scrollTo(), clearText(), typeText("Coastal"));
        pauseTestFor(500);
        Espresso.onView(withId(R.id.Forgot_UsernameCheckBox)).perform(scrollTo(), click());
        Espresso.onView(withId(R.id.Forgot_PasswordCheckBox)).perform(scrollTo(), click());
        Espresso.onView(withId(R.id.Forgot_DisplaySecurityQuestionButton)).perform(scrollTo(), click());

        //intended(hasComponent(hasClassName(Welcome.class.getName())));
    }*/

}
