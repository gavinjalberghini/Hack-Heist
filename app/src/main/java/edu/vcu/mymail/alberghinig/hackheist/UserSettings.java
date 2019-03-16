package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class UserSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        final DBController controller = new DBController(this);

        Button resetAccountInfoButton = findViewById(R.id.UserSettings_ResetDataButton);
        Button deleteAccountButton = findViewById(R.id.UserSettings_DeleteAccountButton);
        Button setSecurityQuestionButton = findViewById(R.id.UserSettings_SetSecurityQuestionButton);
        Button changePasswordButton = findViewById(R.id.UserSettings_ChangePasswordButton);
        Button leaveAReviewButton = findViewById(R.id.UserSettings_LeaveReviewButton);
        ImageButton backButton = findViewById(R.id.UserSettings_BackButton);

        final Toast deleteSuccessPopUp = Toast.makeText(getApplicationContext(),
                "Account Deleted",
                Toast.LENGTH_LONG);

        View.OnClickListener resetEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        View.OnClickListener deleteEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActiveUser currentUser = new ActiveUser(false);
                controller.deleteUser(currentUser);
                ActiveUser clearUser = new ActiveUser(true);
                deleteSuccessPopUp.show();
                Intent I = new Intent(getApplicationContext(), Welcome.class);
                startActivity(I);
            }
        };

        View.OnClickListener setSecurityQEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), SetSecurityQuestion.class);
                startActivity(I);
            }
        };

        View.OnClickListener changePasswordEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), ChangePassword.class);
                startActivity(I);
            }
        };

        View.OnClickListener leaveReviewEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Review.class);
                startActivity(I);
            }
        };

        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(I);
            }
        };

        backButton.setOnClickListener(goBackEvent);
        changePasswordButton.setOnClickListener(changePasswordEvent);
        setSecurityQuestionButton.setOnClickListener(setSecurityQEvent);
        resetAccountInfoButton.setOnClickListener(resetEvent);
        deleteAccountButton.setOnClickListener(deleteEvent);
        leaveAReviewButton.setOnClickListener(leaveReviewEvent);

    }

}
