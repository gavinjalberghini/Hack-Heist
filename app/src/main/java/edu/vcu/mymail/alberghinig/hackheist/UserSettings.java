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
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;


/*
 *Creates public class User settings extending the app compatible acitvity
*/
public class UserSettings extends AppCompatActivity {

    private static String TAG = "UserSettings";
    private Handler handler;
    private Runnable runnable;

    /*
        Overrides the onCreate function
        Sets the screen to the activity_user_settings.xml
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        //creates handler and runnable to track time of inactivity
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                //sends app to the welcome screen (logged out) after five minutes of inactivity and informs the user via a toast message
                Intent logout = new Intent(getApplicationContext(), Welcome.class);
                startActivity(logout);
                Log.d(TAG, "Logged out after 5 minutes of inactivity.");
                finish();

                Toast.makeText(UserSettings.this, "Logged out after 5 minutes of inactivity.", Toast.LENGTH_SHORT).show();
            }
        };

        startHandler();

        //creates JSONHelper and Request Queue
        final JSONHelper helper = new JSONHelper();
        final RequestQueue requestQueue = VolleySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        //Initializes buttons
        Button resetAccountInfoButton = findViewById(R.id.UserSettings_ResetDataButton);
        Button deleteAccountButton = findViewById(R.id.UserSettings_DeleteAccountButton);
        Button setSecurityQuestionButton = findViewById(R.id.UserSettings_SetSecurityQuestionButton);
        Button changePasswordButton = findViewById(R.id.UserSettings_ChangePasswordButton);
        Button leaveAReviewButton = findViewById(R.id.UserSettings_LeaveReviewButton);
        ImageButton backButton = findViewById(R.id.UserSettings_BackButton);

        //sends message to screen to inform user that their account has been deleted
        final Toast deleteSuccessPopUp = Toast.makeText(getApplicationContext(),
                "Account Deleted",
                Toast.LENGTH_LONG);

        //Sets listener for the reset button to be clicked and removes all data from the user's profile
        View.OnClickListener resetEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        //Sets listener for the delete button to be clicked and deletes user's account
        View.OnClickListener deleteEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActiveUser currentUser = new ActiveUser(false);
                deleteUser(helper, requestQueue, currentUser, deleteSuccessPopUp);
            }
        };

        //Sets listener for the set security question button to be clicked and sends the user to the security question page
        View.OnClickListener setSecurityQEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), SetSecurityQuestion.class);
                startActivity(I);
            }
        };

        //Sets listener for the change password button to be clicked and sends the user to the change password page
        View.OnClickListener changePasswordEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), ChangePassword.class);
                startActivity(I);
            }
        };

        //Sets listener for the leave review button to be clicked and sends the user to the review page
        View.OnClickListener leaveReviewEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Review.class);
                startActivity(I);
            }
        };

        //Sets listener for the back button to be clicked and sends the user to the previous page (main menu)
        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(I);
            }
        };

        //Listens for the activity to be started, depending on the button that the user clicked
        backButton.setOnClickListener(goBackEvent);
        changePasswordButton.setOnClickListener(changePasswordEvent);
        setSecurityQuestionButton.setOnClickListener(setSecurityQEvent);
        resetAccountInfoButton.setOnClickListener(resetEvent);
        deleteAccountButton.setOnClickListener(deleteEvent);
        leaveAReviewButton.setOnClickListener(leaveReviewEvent);

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

    //deletes all of the user information from the database when delete account button is selected
    private void deleteUser(final JSONHelper helper, RequestQueue requestQueue, ActiveUser user, final Toast popUp) {

        try {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, helper.getDeleteUserURL(), helper.wrapUserAsJson(user),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("REPLY FROM PHP", response.toString());
                            popUp.setText("Account Deleted");
                            popUp.show();
                            ActiveUser clearUser = new ActiveUser(true);
                            Intent I = new Intent(getApplicationContext(), Welcome.class);
                            startActivity(I);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String serverResp = "Error: " + error;
                            Log.d("VOLLEY ERROR ", serverResp);
                            popUp.setText("Deletion Failure");
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
