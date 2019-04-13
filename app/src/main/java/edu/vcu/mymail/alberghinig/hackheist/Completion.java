package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Completion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion);

        //Initializes buttons
        final Button emailButton = findViewById(R.id.Completion_EmailButton);
        final ImageButton backButton = findViewById(R.id.Completion_BackButton);

        //Sets listener for the back button to be clicked and sends the program to the main menu class
        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(I);
            }
        };

        //Sets listener for the menu button to be clicked and sends the program to the main menu class
        View.OnClickListener emailScreen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        };

        //Listens for the activity to be started, depending on the button that the user clicked
        backButton.setOnClickListener(goBackEvent);
        emailButton.setOnClickListener(emailScreen);
    }


    protected void sendEmail() {
        Log.i("Send email", "");

        ActiveUser user = new ActiveUser(false);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");
        emailIntent.setPackage("com.google.android.gm");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, user.getEmail());
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Congratulations! You did it!");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Congratulations " + user.getFirstName() + "! \nYou have completed HackHeist. Now you are ready to move on to bigger and better things. We wish you the best of luck in your future endeavors.\n\n\n Sincerely,\nThe Hack Heist Team");
//        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("@drawable/certificate.jpg"));


        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Completion.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }


    }
}
