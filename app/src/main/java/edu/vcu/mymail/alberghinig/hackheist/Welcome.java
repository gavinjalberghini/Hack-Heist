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


/*
 *Creates public class Welcome extending the app compatible acitvity
*/
public class Welcome extends AppCompatActivity {

    /*
    Overrides the onCreate function
    Sets the screen to the activity_welcome.xml
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Initializes buttons
        Button loginButton = findViewById(R.id.Welcome_LoginButton);
        Button signUpButton = findViewById(R.id.Welcome_SignupButton);
        Button infoButton = findViewById(R.id.Welcome_InfoButton);
        Button forgotButton = findViewById(R.id.Welcome_ForgotButton);
        Button levelsContentButton = findViewById(R.id.Welcome_LevelsContentButton);

        //Sets listener for the login button to be clicked and sends the program to the login class
        View.OnClickListener loginEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Login.class);
                startActivity(I);
            }
        };

        //Sets listener for the sign up button to be clicked and sends the program to the sing up class
        View.OnClickListener signupEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), SignUp.class);
                startActivity(I);
            }
        };

        //Sets listener for the info button to be clicked and sends the program to the developer information class
        View.OnClickListener infoEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), DevInformation.class);
                startActivity(I);
            }
        };

        //Sets listener for the forgot something button to be clicked and sends the program to the forgot class
        View.OnClickListener forgotEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Forgot.class);
                startActivity(I);
            }
        };

        //Sets listener for the forgot something button to be clicked and sends the program to the forgot class
        View.OnClickListener levelsContentEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), LevelContents.class);
                startActivity(I);
            }
        };


        //Listens for the activity to be started, depending on the button that the user clicked
        loginButton.setOnClickListener(loginEvent);
        signUpButton.setOnClickListener(signupEvent);
        infoButton.setOnClickListener(infoEvent);
        forgotButton.setOnClickListener(forgotEvent);
        levelsContentButton.setOnClickListener(levelsContentEvent);
    }

}
