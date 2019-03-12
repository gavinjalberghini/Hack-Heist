package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        final EditText oldPasswordText = findViewById(R.id.ChangePassword_OldPasswordInputField);
        final EditText newPasswordText = findViewById(R.id.ChangePassword_NewPasswordInputField);
        final EditText newPasswordConformationText = findViewById(R.id.ChangePassword_NewPasswordConfirmInputField);
        final Button submitPasswordUpdateButton = findViewById(R.id.ChangePassword_SubmitButton);
        final ImageButton backButton = findViewById(R.id.ChangePassword_BackButton);

        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(I);
            }
        };

        View.OnClickListener submitPasswordChange = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast errorPopUp = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

                try{String oldPassword = oldPasswordText.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Password");errorPopUp.show();return;}
                try{String password = newPasswordText.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Password");errorPopUp.show();return;}
                try{String confirmPassword = newPasswordConformationText.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Password Confirmation");errorPopUp.show();return;}

                String oldPassword = oldPasswordText.getText().toString();
                String password = newPasswordText.getText().toString();
                String confirmPassword = newPasswordConformationText.getText().toString();

                if(oldPassword.equals(password)){
                    errorPopUp.setText("Passwords are the same");
                    errorPopUp.show();
                    return;
                }

                if(password.length() < 8){
                    errorPopUp.setText("Password must be at least 8 characters");
                    errorPopUp.show();
                    return;
                }

                if(!(confirmPassword.equals(password))){
                    errorPopUp.setText("Password and Confirm Password do not match");
                    errorPopUp.show();
                    return;
                }

                //TODO: Update the database
            }
        };

        backButton.setOnClickListener(goBackEvent);
        submitPasswordUpdateButton.setOnClickListener(submitPasswordChange);

    }
}
