package edu.vcu.mymail.alberghinig.hackheist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        final EditText oldPassword = findViewById(R.id.OldPassword);
        final EditText newPassword = findViewById(R.id.NewPassword);
        final EditText newPasswordConformation = findViewById(R.id.NewPasswordConformation);
        final Button submitPasswordUpdateButton = findViewById(R.id.SubmitPasswordChange);


    }
}
