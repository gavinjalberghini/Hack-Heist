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

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        final JSONHelper helper = new JSONHelper();
        final RequestQueue requestQueue = VolleySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        final EditText oldPasswordText = findViewById(R.id.ChangePassword_OldPasswordInputField);
        final EditText newPasswordText = findViewById(R.id.ChangePassword_NewPasswordInputField);
        final EditText newPasswordConformationText = findViewById(R.id.ChangePassword_NewPasswordConfirmInputField);
        final Button submitPasswordUpdateButton = findViewById(R.id.ChangePassword_SubmitButton);
        final ImageButton backButton = findViewById(R.id.ChangePassword_BackButton);

        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(I);
            }
        };

        View.OnClickListener submitPasswordChange = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast errorPopUp = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

                final Toast incorrectPasswordPopUp = Toast.makeText(getApplicationContext(),
                        "Password Incorrect",
                        Toast.LENGTH_LONG);

                try{String oldPassword = oldPasswordText.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Password");errorPopUp.show();return;}
                try{String password = newPasswordText.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Password");errorPopUp.show();return;}
                try{String confirmPassword = newPasswordConformationText.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Password Confirmation");errorPopUp.show();return;}

                String oldPassword = oldPasswordText.getText().toString();
                String password = newPasswordText.getText().toString();
                String confirmPassword = newPasswordConformationText.getText().toString();

                if(oldPassword.equals(password)){
                    errorPopUp.setText("Passwords are the same");
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

                ActiveUser currentUser = new ActiveUser(false);

                if(oldPassword.equals(currentUser.getPassword())){
                    currentUser.setPassword(password);
                    updateUserInfo(helper, requestQueue, currentUser, incorrectPasswordPopUp);
                }else{
                    incorrectPasswordPopUp.show();
                    return;
                }
            }
        };

        backButton.setOnClickListener(goBackEvent);
        submitPasswordUpdateButton.setOnClickListener(submitPasswordChange);

    }

    private void updateUserInfo(final JSONHelper helper, RequestQueue requestQueue, ActiveUser user, final Toast popUp) {

        try {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, helper.getUpdateUserURL(), helper.wrapUserAsJson(user),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("REPLY FROM PHP", response.toString());
                            popUp.setText("Update Successful");
                            popUp.show();
                            Intent I = new Intent(getApplicationContext(), UserSettings.class);
                            startActivity(I);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String serverResp = "Error: " + error;
                            Log.d("VOLLEY ERROR ", serverResp);
                            popUp.setText("Login credentials are incorrect");
                            popUp.show();
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
