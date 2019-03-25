package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final JSONHelper helper = new JSONHelper();
        final RequestQueue requestQueue = VolleySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

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

                User userInstance = new User(firstName, lastName, username, email, password, securityQuestion, securityQuestionAnswer);
                loadAllUsersForSignUpValidation(helper, requestQueue, errorPopUp, email, username, userInstance);

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

    private void loadAllUsersForSignUpValidation(final JSONHelper helper, final RequestQueue requestQueue, final Toast errorPopUp, final String email, final String username, final User userInstance) {

        try {

            JSONObject blank = new JSONObject();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, helper.getAllUsersURL(), blank,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("REPLY FROM PHP", response.toString());

                            if(validateUsernameAndEmail(helper.decodeJsonIntoUserList(response),email,username,errorPopUp)){
                                ActiveUser primaryUser = new ActiveUser(userInstance);
                                createNewUser(helper, requestQueue, primaryUser, errorPopUp);
                            } else {
                                return;
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String serverResp = "Error: " + error;
                            Log.d("VOLLEY ERROR ", serverResp);
                            errorPopUp.setText("Error loading leaderboard");
                            errorPopUp.show();
                        }
                    });

            Log.d("URL REQUEST", jsonObjectRequest.toString());

            requestQueue.add(jsonObjectRequest);

        }catch(Exception e){
            String msg = "TRY CATCH FAILURE " + e.toString();
            Log.d("VOLLEY ERROR ", msg);
            e.printStackTrace();
        }

    }

    private static boolean validateUsernameAndEmail(ArrayList<User> users, String email, String username, Toast errorPopUp){

        for(User u:users){
            if(u.getUsername().equalsIgnoreCase(username)) {
                errorPopUp.setText("This username is taken");
                errorPopUp.show();
                return false;
            }
        }

        for(User u:users){
            if(u.getEmail().equalsIgnoreCase(email)) {
                errorPopUp.setText("This email is already tied to an account");
                errorPopUp.show();
                return false;
            }
        }

        return true;
    }

    private void createNewUser(final JSONHelper helper, RequestQueue requestQueue, final ActiveUser newUser, final Toast errorPopUp) {

        try {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, helper.getCreateUserURL(), helper.wrapUserAsJson(newUser),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("REPLY FROM PHP", response.toString());
                            helper.decodeJSONIntoActiveUser(response);
                            Intent I = new Intent(getApplicationContext(), MainMenu.class);
                            startActivity(I);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String serverResp = "Error: " + error;
                            Log.d("VOLLEY ERROR ", serverResp);
                            errorPopUp.setText("Login credentials are incorrect");
                            errorPopUp.show();
                        }
                    });

            Log.d("URL REQUEST", jsonObjectRequest.toString());

            requestQueue.add(jsonObjectRequest);

        }catch(Exception e){
            String msg = "TRY CATCH FAILURE " + e.toString();
            Log.d("VOLLEY ERROR ", msg);
            e.printStackTrace();
        }

    }


}
