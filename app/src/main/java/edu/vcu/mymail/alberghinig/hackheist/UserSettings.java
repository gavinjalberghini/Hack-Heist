package edu.vcu.mymail.alberghinig.hackheist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        Button forgotButton = findViewById(R.id.us_btn_forgot);
        Button resetAccountInfoButton = findViewById(R.id.us_btn_reset_data);
        Button deleteAccountButton = findViewById(R.id.us_btn_delete_account);
        Button setSecurityQuestionButton = findViewById(R.id.us_btn_set_security_question);
        Button changePasswordButton = findViewById(R.id.us_btn_change_password);
        Button leaveAReviewButton = findViewById(R.id.us_btn_review);

        View.OnClickListener forgotEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        View.OnClickListener resetEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        View.OnClickListener deleteEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        View.OnClickListener setSecurityQEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        View.OnClickListener changePasswordEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        View.OnClickListener leaveReviewEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };


    }
}
