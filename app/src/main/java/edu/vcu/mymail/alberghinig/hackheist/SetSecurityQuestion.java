package edu.vcu.mymail.alberghinig.hackheist;

/*
 *Written by Imagination Terraformers
 */


//imports necessary libraries
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
 *Creates public class SetSecurityQuestion settings extending the app compatible activity
 */
public class SetSecurityQuestion extends AppCompatActivity {

    private static String TAG = "SetSecurityQuestion";
    private Handler handler;
    private Runnable runnable;

    /*
            Overrides the onCreate function
            Sets the screen to the activity_set_security_question.xml
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_security_question);

        //creates handler and runnable to track time of inactivity
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                Intent logout = new Intent(getApplicationContext(), Welcome.class);
                startActivity(logout);
                Log.d(TAG, "Logged out after 5 minutes of inactivity.");
                finish();

                Toast.makeText(SetSecurityQuestion.this, "Logged out after 5 minutes of inactivity.", Toast.LENGTH_SHORT).show();
            }
        };

        startHandler();

        //creates JSONHelper and Request Queue
        final JSONHelper helper = new JSONHelper();
        final RequestQueue requestQueue = VolleySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        //Initializes buttons and grabs text fields
        final EditText securityQuestion = findViewById(R.id.SetSecurityQuestion_QuestionInputField);
        final EditText securityQuestionAnswer = findViewById(R.id.SetSecurityQuestion_AnswerInputField);
        final EditText password = findViewById(R.id.SetSecurityQuestion_PasswordInputField);
        final Button submitSecurityUpdateButton = findViewById(R.id.SetSecurityQuestion_SubmitButton);
        final ImageButton backButton = findViewById(R.id.SetSecurityQuestion_BackButton);
        securityQuestion.setImeOptions(EditorInfo.IME_ACTION_DONE);
        securityQuestion.setRawInputType(InputType.TYPE_CLASS_TEXT);

        //informs the user that their security question has been updated in the database
        final Toast successPopUp = Toast.makeText(getApplicationContext(),
                "Security Question Updated",
                Toast.LENGTH_LONG);

        //informs the user of missing or invalid input
        final Toast missingEntryPopUp = Toast.makeText(getApplicationContext(),
                "Invalid or Missing Input",
                Toast.LENGTH_LONG);

        //informs the user that the password that they ave entered does not match the one saved in the database
        final Toast incorrectPasswordPopUp = Toast.makeText(getApplicationContext(),
                "Password Incorrect",
                Toast.LENGTH_LONG);

        //Sets listener for the back button to be clicked and sends the program to the user settings class
        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), UserSettings.class);
                startActivity(I);
            }
        };

        //Sets listener for the update button to be clicked and updates the user's security question in the database
        View.OnClickListener updateSecurityQuestionEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String securityQuestionString = "";
                String securityQuestionAnswerString = "";
                String passwordString = "";

                try{
                    securityQuestionString = securityQuestion.getText().toString();
                    securityQuestionAnswerString = securityQuestionAnswer.getText().toString();
                    passwordString = password.getText().toString();
                } catch (Exception e){
                    missingEntryPopUp.show();
                    return;
                }

                ActiveUser currentUser = new ActiveUser(false);

                if(passwordString.equals(currentUser.getPassword())){
                    currentUser.setSecurityQuestion(securityQuestionString);
                    currentUser.setSecurityQuestionAnswer(securityQuestionAnswerString);
                    updateUserInfo(helper, requestQueue, currentUser, incorrectPasswordPopUp);

                }else{
                    incorrectPasswordPopUp.show();
                    return;
                }

            }
        };

        //Listens for the activity to be started, depending on the button that the user clicked
        submitSecurityUpdateButton.setOnClickListener(updateSecurityQuestionEvent);
        backButton.setOnClickListener(goBackEvent);

    }

    //updates the user's information in the database
    //looks at current state of user in app and checks that the current username and password are the
    //same in the app and if not it updates it in the database
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
