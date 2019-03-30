package edu.vcu.mymail.alberghinig.hackheist;


/*
 *Written by Imagination Terraformers
 */


//imports necessary libraries
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/*
 *Creates public class MainMenu settings extending the app compatible activity
 */
public class MainMenu extends AppCompatActivity {

    private static String TAG = "MainMenu";
    private Handler handler;
    private Runnable runnable;

    /*
                Overrides the onCreate function
                Sets the screen to the activity_main_menu.xml
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //creates handler and runnable to track time of inactivity
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                Intent logout = new Intent(getApplicationContext(), Welcome.class);
                startActivity(logout);
                Log.d(TAG, "Logged out after 5 minutes of inactivity.");
                finish();

                Toast.makeText(MainMenu.this, "Logged out after 5 minutes of inactivity.", Toast.LENGTH_SHORT).show();
            }
        };

        startHandler();

        //creates new instance of an active user
        ActiveUser user = new ActiveUser(false);
        Log.d("LOGGED IN USER FROM MM", user.toString());

        //Initializes buttons
        Button campaignButton = findViewById(R.id.MainMenu_CampaignButton);
        Button leaderBoardButton = findViewById(R.id.MainMenu_LeaderboardButton);
        Button resetDataButton = findViewById(R.id.MainMenu_ResetButton);
        Button helpButton = findViewById(R.id.MainMenu_HelpScreenButton);
        Button userSettingsButton = findViewById(R.id.MainMenu_UserSettingsButton);
        Button logoutButton = findViewById(R.id.MainMenu_LogoutButton);

        //Sets listener for the campaign button to be clicked and sends the program to the game
        View.OnClickListener campaignEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), UnityLauncher.class);
                startActivity(I);
            }
        };

        //Sets listener for the leaderboard button to be clicked and sends the program to the leaderboard class
        View.OnClickListener leaderboardEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Leaderboard.class);
                startActivity(I);
            }
        };

        //Sets listener for the reset data button to be clicked and sends the menu page after resetting the data
        View.OnClickListener resetDataEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Implement a reset data page with password verification
            }
        };

        //Sets listener for the help button to be clicked and sends the program to the help class
        View.OnClickListener helpEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Help.class);
                startActivity(I);
            }
        };

        //Sets listener for the settings button to be clicked and sends the program to the user settings class
        View.OnClickListener userSettingsEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), UserSettings.class);
                startActivity(I);
            }
        };

        //Sets listener for the logout button to be clicked and sends the program to the welcome class
        View.OnClickListener logoutEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Welcome.class);
                startActivity(I);
            }
        };

        //Listens for the activity to be started, depending on the button that the user clicked
        campaignButton.setOnClickListener(campaignEvent);
        leaderBoardButton.setOnClickListener(leaderboardEvent);
        resetDataButton.setOnClickListener(resetDataEvent);
        helpButton.setOnClickListener(helpEvent);
        userSettingsButton.setOnClickListener(userSettingsEvent);
        logoutButton.setOnClickListener(logoutEvent);

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
