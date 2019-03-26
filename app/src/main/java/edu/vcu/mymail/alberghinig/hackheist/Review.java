package edu.vcu.mymail.alberghinig.hackheist;

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

public class Review extends AppCompatActivity {

    private static String TAG = "Review";
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

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


        final RatingBar ratingBar = findViewById(R.id.Review_RatingBar);
        //final int ratingNumber = 0;//ratingBar.getNumStars();

        final EditText reviewAnswer = findViewById(R.id.Review_ReviewCommentInputField);
        reviewAnswer.setImeOptions(EditorInfo.IME_ACTION_DONE);
        reviewAnswer.setRawInputType(InputType.TYPE_CLASS_TEXT);
        final ImageButton backButton = findViewById(R.id.Review_BackButton);
        final Button submitButton = findViewById(R.id.Review_SubmitButton);

        final Toast successPopUp = Toast.makeText(getApplicationContext(),
                "Thank You!",
                Toast.LENGTH_LONG);

        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), UserSettings.class);
                startActivity(I);
            }
        };

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



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                //ratingNumber = ratingBar.getNumStars();
            }
        });

        backButton.setOnClickListener(goBackEvent);
        submitButton.setOnClickListener(clearScreen);
    }

    public void stopHandler() {
        handler.removeCallbacks(runnable);
        Log.d("HandlerRun", "stopHandlerMain");
    }

    public void startHandler() {
        handler.postDelayed(runnable, 5 * 60 * 1000);
        Log.d("HandlerRun", "startHandlerMain");
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        stopHandler();
        startHandler();
    }

    @Override
    protected void onPause() {
        stopHandler();
        Log.d("onPause", "onPauseActivity change");
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        startHandler();
        Log.d("onResume", "onResume_restartActivity");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopHandler();
        Log.d("onDestroy", "onDestroyActivity change");

    }


}
