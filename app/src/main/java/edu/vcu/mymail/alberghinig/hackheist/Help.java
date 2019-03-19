package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ImageButton backButton = findViewById(R.id.Help_BackButton);
        final EditText questionForDev = findViewById(R.id.Review_ReviewCommentInputField);
        final Button submitButton = findViewById(R.id.Review_SubmitButton);
        questionForDev.setImeOptions(EditorInfo.IME_ACTION_DONE);
        questionForDev.setRawInputType(InputType.TYPE_CLASS_TEXT);

        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(I);
            }
        };

        View.OnClickListener sendRequest = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast errorPopUp = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

                try{String question = questionForDev.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Email");errorPopUp.show();return;}
                String emailBody = questionForDev.getText().toString();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"gavinalberghini@yahoo.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Hack Heist Question");
                i.putExtra(Intent.EXTRA_TEXT   , emailBody);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Help.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

                questionForDev.setText("");
                Intent welc = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(welc);
            }
        };

        backButton.setOnClickListener(goBackEvent);
        submitButton.setOnClickListener(sendRequest);

    }
}
