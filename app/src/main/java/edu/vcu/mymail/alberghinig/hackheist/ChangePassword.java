package edu.vcu.mymail.alberghinig.hackheist;

/*
 *Written by Imagination Terraformers
 */


//imports necessary libraries
import android.content.Intent;
import android.os.Handler;
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


/*
 *Creates public class ChangePassword settings extending the app compatible activity
 */
public class ChangePassword extends AppCompatActivity {

    private static String TAG = "ChangePassword";
    private Handler handler;
    private Runnable runnable;

    /*
                Overrides the onCreate function
                Sets the screen to the activity_change_password.xml
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        //creates handler and runnable to track time of inactivity
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                Intent logout = new Intent(getApplicationContext(), Welcome.class);
                startActivity(logout);
                Log.d(TAG, "Logged out after 5 minutes of inactivity.");
                finish();

                Toast.makeText(ChangePassword.this, "Logged out after 5 minutes of inactivity.", Toast.LENGTH_SHORT).show();
            }
        };

        startHandler();

        //creates JSONHelper and Request Queue
        final JSONHelper helper = new JSONHelper();
        final RequestQueue requestQueue = VolleySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        //Initializes buttons and grabs text fields
        final EditText oldPasswordText = findViewById(R.id.ChangePassword_OldPasswordInputField);
        final EditText newPasswordText = findViewById(R.id.ChangePassword_NewPasswordInputField);
        final EditText newPasswordConformationText = findViewById(R.id.ChangePassword_NewPasswordConfirmInputField);
        final Button submitPasswordUpdateButton = findViewById(R.id.ChangePassword_SubmitButton);
        final ImageButton backButton = findViewById(R.id.ChangePassword_BackButton);

        //Sets listener for the back button to be clicked and sends the program to the main menu class
        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(I);
            }
        };

        //Sets listener for the submit button to be clicked and changes the user's password in the database
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

        //Listens for the activity to be started, depending on the button that the user clicked
        backButton.setOnClickListener(goBackEvent);
        submitPasswordUpdateButton.setOnClickListener(submitPasswordChange);

    }

    //updates the user's information in the database for their changed password
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

    //stops the handler from recording the time inactive
    public void stopHandler() {
        handler.removeCallbacks(runnable);
        Log.d("HandlerRun", "stopHandlerMain");
    }

    //starts the handler recording the time the user has been inactive
    public void startHandler() {
        handler.postDelayed(runnable, 5 * 60 * 1000);
        Log.d("HandlerRun", "startHandlerMain");
    }

    //Overrides onUserInteraction: when user interacts with the screen, restart the handler
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        stopHandler();
        startHandler();
    }

    //Overrides onPause
    @Override
    protected void onPause() {
        stopHandler();
        Log.d("onPause", "onPauseActivity change");
        super.onPause();

    }

    //Overrides onResume
    @Override
    protected void onResume() {
        super.onResume();
        startHandler();
        Log.d("onResume", "onResume_restartActivity");

    }

    //Overrides onDestroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopHandler();
        Log.d("onDestroy", "onDestroyActivity change");

    }

}
