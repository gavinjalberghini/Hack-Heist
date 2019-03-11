package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        final EditText oldPassword = findViewById(R.id.ChangePassword_OldPasswordInputField);
        final EditText newPassword = findViewById(R.id.ChangePassword_NewPasswordInputField);
        final EditText newPasswordConformation = findViewById(R.id.ChangePassword_NewPasswordConfirmInputField);
        final Button submitPasswordUpdateButton = findViewById(R.id.SubmitPasswordChange);
        final ImageButton backButton = findViewById(R.id.ChangePasswordBackButton);

        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(I);
            }
        };

        backButton.setOnClickListener(goBackEvent);

    }
}
