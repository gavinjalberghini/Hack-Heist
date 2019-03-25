package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    ActiveUser activeUser = new ActiveUser(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final JSONHelper helper = new JSONHelper();

        final RequestQueue requestQueue = VolleySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        final EditText usernameOrEmailTextBox = findViewById(R.id.Login_UsernameOrEmailInputField);
        final EditText passwordTextBox = findViewById(R.id.Login_PasswordInputField);
        final Button switchToSignupButton = findViewById(R.id.Login_SignupButton);
        final Button loginButton = findViewById(R.id.Login_LoginButton);
        final ImageButton backButton = findViewById(R.id.Login_BackButton);

        View.OnClickListener switchToSignupScreen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), SignUp.class);
                startActivity(I);
            }
        };

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

        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Welcome.class);
                startActivity(I);
            }
        };

        backButton.setOnClickListener(goBackEvent);
        loginButton.setOnClickListener(loginQuery);
        switchToSignupButton.setOnClickListener(switchToSignupScreen);
    }

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

}
