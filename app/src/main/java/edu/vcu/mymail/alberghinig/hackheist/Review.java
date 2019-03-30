package edu.vcu.mymail.alberghinig.hackheist;


/*
 *Written by Imagination Terraformers
 */


//imports necessary libraries
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;


/*
 *Creates public class Review extending the app compatible activity
 */
public class Review extends AppCompatActivity {

    private static String TAG = "Review";
    private Handler handler;
    private Runnable runnable;

    /*
            Overrides the onCreate function
            Sets the screen to the activity_review.xml
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        //creates handler and runnable to track time of inactivity
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                Intent logout = new Intent(getApplicationContext(), Welcome.class);
                startActivity(logout);
                Log.d(TAG, "Logged out after 5 minutes of inactivity.");
                finish();

                Toast.makeText(Review.this, "Logged out after 5 minutes of inactivity.", Toast.LENGTH_SHORT).show();
            }
        };

        startHandler();

        //Initializes rating bar, buttons, and grabs text fields
        final RatingBar ratingBar = findViewById(R.id.Review_RatingBar);

        final EditText reviewAnswer = findViewById(R.id.Review_ReviewCommentInputField);
        reviewAnswer.setImeOptions(EditorInfo.IME_ACTION_DONE);
        reviewAnswer.setRawInputType(InputType.TYPE_CLASS_TEXT);
        final ImageButton backButton = findViewById(R.id.Review_BackButton);
        final Button submitButton = findViewById(R.id.Review_SubmitButton);

        //thanks user for their input after submit review has been clicked
        final Toast successPopUp = Toast.makeText(getApplicationContext(),
                "Thank You!",
                Toast.LENGTH_LONG);

        //Sets listener for the back button to be clicked and sends the program to the user settings class
        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), UserSettings.class);
                startActivity(I);
            }
        };

        //Sets listener for the submit button to be clicked and clears the input fields that the user had just filled
        //emails the review and the rating to the developing team
        View.OnClickListener clearScreen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailBody = reviewAnswer.getText().toString();
                String reviewScore = "Review Score : " + ratingBar.getRating();
                emailBody += "\n\n"  + reviewScore;


                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"gavinalberghini@yahoo.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Hack Heist Review");
                i.putExtra(Intent.EXTRA_TEXT   , emailBody);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Review.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

                reviewAnswer.getText().clear();
                ratingBar.setRating(0);
                successPopUp.show();
            }
        };


        //gets rating number
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                //ratingNumber = ratingBar.getNumStars();
            }
        });

        //Listens for the activity to be started, depending on the button that the user clicked
        backButton.setOnClickListener(goBackEvent);
        submitButton.setOnClickListener(clearScreen);
    }

    //stops the handler from recording the time inactive
    public void stopHandler() {
        handler.removeCallbacks(runnable);
        Log.d("HandlerRun", "stopHandlerMain");
    }

    //starts the handler recording the time the user has been inactive
    public void startHandler() {
        handler.postDelayed(runnable, 5 * 60 * 1000);
        Log.d("HandlerRun", "startHandlerMain");
    }

    //Overrides onUserInteraction: when user interacts with the screen, restart the handler
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        stopHandler();
        startHandler();
    }

    //Overrides onPause
    @Override
    protected void onPause() {
        stopHandler();
        Log.d("onPause", "onPauseActivity change");
        super.onPause();

    }

    //Overrides onResume
    @Override
    protected void onResume() {
        super.onResume();
        startHandler();
        Log.d("onResume", "onResume_restartActivity");

    }

    //Overrides onDestroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopHandler();
        Log.d("onDestroy", "onDestroyActivity change");

    }


}
