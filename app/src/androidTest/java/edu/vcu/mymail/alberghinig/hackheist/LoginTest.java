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
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public IntentsTestRule<Login> loginIntentsTestRule = new IntentsTestRule<Login>(Login.class);

    @Test
    public void loginPageLoginButton() {
        //Check the login button on the start up screen goes to the help page
        Espresso.onView(withId(R.id.Login_UsernameOrEmailInputField)).perform(clearText(), typeText("abd@gmail.com"));
        //pauseTestFor(500);
        Espresso.onView(withId(R.id.Login_PasswordInputField)).perform(clearText(), typeText("12345678"));
        //pauseTestFor(500);
        Espresso.onView(withId(R.id.Login_LoginButton)).perform(click());
        intended(hasComponent(hasClassName(MainMenu.class.getName())));
    }

    @Test
    public void loginPageSignupButton() {
        //Check the sign up button on the start up screen goes to the help page
        Espresso.onView(withId(R.id.Login_SignupButton)).perform(click());
        intended(hasComponent(hasClassName(SignUp.class.getName())));
    }

}
