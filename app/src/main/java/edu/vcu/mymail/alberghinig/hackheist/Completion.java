package edu.vcu.mymail.alberghinig.hackheist;

/*
 *Written by Imagination Terraformers
 */


//imports necessary libraries
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/*
 *Creates public class Completion settings extending the app compatible activity
 */
public class Completion extends AppCompatActivity {

    /*
            Overrides the onCreate function
            Sets the screen to the activity_completion.xml
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion);

        //Initializes buttons
        final Button homeButton = findViewById(R.id.Completion_HomeButton);
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
        View.OnClickListener menuScreen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(I);
            }
        };

        //Listens for the activity to be started, depending on the button that the user clicked
        backButton.setOnClickListener(goBackEvent);
        homeButton.setOnClickListener(menuScreen);
    }
}
