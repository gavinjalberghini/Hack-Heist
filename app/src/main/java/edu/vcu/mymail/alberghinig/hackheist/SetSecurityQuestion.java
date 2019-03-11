package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SetSecurityQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_security_question);

        final EditText securityQuestion = findViewById(R.id.SetSecurityQuestion_QuestionInputField);
        final EditText securityQuestionAnswer = findViewById(R.id.SetSecurityQuestion_AnswerInputField);
        final EditText password = findViewById(R.id.SetSecurityQuestion_PasswordInputField);
        final Button submitSecurityUpdateButton = findViewById(R.id.SetSecurityQuestion_SubmitButton);
        final ImageButton backButton = findViewById(R.id.SetSecurityQuestion_BackButton);
        securityQuestion.setImeOptions(EditorInfo.IME_ACTION_DONE);
        securityQuestion.setRawInputType(InputType.TYPE_CLASS_TEXT);

        final Toast successPopUp = Toast.makeText(getApplicationContext(),
                "Security Question Updated",
                Toast.LENGTH_LONG);

        final Toast missingEntryPopUp = Toast.makeText(getApplicationContext(),
                "Invalid or Missing Input",
                Toast.LENGTH_LONG);

        final Toast incorrectPasswordPopUp = Toast.makeText(getApplicationContext(),
                "Password Incorrect",
                Toast.LENGTH_LONG);

        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), UserSettings.class);
                startActivity(I);
            }
        };

        View.OnClickListener updateSecurityQuestionEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String securityQuestionString = "";
                String securityQuestionAnswerString = "";
                String passwordString = "";
                boolean failureFlag = false;

                try{
                    securityQuestionString = securityQuestion.getText().toString();
                    securityQuestionAnswerString = securityQuestionAnswer.getText().toString();
                    passwordString = password.getText().toString();
                } catch (Exception e){
                    failureFlag = true;
                    missingEntryPopUp.show();
                }

                if(!failureFlag){

                    //compare inputed password to the current users password
                    //if the passwords match:
                    //update the database
                    //display toast for correct submission
                    //otherwise:
                    //display toast for invalid password

                }

            }
        };

        submitSecurityUpdateButton.setOnClickListener(updateSecurityQuestionEvent);
        backButton.setOnClickListener(goBackEvent);

    }
}
