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

/*
 *Creates public class Help settings extending the app compatible activity
 */
public class Help extends AppCompatActivity {

    private static String TAG = "Help";
    private Handler handler;
    private Runnable runnable;

    /*
                Overrides the onCreate function
                Sets the screen to the activity_help.xml
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //creates handler and runnable to track time of inactivity
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                Intent logout = new Intent(getApplicationContext(), Welcome.class);
                startActivity(logout);
                Log.d(TAG, "Logged out after 5 minutes of inactivity.");
                finish();

                Toast.makeText(Help.this, "Logged out after 5 minutes of inactivity.", Toast.LENGTH_SHORT).show();
            }
        };

        startHandler();

        //Initializes buttons and grabs text
        ImageButton backButton = findViewById(R.id.Help_BackButton);
        final EditText questionForDev = findViewById(R.id.Help_DevQuestionText);
        final Button submitButton = findViewById(R.id.Help_SubmitButton);
        questionForDev.setImeOptions(EditorInfo.IME_ACTION_DONE);
        questionForDev.setRawInputType(InputType.TYPE_CLASS_TEXT);

        //Sets listener for the back button to be clicked and sends the program to the main menu class
        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(I);
            }
        };

        //Sets listener for the submit button to be clicked and sends the developers an email with the user's question
        View.OnClickListener sendRequest = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast errorPopUp = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);

                try{String question = questionForDev.getText().toString();}catch(Exception e){errorPopUp.setText("Invalid/Empty Question");errorPopUp.show();return;}
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
            }
        };

        //Listens for the activity to be started, depending on the button that the user clicked
        backButton.setOnClickListener(goBackEvent);
        submitButton.setOnClickListener(sendRequest);

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
