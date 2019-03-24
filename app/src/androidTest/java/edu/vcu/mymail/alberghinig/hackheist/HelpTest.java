package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
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
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;


import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Help test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class HelpTest {
    @Rule
    public IntentsTestRule<Help> helpIntentsTestRule = new IntentsTestRule<Help>(Help.class);

    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void helpPageBackButton() {
        //Check the back button
        Espresso.onView(withId(R.id.Help_BackButton)).perform(scrollTo(), click());
        intended(hasComponent(hasClassName(MainMenu.class.getName())));
    }

    /*@Test
    public void devQuestionButton() {
        //Check the back button
        Espresso.onView(withId(R.id.Help_DevQuestionText)).perform(scrollTo(), clearText(), typeText("User Dev Question Entered Here"));
        pauseTestFor(500);
        Espresso.onView(withId(R.id.Help_SubmitButton)).perform(scrollTo(), click());
        intended(hasAction(Intent.ACTION_SEND));
    }*/

}
