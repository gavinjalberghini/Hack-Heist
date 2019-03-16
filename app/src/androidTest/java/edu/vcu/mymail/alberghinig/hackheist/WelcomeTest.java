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

import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;


import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Welcome test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class WelcomeTest {

    @Rule
    public IntentsTestRule<Welcome> startupIntentsTestRule = new IntentsTestRule<Welcome>(Welcome.class);

    @Test
    public void welcomePageHelpButton() {
        //Check the help button on the start up screen goes to the help page
        Espresso.onView(withId(R.id.Welcome_HelpButton)).perform(click());
        intended(hasComponent(hasClassName(Help.class.getName())));
    }

    @Test
    public void welcomePageLoginButton() {
        //Check the login button on the start up screen goes to the login page
        Espresso.onView(withId(R.id.Welcome_LoginButton)).perform(click());
        intended(hasComponent(hasClassName(Login.class.getName())));
    }

    @Test
    public void welcomePageSignUpButton() {
        //Check the sign up button on the start up screen goes to the sign up page
        Espresso.onView(withId(R.id.Welcome_SignupButton)).perform(click());
        intended(hasComponent(hasClassName(SignUp.class.getName())));
    }

    @Test
    public void welcomePageInfoButton() {
        //Check the info button on the start up screen goes to the information page
        Espresso.onView(withId(R.id.Welcome_InfoButton)).perform(click());
        intended(hasComponent(hasClassName(DevInformation.class.getName())));
    }

    @Test
    public void welcomePageForgotButton() {
        //Check the forgot button on the start up screen goes to the forgot page
        Espresso.onView(withId(R.id.Welcome_ForgotButton)).perform(scrollTo(), click());
        intended(hasComponent(hasClassName(Forgot.class.getName())));
    }
}