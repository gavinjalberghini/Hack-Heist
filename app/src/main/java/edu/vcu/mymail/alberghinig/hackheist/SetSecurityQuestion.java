package edu.vcu.mymail.alberghinig.hackheist;

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

public class SetSecurityQuestion extends AppCompatActivity {

    private static String TAG = "SetSecurityQuestion";
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_security_question);

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

        final JSONHelper helper = new JSONHelper();
        final RequestQueue requestQueue = VolleySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        final EditText securityQuestion = findViewById(R.id.SetSecurityQuestion_QuestionInputField);
        final EditText securityQuestionAnswer = findViewById(R.id.SetSecurityQuestion_AnswerInputField);
        final EditText password = findViewById(R.id.SetSecurityQuestion_PasswordInputField);
        final Button submitSecurityUpdateButton = findViewById(R.id.SetSecurityQuestion_SubmitButton);
        final ImageButton backButton = findViewById(R.id.SetSecurityQuestion_BackButton);
        securityQuestion.setImeOptions(EditorInfo.IME_ACTION_DONE);
        securityQuestion.setRawInputType(InputType.TYPE_CLASS_TEXT);

        final Toast successPopUp = Toast.makeText(getApplicationContext(),
                "Security Question Updated",
                Toast.LENGTH_LONG);

        final Toast missingEntryPopUp = Toast.makeText(getApplicationContext(),
                "Invalid or Missing Input",
                Toast.LENGTH_LONG);

        final Toast incorrectPasswordPopUp = Toast.makeText(getApplicationContext(),
                "Password Incorrect",
                Toast.LENGTH_LONG);

        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), UserSettings.class);
                startActivity(I);
            }
        };

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

        submitSecurityUpdateButton.setOnClickListener(updateSecurityQuestionEvent);
        backButton.setOnClickListener(goBackEvent);

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

    public void stopHandler() {
        handler.removeCallbacks(runnable);
        Log.d("HandlerRun", "stopHandlerMain");
    }

    public void startHandler() {
        handler.postDelayed(runnable, 5 * 60 * 1000);
        Log.d("HandlerRun", "startHandlerMain");
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        stopHandler();
        startHandler();
    }

    @Override
    protected void onPause() {
        stopHandler();
        Log.d("onPause", "onPauseActivity change");
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        startHandler();
        Log.d("onResume", "onResume_restartActivity");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopHandler();
        Log.d("onDestroy", "onDestroyActivity change");

    }
}
