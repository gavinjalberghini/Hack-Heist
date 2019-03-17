package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Forgot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        final String[] emailInput = {""};

        final DBController controller = new DBController(this);

        final EditText securityQuestionAnswer = findViewById(R.id.Forgot_SecurityQuestionAnswerInputField);
        final EditText emailEntryBox = findViewById(R.id.Forgot_EmailInputField);
        final CheckBox usernameCheckBox = findViewById(R.id.Forgot_UsernameCheckBox);
        final CheckBox passwordCheckBox = findViewById(R.id.Forgot_PasswordCheckBox);
        final Button submitInfoRequestButton = findViewById(R.id.Forgot_SubmitButton);
        final Button displaySecurityQuestionButton = findViewById(R.id.Forgot_DisplaySecurityQuestionButton);
        final ImageButton backButton = findViewById(R.id.Forgot_BackButton);
        final TextView securityQuestion = findViewById(R.id.Forgot_SecurityQuestionTextView);
        final TextView usernameTextView = findViewById(R.id.Forgot_UsernameTextView);
        final TextView passwordTextView = findViewById(R.id.Forgot_PasswordTextView);


        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Welcome.class);
                startActivity(I);
            }
        };

        View.OnClickListener displaySecurityQuestionEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{final String email = emailEntryBox.getText().toString();} catch(Exception e){return;}
                String email =  emailEntryBox.getText().toString();

                for(User u : controller.getListOfUsers()){

                    if(u.getEmail().equals(email)){
                        securityQuestion.setText(u.getSecurityQuestion());
                        emailInput[0] =email;
                    }

                }

                //TODO check if email is linked to account, if not show toast
                //TODO if valid email then send security question
            }
        };
//------------------------------------------------------------------------------------------------------------------------------------------------------------\\
        View.OnClickListener displayInformationEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO retrieve the security question and answer from database
                //TODO send security question via email and expect answer
                //TODO retrieve the username and password from the database
                Toast errorPopUp = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

                try{final String securityQuestion = securityQuestionAnswer.getText().toString();} catch(Exception e){return;}
                String securityQuestion =  securityQuestionAnswer.getText().toString();

                for(User u : controller.getListOfUsers()){

                    if(u.getEmail().equals(emailInput[0])){
                            String securityAnswerInput = "";
                            final String securityAnswerActual = u.getSecurityQuestionAnswer();
                            final String usernameActual = "";
                            final String passwordActual = "";
                        usernameTextView.setText(usernameActual);
                        passwordTextView.setText(passwordActual);

                            try {
                                securityAnswerInput = securityQuestionAnswer.getText().toString();
                                boolean username = usernameCheckBox.isChecked();
                                boolean password = passwordCheckBox.isChecked();

                                if(securityAnswerInput.equals(securityAnswerActual) && !securityAnswerInput.equals("")){

                                    if(!username && !password){
                                        //No info output
                                    } else if(username && password){
                                        usernameTextView.setText(u.getUsername());
                                        passwordTextView.setText(u.getPassword());
                                    } else if(username){
                                        usernameTextView.setText(u.getUsername());
                                    } else if(password){
                                        passwordTextView.setText(u.getPassword());
                                    }
                                }else{
                                    errorPopUp.setText("Incorrect Security Question Answer");
                                    errorPopUp.show();
                                }
                            } catch(Exception e){

                            }


                        }
                    }
                }
            };

        submitInfoRequestButton.setOnClickListener(displayInformationEvent);
        displaySecurityQuestionButton.setOnClickListener(displaySecurityQuestionEvent);
        backButton.setOnClickListener(goBackEvent);

    }

}
