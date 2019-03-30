package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


/*
 *Written by Imagination Terraformers
 */


//imports necessary libraries
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 *Creates public class Login settings extending the app compatible activity
 */
public class Login extends AppCompatActivity {

    private static String TAG = "Login";
    private Handler handler;
    private Runnable runnable;

    ActiveUser activeUser = new ActiveUser(true);

    /*
            Overrides the onCreate function
            Sets the screen to the activity_login.xml
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //creates handler and runnable to track time of inactivity
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                Intent logout = new Intent(getApplicationContext(), Welcome.class);
                startActivity(logout);
                Log.d(TAG, "Logged out after 5 minutes of inactivity.");
                finish();

                Toast.makeText(Login.this, "Logged out after 5 minutes of inactivity.", Toast.LENGTH_SHORT).show();
            }
        };

        startHandler();

        //creates JSONHelper and Request Queue
        final JSONHelper helper = new JSONHelper();
        final RequestQueue requestQueue = VolleySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        //Initializes buttons and grabs text fields
        final EditText usernameOrEmailTextBox = findViewById(R.id.Login_UsernameOrEmailInputField);
        final EditText passwordTextBox = findViewById(R.id.Login_PasswordInputField);
        final Button switchToSignupButton = findViewById(R.id.Login_SignupButton);
        final Button loginButton = findViewById(R.id.Login_LoginButton);
        final ImageButton backButton = findViewById(R.id.Login_BackButton);

        //Sets listener for the sign up button to be clicked and sends the program to the sign up class
        View.OnClickListener switchToSignupScreen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), SignUp.class);
                startActivity(I);
            }
        };

        //Sets listener for the login button to be clicked and sends the program to the main menu class after successfully logging in
        View.OnClickListener loginQuery = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast errorPopUp = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

                try {
                    String usernameOrEmail = usernameOrEmailTextBox.getText().toString();
                    String password = passwordTextBox.getText().toString();
                } catch (Exception e) {
                    errorPopUp.setText("Invalid input");
                    errorPopUp.show();
                    return;
                }

                String usernameOrEmail = usernameOrEmailTextBox.getText().toString();
                String password = passwordTextBox.getText().toString();

                login(helper, requestQueue, usernameOrEmail, password, errorPopUp);

            }
        };

        //Sets listener for the back button to be clicked and sends the program to the welcome class
        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Welcome.class);
                startActivity(I);
            }
        };

        //Listens for the activity to be started, depending on the button that the user clicked
        backButton.setOnClickListener(goBackEvent);
        loginButton.setOnClickListener(loginQuery);
        switchToSignupButton.setOnClickListener(switchToSignupScreen);
    }

    //logs the user in based off of their information matching an existing account in the database
    private void login(final JSONHelper helper, RequestQueue requestQueue, String usernameOrEmail, String password, final Toast errorPopUp) {

        try {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, helper.getSingleUserURL(), helper.loginInfoAsJSON(usernameOrEmail, password),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("REPLY FROM PHP", response.toString());
                        helper.decodeJSONIntoActiveUser(response);

                        ActiveUser currentUser = new ActiveUser(false);

                        if(currentUser.getFirstName() != null) {
                            Intent I = new Intent(getApplicationContext(), MainMenu.class);
                            startActivity(I);
                        }
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
