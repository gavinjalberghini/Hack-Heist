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
 * User Settings test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UserSettingsTest {

    @Rule
    public IntentsTestRule<UserSettings> settingsIntentsTestRule = new IntentsTestRule<UserSettings>(UserSettings.class);

    @Test
    public void userSettingsPageBackButton() {
        //Check the back button
        Espresso.onView(withId(R.id.UserSettings_BackButton)).perform(scrollTo(), click());
        intended(hasComponent(hasClassName(MainMenu.class.getName())));
    }

    @Test
    public void settingsPageChangePasswordButton() {
        //Check the change password button on the settings screen goes to the change password page
        Espresso.onView(withId(R.id.UserSettings_ChangePasswordButton)).perform(click());
        intended(hasComponent(hasClassName(ChangePassword.class.getName())));
    }

    @Test
    public void settingsPageSecurityQuestionButton() {
        //Check the set security question button on the settings screen goes to the set security question page
        Espresso.onView(withId(R.id.UserSettings_SetSecurityQuestionButton)).perform(click());
        intended(hasComponent(hasClassName(SetSecurityQuestion.class.getName())));
    }

    @Test
    public void settingsPageReviewButton() {
        //Check the leave review button on the settings screen goes to the leave review page
        Espresso.onView(withId(R.id.UserSettings_LeaveReviewButton)).perform(scrollTo(), click());
        intended(hasComponent(hasClassName(Review.class.getName())));
    }

    /*@Test
    public void settingsPageResetDataButton() {
        //Check the reset data button on the settings screen goes to the main menu page
        Espresso.onView(withId(R.id.UserSettings_ResetDataButton)).perform(scrollTo(), click());
        intended(hasComponent(hasClassName(MainMenu.class.getName())));
    }

    @Test
    public void settingsPageDeleteAccountButton() {
        //Check the delete account button on the settings screen goes to the welcome page
        Espresso.onView(withId(R.id.UserSettings_DeleteAccountButton)).perform(scrollTo(), click());
        intended(hasComponent(hasClassName(Welcome.class.getName())));
    }*/

}
