package edu.vcu.mymail.alberghinig.hackheist;


/*
 *Written by Imagination Terraformers
 */


//imports necessary libraries
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/*
 *Creates public class Forgot settings extending the app compatible activity
 */
public class Forgot extends AppCompatActivity {

    private static String TAG = "Forgot";
    private Handler handler;
    private Runnable runnable;

    /*
                Overrides the onCreate function
                Sets the screen to the activity_forgot.xml
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        //creates handler and runnable to track time of inactivity
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                Intent logout = new Intent(getApplicationContext(), Welcome.class);
                startActivity(logout);
                Log.d(TAG, "Logged out after 5 minutes of inactivity.");
                finish();

                Toast.makeText(Forgot.this, "Logged out after 5 minutes of inactivity.", Toast.LENGTH_SHORT).show();
            }
        };

        startHandler();


        final String[] emailInput = {""};

        //creates JSONHelper and Request Queue
        final JSONHelper helper = new JSONHelper();
        final RequestQueue requestQueue = VolleySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        //Initializes buttons and grabs text fields and checked boxes
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

        //Sets listener for the back button to be clicked and sends the program to the welcome class
        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Welcome.class);
                startActivity(I);
            }
        };

        //Sets listener for the security question and displays the security question that the user has set in the database
        View.OnClickListener displaySecurityQuestionEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast errorPopUp = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);
                try{final String email = emailEntryBox.getText().toString();} catch(Exception e){return;}
                String email =  emailEntryBox.getText().toString();

                loadAllUsersForSQ(helper, requestQueue, email, errorPopUp, securityQuestion);

            }
        };

        //Sets listener for the display information and shows the user's information that they forgot
        View.OnClickListener displayInformationEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast errorPopUp = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

                try {
                    final String securityQuestion = securityQuestionAnswer.getText().toString();
                } catch (Exception e) {
                    return;
                }
                String securityQuestion = securityQuestionAnswer.getText().toString();

                String securityAnswerInput = "";
                ActiveUser currentUser = new ActiveUser(false);
                final String securityAnswerActual = currentUser.getSecurityQuestionAnswer();
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
                            usernameTextView.setText(currentUser.getUsername());
                            passwordTextView.setText(currentUser.getPassword());
                        } else if(username){
                            usernameTextView.setText(currentUser.getUsername());
                        } else if(password){
                            passwordTextView.setText(currentUser.getPassword());
                        }
                    }else{
                        errorPopUp.setText("Incorrect Security Question Answer");
                        errorPopUp.show();
                    }
                } catch(Exception e){

                }


            }

        };

        //Listens for the activity to be started, depending on the button that the user clicked
        submitInfoRequestButton.setOnClickListener(displayInformationEvent);
        displaySecurityQuestionButton.setOnClickListener(displaySecurityQuestionEvent);
        backButton.setOnClickListener(goBackEvent);

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

    //loads all of the users and their information from the database for their security question
    private void loadAllUsersForSQ(final JSONHelper helper, RequestQueue requestQueue, final String email, final Toast errorPopUp, final TextView sQ) {

        try {

            JSONObject blank = new JSONObject();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, helper.getAllUsersURL(), blank,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("REPLY FROM PHP", response.toString());
                            findUserInfo(email,helper.decodeJsonIntoUserList(response), sQ);
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

    //find the user's information based off of the correct answer to the security question
    private void findUserInfo(String email, ArrayList<User> users, TextView securityQuestion){

        ActiveUser currentUser = new ActiveUser(false);

        for(User u:users)
            if(email.equals(u.getEmail()))
                currentUser = new ActiveUser(u);

        if(currentUser.getFirstName() == null){
            Toast toast = new Toast(this);
            toast.setText("No user found with " + email);
            toast.show();
            securityQuestion.setText("");
        } else {
            securityQuestion.setText(currentUser.getSecurityQuestion());
        }
    }

}
