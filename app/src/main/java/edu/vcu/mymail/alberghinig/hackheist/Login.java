package edu.vcu.mymail.alberghinig.hackheist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameOrEmailTextBox = findViewById(R.id.txt_Username);
        final EditText passwordTextBox = findViewById(R.id.txt_Password);
        final Button switchToSignupButton = findViewById(R.id.btn_SignUp);
        final Button loginButton = findViewById(R.id.btn_Login);

        View.OnClickListener switchToSignupScreen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //TODO launch signup screen
                } catch(Exception e){

                }
            }
        };

        View.OnClickListener loginQuery = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String usernameOrEmail = usernameOrEmailTextBox.getText().toString();
                    String password = passwordTextBox.getText().toString();

                    //TODO verify credientials

                } catch(Exception e){

                }
            }
        };
    }

}
