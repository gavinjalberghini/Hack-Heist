package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

public class Review extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        //final int ratingNumber = 0;//ratingBar.getNumStars();

        final EditText reviewAnswer = findViewById(R.id.question_for_dev);
        reviewAnswer.setImeOptions(EditorInfo.IME_ACTION_DONE);
        reviewAnswer.setRawInputType(InputType.TYPE_CLASS_TEXT);
        final ImageButton backButton = findViewById(R.id.Review_BackButton);
        final Button submitButton = findViewById(R.id.submit_button);

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
}