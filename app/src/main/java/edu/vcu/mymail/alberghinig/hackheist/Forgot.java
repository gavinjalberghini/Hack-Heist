package edu.vcu.mymail.alberghinig.hackheist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Forgot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        final EditText securityQuestionAnswer = findViewById(R.id.SecurityQuestionAnswerBox);
        final EditText emailEntryBox = findViewById(R.id.EmailInputBox);
        final CheckBox usernameCheckBox = findViewById(R.id.UsernameCheckBox);
        final CheckBox passwordCheckBox = findViewById(R.id.PasswordCheckBox);
        final Button submitInfoRequestButton = findViewById(R.id.SubmitForgotInfoButton);
        final Button sendSecurityQuestionButton = findViewById(R.id.SendSecurityQuestionButton);


        View.OnClickListener sendSecurityQuestionToEmailEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    final String email = emailEntryBox.getText().toString();
                    //TODO check if email is linked to account, if not show toast
                    //TODO if valid email then send security question
                } catch(Exception e){

                }
            }
        };

        View.OnClickListener requestInformationEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO retrieve the security question and answer from database
                //TODO send security question via email and expect answer
                //TODO retrieve the username and password from the database

                String securityAnswerInput = "";
                final String securityAnswerActual = "";
                final String usernameActual = "";
                final String passwordActual = "";

                try {
                    securityAnswerInput = securityQuestionAnswer.getText().toString();
                    boolean username = usernameCheckBox.isChecked();
                    boolean password = passwordCheckBox.isChecked();

                    if(securityAnswerInput.equals(securityAnswerActual) && !securityAnswerInput.equals("")){

                        if(!username && !password){
                            //No info output
                        } else if(username && password){
                            //Output both
                        } else if(username){
                            //Output username
                        } else if(password){
                            //Output password
                        }

                    }

                } catch(Exception e){
                    //ToastDisplay
                }

            }
        };

        submitInfoRequestButton.setOnClickListener(requestInformationEvent);
        sendSecurityQuestionButton.setOnClickListener(sendSecurityQuestionToEmailEvent);

    }

}
