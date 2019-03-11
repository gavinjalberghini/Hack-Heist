package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button loginButton = findViewById(R.id.Welcome_LoginButton);
        Button signUpButton = findViewById(R.id.Welcome_SignupButton);
        Button infoButton = findViewById(R.id.Welcome_InfoButton);
        Button helpButton = findViewById(R.id.but_Help);
        Button forgotButton = findViewById(R.id.but_forgot);

        View.OnClickListener loginEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Login.class);
                startActivity(I);
            }
        };

        View.OnClickListener signupEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent I = new Intent(getApplicationContext(), SignUp.class);
//                startActivity(I);
            }
        };

        View.OnClickListener infoEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent I = new Intent(getApplicationContext(), Info.class);
//                startActivity(I);
            }
        };

        View.OnClickListener helpEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent I = new Intent(getApplicationContext(), Help.class);
//                startActivity(I);
            }
        };

        View.OnClickListener forgotEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Forgot.class);
                startActivity(I);
            }
        };

        loginButton.setOnClickListener(loginEvent);
        signUpButton.setOnClickListener(signupEvent);
        infoButton.setOnClickListener(infoEvent);
        helpButton.setOnClickListener(helpEvent);
        forgotButton.setOnClickListener(forgotEvent);
    }
}
