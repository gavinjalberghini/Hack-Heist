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

public class Login extends AppCompatActivity {

    ActiveUser activeUser = new ActiveUser(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final DBController controller = new DBController(this);

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

                try{
                    String usernameOrEmail = usernameOrEmailTextBox.getText().toString();
                    String password = passwordTextBox.getText().toString();
                } catch(Exception e){
                    errorPopUp.setText("Invalid input");
                    errorPopUp.show();
                    return;
                }

                String usernameOrEmail = usernameOrEmailTextBox.getText().toString();
                String password = passwordTextBox.getText().toString();

                if(!isValidLoginCredentials(controller, usernameOrEmail, password)) {
                    errorPopUp.setText("Your username or password is incorrect");
                    errorPopUp.show();
                    return;
                }

                activeUser = new ActiveUser(loadUser(controller, usernameOrEmail, password));

                Intent I = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(I);

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
    
    private boolean isValidLoginCredentials(DBController controller, String userOrEmail, String password){

        for(User u : controller.getListOfUsers())
            Log.d("User", u.toString());

        for(User u : controller.getListOfUsers())
            if(userOrEmail.equalsIgnoreCase(u.getUsername()) || userOrEmail.equalsIgnoreCase(u.getEmail()) && password.equals(u.getPassword()))
               return true;

        return false;
    }

    private User loadUser(DBController controller, String userOrEmail, String password){
        for(User u : controller.getListOfUsers())
            if(userOrEmail.equalsIgnoreCase(u.getUsername()) || userOrEmail.equalsIgnoreCase(u.getEmail()) && password.equals(u.getPassword()))
                return u;

        return null;
    }

}
