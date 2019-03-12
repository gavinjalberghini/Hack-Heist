package edu.vcu.mymail.alberghinig.hackheist;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

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

                Toast errorPopUp = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

                try{String email = emailTextField.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Email");errorPopUp.show();return;}
                try{String username = usernameTextField.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Username");errorPopUp.show();return;}
                try{String name = nameTextField.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Name");errorPopUp.show();return;}
                try{String password = passwordTextField.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Password");errorPopUp.show();return;}
                try{String confirmPassword = confirmPasswordTextField.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Password Confirmation");errorPopUp.show();return;}
                try{String securityQuestion = securityQuestionTextField.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Security Question");errorPopUp.show();return;}
                try{String securityQuestionAnswer = securityQuestionAnswerTextField.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Security Question Answer");errorPopUp.show();return;}

                String email = emailTextField.getText().toString();
                String username = usernameTextField.getText().toString();
                String name = nameTextField.getText().toString();
                String password = passwordTextField.getText().toString();
                String confirmPassword = confirmPasswordTextField.getText().toString();
                String securityQuestion = securityQuestionTextField.getText().toString();
                String securityQuestionAnswer = securityQuestionAnswerTextField.getText().toString();

                if(email.equals("") ||
                    username.equals("") ||
                    name.equals("")     ||
                    password.equals("") ||
                    confirmPassword.equals("") ||
                    securityQuestion.equals("") ||
                    securityQuestionAnswer.equals("")){
                    errorPopUp.setText("One or more fields are blank");
                    errorPopUp.show();
                    return;
                }

                if(!isValidEmailAddress(email)){
                    errorPopUp.setText("Email is invalid");
                    errorPopUp.show();
                    return;
                }

                if(isUsernameTaken(username)){
                    errorPopUp.setText("This username is taken");
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

                User userInstance = new User(true);
                userInstance.buildNewUser(name, username, password, email, securityQuestion, securityQuestionAnswer);

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
        createAccountButton.setOnClickListener(createAccountQuery);

    }

    public static boolean isValidEmailAddress(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-z"+"A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isUsernameTaken(String userName) {
        //TODO when database is implemented
        return false;
    }
}
