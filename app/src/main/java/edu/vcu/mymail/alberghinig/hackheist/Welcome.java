package edu.vcu.mymail.alberghinig.hackheist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button loginButton = findViewById(R.id.but_Login);
        Button signUpButton = findViewById(R.id.but_Signup);
        Button infoButton = findViewById(R.id.but_Information);
        Button helpButton = findViewById(R.id.but_Help);
        Button forgotButton = findViewById(R.id.but_forgot);

        View.OnClickListener loginEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Launch the associated page
            }
        };

        View.OnClickListener signupEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Launch the associated page
            }
        };

        View.OnClickListener infoEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Launch the associated page
            }
        };

        View.OnClickListener helpEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Launch the associated page
            }
        };

        View.OnClickListener forgotEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Launch the associated page
            }
        };

        loginButton.setOnClickListener(loginEvent);
        signUpButton.setOnClickListener(signupEvent);
        infoButton.setOnClickListener(infoEvent);
        helpButton.setOnClickListener(helpEvent);
        forgotButton.setOnClickListener(forgotEvent);
    }
}
