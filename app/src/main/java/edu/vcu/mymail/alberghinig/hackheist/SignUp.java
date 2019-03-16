package edu.vcu.mymail.alberghinig.hackheist;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final DBController controller = new DBController(this);
        //controller.clearLocalDB();

        ImageButton backButton = findViewById(R.id.SignUp_BackButton);
        Button createAccountButton = findViewById(R.id.SignUp_CreateAccountButton);
        final EditText emailTextField = findViewById(R.id.SignUp_EmailInputField);
        final EditText usernameTextField = findViewById(R.id.SignUp_UsernameInputField);
        final EditText firstNameTextField = findViewById(R.id.SignUp_FirstNameInputField);
        final EditText lastNameTextField = findViewById(R.id.SignUp_LastNameInputField);
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
                try{String firstName = firstNameTextField.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Name");errorPopUp.show();return;}
                try{String lastName = lastNameTextField.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Name");errorPopUp.show();return;}
                try{String password = passwordTextField.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Password");errorPopUp.show();return;}
                try{String confirmPassword = confirmPasswordTextField.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Password Confirmation");errorPopUp.show();return;}
                try{String securityQuestion = securityQuestionTextField.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Security Question");errorPopUp.show();return;}
                try{String securityQuestionAnswer = securityQuestionAnswerTextField.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Security Question Answer");errorPopUp.show();return;}

                String email = emailTextField.getText().toString();
                String username = usernameTextField.getText().toString();
                String firstName = firstNameTextField.getText().toString();
                String lastName = lastNameTextField.getText().toString();
                String password = passwordTextField.getText().toString();
                String confirmPassword = confirmPasswordTextField.getText().toString();
                String securityQuestion = securityQuestionTextField.getText().toString();
                String securityQuestionAnswer = securityQuestionAnswerTextField.getText().toString();

                if(email.equals("") ||
                    username.equals("") ||
                    firstName.equals("")     ||
                    lastName.equals("")     ||
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

                if(isUsernameTaken(controller, username)){
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

                if(securityQuestion.contains(password)){
                    errorPopUp.setText("Security Question can not contain the password");
                    errorPopUp.show();
                    return;
                }

                if(isEmailTaken(controller, email)){
                    errorPopUp.setText("This email is already tied to an account");
                    errorPopUp.show();
                    return;
                }

                User userInstance = new User(firstName, lastName, username, email, password, securityQuestion, securityQuestionAnswer);

                controller.insertUser(userInstance);
                controller.composeJSONfromSQLite();

                userInstance = loadUser(controller, userInstance.getEmail(), userInstance.getPassword());
                ActiveUser primaryUser = new ActiveUser(userInstance);

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

    public static boolean isUsernameTaken(DBController controller, String username) {

        for(User u : controller.getListOfUsers())
            if(username.equalsIgnoreCase(u.getUsername()))
                return true;

        for(User u : controller.getListOfUsers())
            Log.d("User", u.toString());

        return false;
    }

    public static boolean isEmailTaken(DBController controller, String username){

        for(User u : controller.getListOfUsers())
            if(username.equalsIgnoreCase(u.getUsername()))
                return true;

        return  false;

    }

    private User loadUser(DBController controller, String userOrEmail, String password){
        for(User u : controller.getListOfUsers())
            if(userOrEmail.equalsIgnoreCase(u.getUsername()) || userOrEmail.equalsIgnoreCase(u.getEmail()) && password.equals(u.getPassword()))
                return u;

        return null;
    }

}
