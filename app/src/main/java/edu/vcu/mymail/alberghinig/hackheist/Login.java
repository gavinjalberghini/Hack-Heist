package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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
                Intent I = new Intent(getApplicationContext(), SignUp.class);
                startActivity(I);
            }
        };

        View.OnClickListener loginQuery = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast errorPopUp = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

                try{
                    String usernameOrEmail = usernameOrEmailTextBox.getText().toString();
                    String password = passwordTextBox.getText().toString();
                } catch(Exception e){
                    errorPopUp.setText("Invalid input");
                    errorPopUp.show();
                    return;
                }

                String usernameOrEmail = usernameOrEmailTextBox.getText().toString();
                String password = passwordTextBox.getText().toString();

                //TODO verify credientials

                if(!isValidLoginCredentials(usernameOrEmail, password))
                    return;

                Intent I = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(I);

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
        loginButton.setOnClickListener(loginQuery);
        switchToSignupButton.setOnClickListener(switchToSignupScreen);
    }

    private boolean isValidLoginCredentials(String userOrEmail, String password){
        boolean isValid = false;

        return isValid;
    }

}
