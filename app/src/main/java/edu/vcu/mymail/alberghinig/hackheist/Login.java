package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameOrEmailTextBox = findViewById(R.id.Login_UsernameOrEmailInputField);
        final EditText passwordTextBox = findViewById(R.id.Login_PasswordInputField);
        final Button switchToSignupButton = findViewById(R.id.Login_SignupButton);
        final Button loginButton = findViewById(R.id.Login_LoginButton);
        final ImageButton backButton = findViewById(R.id.Login_BackButton);

        View.OnClickListener switchToSignupScreen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //TODO launch signup screen
                } catch(Exception e){

                }
            }
        };

        View.OnClickListener loginQuery = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String usernameOrEmail = usernameOrEmailTextBox.getText().toString();
                    String password = passwordTextBox.getText().toString();

                    //TODO verify credientials

                } catch(Exception e){

                }
            }
        };

        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Welcome.class);
                startActivity(I);
            }
        };

        backButton.setOnClickListener(goBackEvent);
    }

}
