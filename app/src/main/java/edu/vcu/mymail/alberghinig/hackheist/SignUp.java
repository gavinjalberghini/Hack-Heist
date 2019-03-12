package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ImageButton backButton = findViewById(R.id.SignUp_BackButton);
        Button createAccountButton = findViewById(R.id.SignUp_CreateAccountButton);
        final EditText emailTextField = findViewById(R.id.SignUp_EmailInputField);
        final EditText usernameTextField = findViewById(R.id.SignUp_UsernameInputField);
        final EditText nameTextField = findViewById(R.id.SignUp_NameInputField);
        final EditText passwordTextField = findViewById(R.id.SignUp_PasswordInputField);
        final EditText confirmPasswordTextField = findViewById(R.id.SignUp_ConfirmPasswordInputField);
        final EditText securityQuestionTextField = findViewById(R.id.SignUp_SecurityQuestionInputField);
        final EditText securityQuestionAnswerTextField = findViewById(R.id.SignUp_SecurityQuestionAnswerInputField);

        View.OnClickListener createAccountQuery = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String email = emailTextField.getText().toString();
                    String username = usernameTextField.getText().toString();
                    String name = nameTextField.getText().toString();
                    String password = passwordTextField.getText().toString();
                    String confirmPassword = confirmPasswordTextField.getText().toString();
                    String securityQuestion = securityQuestionTextField.getText().toString();
                    String securityQuestionAnswer = securityQuestionAnswerTextField.getText().toString();
                    //TODO verify credientials

                    Intent I = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(I);

                } catch(Exception e) {
                    //failure logging in
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
        createAccountButton.setOnClickListener(createAccountQuery);

    }
}
